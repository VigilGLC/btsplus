package fd.se.btsplus.service;

import fd.se.btsplus.model.domain.OperationResult;
import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.repository.bts.AccountRepository;
import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_NOT_ACCEPTABLE;
import static java.net.HttpURLConnection.HTTP_OK;

class AccountServiceTest {
    private AccountService accountService;
    private List<Account> received;

    @BeforeEach
    void setUp() {
        final AccountRepository repository = Mockito.mock(AccountRepository.class);

        Mockito.when(repository.saveAll(Mockito.anyIterable())).
                thenAnswer(invocation -> {
                    final Object arg = invocation.getArguments()[0];
                    Assertions.assertTrue(arg instanceof Iterable);
                    @SuppressWarnings("unchecked") var accounts = (Iterable<Account>) arg;
                    for (Account account : accounts) {
                        received.add(account);
                    }
                    return arg;
                });
        accountService = new AccountService(repository);
        received = new ArrayList<>(2);
    }

    @Test
    void testTransferFromNull() {
        final Account from = null;
        final Account to = new Account();
        final OperationResult res = accountService.transfer(from, to, 0);
        Assertions.assertEquals(HTTP_NOT_ACCEPTABLE, res.getCode());
        Assertions.assertTrue(res.getMessage().toLowerCase().contains("exist"));
    }

    @Test
    void testTransferBalanceNull() {
        final Account from = new Account();
        final Account to = new Account();
        to.setBalance(0d);
        final OperationResult res = accountService.transfer(from, to, 100.1);
        Assertions.assertEquals(HTTP_NOT_ACCEPTABLE, res.getCode());
        Assertions.assertTrue(res.getMessage().toLowerCase().contains("sufficient"));
    }

    @Test
    void testTransferBalanceInsufficient() {
        final Account from = new Account();
        from.setBalance(100d);
        final Account to = new Account();
        to.setBalance(0d);
        final OperationResult res = accountService.transfer(from, to, 100.1);
        Assertions.assertEquals(HTTP_NOT_ACCEPTABLE, res.getCode());
        Assertions.assertTrue(res.getMessage().toLowerCase().contains("sufficient"));
    }

    @Test
    void testTransferToNull() {
        final Account from = new Account();
        from.setBalance(100d);
        final Account to = null;
        final OperationResult res = accountService.transfer(from, to, 99.9);
        Assertions.assertEquals(HTTP_OK, res.getCode());
        Assertions.assertTrue(res.getMessage().toLowerCase().contains("success"));
        Assertions.assertEquals(1, received.size());
    }

    @Test
    void testTransferSuccess() {
        final Account from = new Account();
        from.setBalance(100d);
        final Account to = new Account();
        to.setBalance(0d);
        final OperationResult res = accountService.transfer(from, to, 99.9);
        Assertions.assertEquals(HTTP_OK, res.getCode());
        Assertions.assertTrue(res.getMessage().toLowerCase().contains("success"));
        Assertions.assertEquals(2, received.size());
    }

    @Test
    void testWithdraw() {
        final Account from = new Account();
        from.setBalance(100d);
        final OperationResult res = accountService.withDraw(from, 100);
        Assertions.assertEquals(HTTP_OK, res.getCode());
        Assertions.assertTrue(res.getMessage().toLowerCase().contains("success"));
        Assertions.assertEquals(1, received.size());
    }
}
