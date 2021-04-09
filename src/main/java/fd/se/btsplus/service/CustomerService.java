package fd.se.btsplus.service;

import fd.se.btsplus.model.consts.CreditLevel;
import fd.se.btsplus.model.domain.OperationResult;
import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.model.entity.bts.Bill;
import fd.se.btsplus.model.entity.bts.Customer;
import fd.se.btsplus.model.entity.bts.Loan;
import fd.se.btsplus.repository.bts.AccountRepository;
import fd.se.btsplus.repository.bts.BillRepository;
import fd.se.btsplus.repository.bts.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

import static fd.se.btsplus.model.consts.BillStatus.*;
import static fd.se.btsplus.model.consts.Constant.BANK_ACCOUNT;
import static fd.se.btsplus.model.consts.Constant.PENALTY_RATE;
import static java.net.HttpURLConnection.*;

@AllArgsConstructor
@Service
public class CustomerService {
    private final IDateService dateService;
    private final AccountService accountService;

    private final AccountRepository accountRepository;
    private final LoanRepository loanRepository;
    private final BillRepository billRepository;

    /**
     * @return if paid off.
     */
    private static boolean shave(Bill bill, double toPay) {
        final Double interest = bill.getRemainInterest();
        final Double amount = bill.getRemainAmount();
        if (interest > 0) {
            if (toPay < interest) {
                bill.setRemainInterest(interest - toPay);
                return false;
            } else {
                toPay -= interest;
                bill.setRemainInterest(0d);
            }
        }
        if (amount > 0) {
            if (toPay < amount) {
                bill.setRemainAmount(amount - toPay);
                return false;
            } else {
                toPay -= amount;
                bill.setRemainAmount(0d);
            }
        }
        return bill.getRemainInterest() + bill.getRemainAmount() == 0;
    }

    private static double penaltyInterest(Bill bill) {
        return (bill.getPlanAmount() + bill.getPlanInterest()) * PENALTY_RATE;
    }

    public CreditLevel creditLevel(Customer customer) {
        List<Account> accounts = accountRepository.findByCustomer(customer);
        List<Loan> loans = loanRepository.findByCustomer(customer);
        Double balanceSum = accounts.stream().mapToDouble(Account::getBalance).sum();
        Double loanSum = loans.stream().mapToDouble(Loan::getAmount).sum();
        return CreditLevel.evaluate(balanceSum, loanSum);
    }

    public OperationResult payBill(Long billId, Account account, double amount) {
        int code = HTTP_NOT_ACCEPTABLE;
        String message = "";
        OperationResult result = OperationResult.of(code, message);
        OperationResult backup = null;
        try {
            Bill bill;
            if (billId == null || (bill = billRepository.findById(billId.longValue())) == null) {
                code = HTTP_NOT_FOUND;
                message = "Bill not found.";
                return result;
            }
            if (null == bill.getStatus()) {
                message = "Bill wrong status.";
                return result;
            }
            double balance = account.getBalance();
            final double remain = bill.getRemainAmount() + bill.getRemainInterest();
            final double toPay = Math.min(remain, amount);
            if (balance < toPay) {
                message = "Balance not sufficient.";
                return result;
            }

            if (PAID.equals(bill.getStatus())) {
                code = HTTP_NO_CONTENT;
                message = "Already paid.";
                return result;
            }

            if (UNPAID_PENALIZED.equals(bill.getStatus())) {
                final double penalty = penaltyInterest(bill);
                if (balance >= penalty) {
                    backup = accountService.transfer(account, BANK_ACCOUNT, penalty);
                    if (backup.getCode() != HTTP_OK) {
                        return backup;
                    }
                    code = HTTP_ACCEPTED;
                    message = "Penalty Paid.";
                    balance -= penalty;
                    bill.setStatus(UNPAID_AFTER);
                    bill = billRepository.save(bill);
                } else {
                    message = "Penalty not affordable.";
                    return result;
                }
            }

            if (balance < toPay) {
                return result;
            }

            final boolean paidOff = shave(bill, toPay);
            if (paidOff) {
                bill.setStatus(PAID);
            }
            bill = billRepository.save(bill);
            backup = accountService.transfer(account, BANK_ACCOUNT, toPay);
            if (backup.getCode() != HTTP_OK) {
                return backup;
            }
            code = HTTP_OK;
            message = "Success.";
            return result;
        } finally {
            if (backup != null) {
                code = backup.getCode();
                message = backup.getMessage();
            }
            result.setCode(code);
            result.setMessage(message);
        }
    }

    public OperationResult autoPayBill() {
        final List<Bill> bills = billRepository.findByStatus(UNPAID_PENALIZED);
        int total = bills.size();
        int penaltyPaid = 0;
        int fullPaid = 0;
        for (Bill bill : bills) {
            final double amount = bill.getRemainAmount() + bill.getRemainInterest();
            final Customer customer = bill.getLoan().getCustomer();
            final List<Account> accounts = accountRepository.findByCustomer(customer);
            for (Account account : accounts) {
                final OperationResult result = payBill(bill.getId(), account, amount);
                if (result.getCode() == HTTP_ACCEPTED) {
                    penaltyPaid++;
                } else if (result.getCode() == HTTP_OK) {
                    penaltyPaid++;
                    fullPaid++;
                }
            }
        }
        final String message = MessageFormat.format(
                "Total bills: {1}, Penalty Paid: {2}, Full Paid: {3}",
                total, penaltyPaid, fullPaid);
        return OperationResult.of(HTTP_OK, message);
    }
}
