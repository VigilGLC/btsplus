package fd.se.btsplus.service;

import fd.se.btsplus.config.BtsProperties;
import fd.se.btsplus.model.domain.Available;
import fd.se.btsplus.model.domain.DateEvent;
import fd.se.btsplus.repository.bts.AccountRepository;
import fd.se.btsplus.repository.bts.BillRepository;
import fd.se.btsplus.repository.bts.mock.AccountRepositoryMock;
import fd.se.btsplus.repository.bts.mock.BillRepositoryMock;
import fd.se.btsplus.repository.financial.fund.FundDailyRepository;
import fd.se.btsplus.repository.financial.fund.FundPurchaseRepository;
import fd.se.btsplus.repository.financial.fund.FundRepository;
import fd.se.btsplus.repository.financial.fund.mock.FundDailyRepositoryMock;
import fd.se.btsplus.repository.financial.fund.mock.FundPurchaseRepositoryMock;
import fd.se.btsplus.repository.financial.fund.mock.FundRepositoryMock;
import fd.se.btsplus.repository.financial.stock.StockDailyRepository;
import fd.se.btsplus.repository.financial.stock.StockPurchaseRepository;
import fd.se.btsplus.repository.financial.stock.StockRepository;
import fd.se.btsplus.repository.financial.stock.mock.StockDailyRepositoryMock;
import fd.se.btsplus.repository.financial.stock.mock.StockPurchaseRepositoryMock;
import fd.se.btsplus.repository.financial.stock.mock.StockRepositoryMock;
import fd.se.btsplus.repository.financial.term.TermDailyRepository;
import fd.se.btsplus.repository.financial.term.TermPurchaseRepository;
import fd.se.btsplus.repository.financial.term.TermRepository;
import fd.se.btsplus.repository.financial.term.mock.TermDailyRepositoryMock;
import fd.se.btsplus.repository.financial.term.mock.TermPurchaseRepositoryMock;
import fd.se.btsplus.repository.financial.term.mock.TermRepositoryMock;
import fd.se.btsplus.service.impl.SystemDateService;
import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.ResourceUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class UpdaterServiceTest {
    private static final JsonUtils JSON_UTILS = new JsonUtils();
    private static final ResourceUtils RESOURCE_UTILS = new ResourceUtils();

    private UpdaterService updaterService;

    private Available available;
    private FinancialService financialService;
    private FundRepository fundRepository;
    private StockRepository stockRepository;
    private TermRepository termRepository;
    private FundDailyRepository fundDailyRepository;
    private StockDailyRepository stockDailyRepository;
    private TermDailyRepository termDailyRepository;
    private FundPurchaseRepository fundPurchaseRepository;
    private StockPurchaseRepository stockPurchaseRepository;
    private TermPurchaseRepository termPurchaseRepository;
    private BillRepository billRepository;

    private AccountService accountService;
    private AccountRepository accountRepository;
    private IDateService iDateService;
    private ApplicationEventPublisher publisher;
    private BtsProperties btsProperties;

    @BeforeEach
    void setUp() {
        available = new Available();

        accountRepository = new AccountRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        accountService = new AccountService(accountRepository);
        publisher = Mockito.mock(ApplicationContext.class);
        btsProperties = new BtsProperties();
        iDateService = new SystemDateService(publisher, btsProperties);
        financialService = new FinancialService(fundRepository, stockRepository, termRepository,
                fundDailyRepository, stockDailyRepository, termDailyRepository,
                fundPurchaseRepository, stockPurchaseRepository, termPurchaseRepository,
                accountService, iDateService);

        fundRepository = new FundRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        stockRepository = new StockRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        termRepository = new TermRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        fundDailyRepository = new FundDailyRepositoryMock(RESOURCE_UTILS, JSON_UTILS);
        stockDailyRepository = new StockDailyRepositoryMock(RESOURCE_UTILS, JSON_UTILS);
        termDailyRepository = new TermDailyRepositoryMock(RESOURCE_UTILS, JSON_UTILS);
        fundPurchaseRepository = new FundPurchaseRepositoryMock(RESOURCE_UTILS, JSON_UTILS);
        stockPurchaseRepository = new StockPurchaseRepositoryMock(RESOURCE_UTILS, JSON_UTILS);
        termPurchaseRepository = new TermPurchaseRepositoryMock(RESOURCE_UTILS, JSON_UTILS);
        billRepository = new BillRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);

        ((AccountRepositoryMock) accountRepository).
                init("test-json/UpdaterServiceTest/accounts.json");
        ((FundRepositoryMock) fundRepository).
                init("test-json/UpdaterServiceTest/funds.json");
        ((StockRepositoryMock) stockRepository).
                init("test-json/UpdaterServiceTest/stocks.json");
        ((TermRepositoryMock) termRepository).
                init("test-json/UpdaterServiceTest/terms.json");
        ((FundDailyRepositoryMock) fundDailyRepository).
                init("test-json/UpdaterServiceTest/fundDaily's.json");
        ((StockDailyRepositoryMock) stockDailyRepository).
                init("test-json/UpdaterServiceTest/stockDaily's.json");
        ((TermDailyRepositoryMock) termDailyRepository).
                init("test-json/UpdaterServiceTest/termDaily's.json");
        ((FundPurchaseRepositoryMock) fundPurchaseRepository).
                init("test-json/UpdaterServiceTest/fundPurchase's.json");
        ((StockPurchaseRepositoryMock) stockPurchaseRepository).
                init("test-json/UpdaterServiceTest/stockPurchase's.json");
        ((TermPurchaseRepositoryMock) termPurchaseRepository).
                init("test-json/UpdaterServiceTest/termPurchase's.json");
        ((BillRepositoryMock) billRepository).
                init("test-json/UpdaterServiceTest/bills.json");

        updaterService = new UpdaterService(available, financialService,
                fundRepository, stockRepository, termRepository,
                fundDailyRepository, stockDailyRepository, termDailyRepository,
                fundPurchaseRepository, stockPurchaseRepository, termPurchaseRepository,
                billRepository);
    }

    @AfterEach
    void tearDown() {
        updaterService = null;

        available = null;
        financialService = null;
        fundRepository = null;
        stockRepository = null;
        termRepository = null;
        fundDailyRepository = null;
        stockDailyRepository = null;
        termDailyRepository = null;
        fundPurchaseRepository = null;
        stockPurchaseRepository = null;
        termPurchaseRepository = null;
        billRepository = null;

        accountService = null;
        accountRepository = null;
        iDateService = null;
        publisher = null;
        btsProperties = null;
    }

    @Test
    void onApplicationEvent() {
        Date newDate = DateUtils.truncate(new Date(2021 - 1900, Calendar.APRIL, 20), Calendar.DAY_OF_MONTH);
        Date lastDate = DateUtils.addDays(newDate, -1);
        DateEvent event = new DateEvent(iDateService, lastDate, newDate);
        updaterService.onApplicationEvent(event);
    }

    @Test
    void update() {
        Date newDate = DateUtils.truncate(new Date(2021 - 1900, Calendar.APRIL, 12), Calendar.DAY_OF_MONTH);
        Date lastDate = DateUtils.addDays(newDate, -1);
        updaterService.update(lastDate, newDate);
    }
}