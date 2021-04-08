package fd.se.btsplus.service;

import fd.se.btsplus.model.domain.OperationResult;
import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.repository.bts.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public OperationResult transfer(Account from, Account to, double amount) {
        throw new NotImplementedException();
    }

    public OperationResult withDraw(Account account, double amount) {
        throw new NotImplementedException();
    }

}
