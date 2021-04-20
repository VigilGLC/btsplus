package fd.se.btsplus.service;

import fd.se.btsplus.model.domain.OperationResult;
import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.repository.bts.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_NOT_ACCEPTABLE;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public OperationResult transfer(Account from, Account to, double amount) {
        int code = HTTP_NOT_ACCEPTABLE;
        String message = "";
        final OperationResult result = OperationResult.of(code, message);
        try {
            if (from == null) {
                message = "Account not exist.";
                return result;
            }
            final Double balance = from.getBalance();
            if (balance == null || balance < amount) {
                message = "Balance not sufficient.";
                return result;
            }
            if (amount < 0) {
                message = "Amount cannot be negative.";
                return result;
            }

            List<Account> toSave = new ArrayList<>();
            toSave.add(from);
            from.setBalance(balance - amount);

            if (to != null) {
                to.setBalance(to.getBalance() + amount);
                toSave.add(to);
            }
            accountRepository.saveAll(toSave);
            code = HTTP_OK;
            message = "Success.";
            return result;
        } finally {
            result.setCode(code);
            result.setMessage(message);
        }
    }

    public OperationResult withDraw(Account account, double amount) {
        return transfer(account, null, amount);
    }

}
