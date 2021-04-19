package fd.se.btsplus.service;

import fd.se.btsplus.repository.bts.AccountRepository;
import fd.se.btsplus.repository.bts.BillRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CustomerServiceTest {
    private AccountRepository accountRepository;
    private BillRepository billRepository;
    private AccountService accountService;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        billRepository = Mockito.mock(BillRepository.class);
        accountService = new AccountService(accountRepository);
        customerService = new CustomerService(null,
                accountService, accountRepository, billRepository);

        initAccountRepository();
        initBillRepository();
    }

    void initAccountRepository() {

    }

    private void initBillRepository() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void creditLevel() {
    }

    @Test
    void payBill() {
    }
}
