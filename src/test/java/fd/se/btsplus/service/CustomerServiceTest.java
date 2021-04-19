package fd.se.btsplus.service;

import fd.se.btsplus.model.consts.CreditLevel;
import fd.se.btsplus.model.entity.bts.Account;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

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

        accountService = new AccountService(accountRepository);
        customerService = new CustomerService(null, accountService,
                accountRepository, billRepository);
    }

    @AfterEach
    void tearDown() {
        accountRepository = null;
        billRepository = null;
        customerRepository = null;
        accountService = null;
        customerService = null;
    }

    //region init repo
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
    void payBill() {
    }
}
