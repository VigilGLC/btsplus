package fd.se.btsplus.service;

import fd.se.btsplus.model.consts.CreditLevel;
import fd.se.btsplus.model.domain.OperationResult;
import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.model.entity.bts.Bill;
import fd.se.btsplus.model.entity.bts.Customer;
import fd.se.btsplus.repository.bts.AccountRepository;
import fd.se.btsplus.repository.bts.BillRepository;
import fd.se.btsplus.repository.bts.CustomerRepository;
import fd.se.btsplus.repository.bts.LoanRepository;
import fd.se.btsplus.repository.bts.mock.AccountRepositoryMock;
import fd.se.btsplus.repository.bts.mock.BillRepositoryMock;
import fd.se.btsplus.repository.bts.mock.CustomerRepositoryMock;
import fd.se.btsplus.repository.bts.mock.LoanRepositoryMock;
import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.ResourceUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Method;
import java.text.MessageFormat;

import static fd.se.btsplus.model.consts.BillStatus.*;
import static fd.se.btsplus.service.Utils.epsilonEqual;
import static java.net.HttpURLConnection.*;

class CustomerServiceTest {
    private static final JsonUtils JSON_UTILS = new JsonUtils();
    private static final ResourceUtils RESOURCE_UTILS = new ResourceUtils();

    private AccountRepository accountRepository;
    private BillRepository billRepository;
    private CustomerRepository customerRepository;
    private LoanRepository loanRepository;

    private AccountService accountService;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        accountRepository = new AccountRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        billRepository = new BillRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        customerRepository = new CustomerRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        loanRepository = new LoanRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);

        initAccountRepository();
        initBillRepository();
        initCustomerRepository();
        initLoanRepository();

        accountService = Mockito.mock(AccountService.class);
        initAccountService();

        customerService = new CustomerService(accountService, accountRepository, billRepository);
    }

    @AfterEach
    void tearDown() {
        accountRepository = null;
        billRepository = null;
        customerRepository = null;
        accountService = null;
        customerService = null;
    }

    //region init dep
    void initAccountRepository() {
        ((AccountRepositoryMock) accountRepository).
                init("test-json/CustomerServiceTest/accounts.json");
    }

    private void initBillRepository() {
        ((BillRepositoryMock) billRepository).
                init("test-json/CustomerServiceTest/bills.json");
    }

    private void initCustomerRepository() {
        ((CustomerRepositoryMock) customerRepository).
                init("test-json/CustomerServiceTest/customers.json");
    }

    private void initLoanRepository() {
        ((LoanRepositoryMock) loanRepository).init("test-json/CustomerServiceTest/loans.json");
    }

    private void initAccountService() {
        accountService = new AccountService(accountRepository);
    }
    //endregion

    @Test
    void testCreditLevelForCus1() {
        final Account account = accountRepository.findByAccountNumAndPassword("num1", "");
        final Customer customer = account.getCustomer();
        final CreditLevel level = customerService.creditLevel(customer);
        Assertions.assertEquals(CreditLevel.LEVEL_1, level);
    }

    @Test
    void testMessageFormat() {
        final String[] s = new String[1];
        Assertions.assertDoesNotThrow(() -> {
            s[0] = MessageFormat.format("{0},{1},{2}", "1", "2", "3");
        });
        Assertions.assertEquals("1,2,3", s[0]);
        Assertions.assertDoesNotThrow(() -> {
            s[0] = MessageFormat.format("{1},{2},{3}", "1", "2", "3");
        });
        Assertions.assertEquals("2,3,{3}", s[0]);
    }

    @Test
    void testPayBillNotExist() {
        Account account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        OperationResult res = customerService.payBill(0L, account, 100000);
        Assertions.assertEquals(HTTP_NOT_FOUND, res.getCode());
        res = customerService.payBill(null, account, 100000);
        Assertions.assertEquals(HTTP_NOT_FOUND, res.getCode());
    }

    @Test
    void testPayBill2Full() {
        final long billId = 2;
        final double amount = 100000;
        Account account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        OperationResult res = customerService.payBill(billId, account, amount);
        Assertions.assertEquals(HTTP_OK, res.getCode());
        Bill bill = billRepository.findById(billId);
        Assertions.assertEquals(PAID, bill.getStatus());
    }

    @Test
    void testPayBill2Part() {
        final long billId = 2;
        final double amount = 500;
        Account account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        final double balance = account.getBalance();
        OperationResult res = customerService.payBill(billId, account, amount);
        Assertions.assertEquals(HTTP_OK, res.getCode());
        Bill bill = billRepository.findById(billId);
        Assertions.assertEquals(0, bill.getRemainInterest());
        account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        Assertions.assertTrue(epsilonEqual(balance - amount, account.getBalance()));
        Assertions.assertEquals(UNPAID_BEFORE, bill.getStatus());
    }

    @Test
    void testPayBill3Penalty() {
        final long billId = 3;
        final double amount = 1500;
        Account account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        OperationResult res = customerService.payBill(billId, account, amount);
        Assertions.assertEquals(HTTP_OK, res.getCode());
        Bill bill = billRepository.findById(billId);
        Assertions.assertEquals(UNPAID_AFTER, bill.getStatus());
    }

    @Test
    void testPayBill3Full() {
        final long billId = 3;
        final double amount = 40000;
        Account account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        Double balance = account.getBalance();
        Bill bill = billRepository.findById(billId);
        Double remain = bill.getRemainAmount() + bill.getRemainInterest();
        Double penalty = getPenalty(bill);
        OperationResult res = customerService.payBill(billId, account, amount);
        Assertions.assertEquals(HTTP_OK, res.getCode());

        bill = billRepository.findById(billId);
        account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        Assertions.assertEquals(PAID, bill.getStatus());
        Assertions.assertTrue(epsilonEqual(balance - remain - penalty, account.getBalance()));
    }

    @Test
    void testPayBill4Part() {
        final long billId = 4;
        final double amount = 20000;
        Account account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        Bill bill = billRepository.findById(billId);
        double remain = bill.getRemainAmount() + bill.getRemainInterest();
        OperationResult res = customerService.payBill(billId, account, amount);
        Assertions.assertEquals(HTTP_OK, res.getCode());
        bill = billRepository.findById(billId);
        Assertions.assertEquals(UNPAID_AFTER, bill.getStatus());
        Assertions.assertTrue(epsilonEqual(remain - amount,
                bill.getRemainAmount() + bill.getRemainInterest()));
    }

    @Test
    void testPayBill5() {
        final long billId = 5;
        final double amount = 200;
        Account account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        Double balance = account.getBalance();
        OperationResult res = customerService.payBill(billId, account, amount);
        Assertions.assertEquals(HTTP_NO_CONTENT, res.getCode());

        Bill bill = billRepository.findById(billId);
        account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        Assertions.assertEquals(PAID, bill.getStatus());
        Assertions.assertTrue(epsilonEqual(balance, account.getBalance()));
    }

    @Test
    void testPayBill101() {
        final long billId = 101;
        final double amount = 100001;
        Account account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        OperationResult res = customerService.payBill(billId, account, amount);
        Assertions.assertEquals(HTTP_NOT_ACCEPTABLE, res.getCode());
        Assertions.assertTrue(res.getMessage().toLowerCase().contains("not sufficient"));
    }

    @Test
    void testPayBill102() {
        final long billId = 102;
        final double amount = 99999;
        Account account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        OperationResult res = customerService.payBill(billId, account, amount);
        Assertions.assertEquals(HTTP_ACCEPTED, res.getCode());
    }

    @Test
    void testPayBill103PenaltyUnaffordable() {
        final long billId = 103;
        final double amount = 10;
        Account account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        OperationResult res = customerService.payBill(billId, account, amount);
        Assertions.assertEquals(HTTP_NOT_ACCEPTABLE, res.getCode());
        Assertions.assertTrue(res.getMessage().toLowerCase().contains("not affordable"));
    }

    @Test
    void testInvalidBill() {
        final long billId = 2;
        Bill bill = billRepository.findById(billId);
        bill.setStatus(null);
        billRepository.save(bill);
        Account account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        OperationResult res = customerService.payBill(billId, account, 10);
        Assertions.assertEquals(HTTP_NOT_ACCEPTABLE, res.getCode());
    }

    @Test
    void testInvalidAccount() {
        final long billId = 2;
        Account account = null;
        OperationResult res = customerService.payBill(billId, account, 10);
        Assertions.assertEquals(HTTP_NOT_FOUND, res.getCode());
    }

    @Test
    void testInvalidAmount() {
        final long billId = 2;
        Account account = accountRepository.
                findByAccountNumAndPassword("num2", "");
        OperationResult res = customerService.payBill(billId, account, -1);
        Assertions.assertEquals(HTTP_NOT_ACCEPTABLE, res.getCode());
    }

    @Test
    void testAutoPay() {
        OperationResult res = customerService.autoPayBill();
        Assertions.assertEquals(HTTP_OK, res.getCode());
    }

    @SneakyThrows
    double getPenalty(Bill bill) {
        Method method = customerService.getClass().
                getDeclaredMethod("penaltyInterest", Bill.class);
        method.setAccessible(true);
        return ((Double) method.invoke(null, bill));
    }
}
