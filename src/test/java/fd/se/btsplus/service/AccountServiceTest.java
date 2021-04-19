package fd.se.btsplus.service;

import fd.se.btsplus.repository.bts.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AccountServiceTest {
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        final AccountRepository repository = Mockito.mock(AccountRepository.class);
        Mockito.when(repository.saveAll(Mockito.anyIterable())).
                thenAnswer(invocation -> invocation.getArguments()[0]);
        accountService = new AccountService(repository);
    }

    @Test
    void transfer() {
        accountService.transfer(null, null, 0);
    }

    @Test
    void withDraw() {
        accountService.withDraw(null, 0);
    }
}