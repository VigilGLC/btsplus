package fd.se.btsplus.service;

import fd.se.btsplus.config.BtsProperties;
import fd.se.btsplus.model.consts.Constant;
import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.model.entity.financial.fund.Fund;
import fd.se.btsplus.model.entity.financial.stock.Stock;
import fd.se.btsplus.model.entity.financial.stock.StockDaily;
import fd.se.btsplus.model.entity.financial.term.Term;
import fd.se.btsplus.repository.bts.AccountRepository;
import fd.se.btsplus.repository.bts.mock.AccountRepositoryMock;
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
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;

import java.lang.reflect.Method;
import java.time.Period;

import static java.net.HttpURLConnection.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FinancialServiceTest {
    private static final JsonUtils JSON_UTILS = new JsonUtils();
    private static final ResourceUtils RESOURCE_UTILS = new ResourceUtils();

    private FundRepository fundRepository;
    private StockRepository stockRepository;
    private TermRepository termRepository;

    private FundDailyRepository fundDailyRepository;
    private StockDailyRepository stockDailyRepository;
    private TermDailyRepository termDailyRepository;

    private FundPurchaseRepository fundPurchaseRepository;
    private StockPurchaseRepository stockPurchaseRepository;
    private TermPurchaseRepository termPurchaseRepository;

    private AccountService accountService;
    private AccountRepository accountRepository;
    private IDateService iDateService;
    private ApplicationEventPublisher publisher;
    private BtsProperties btsProperties;

    private FinancialService financialService;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        fundRepository = new FundRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        stockRepository = new StockRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        termRepository = new TermRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        fundDailyRepository = new FundDailyRepositoryMock(RESOURCE_UTILS, JSON_UTILS);
        stockDailyRepository = new StockDailyRepositoryMock(RESOURCE_UTILS, JSON_UTILS);
        termDailyRepository = new TermDailyRepositoryMock(RESOURCE_UTILS, JSON_UTILS);
        fundPurchaseRepository = new FundPurchaseRepositoryMock(RESOURCE_UTILS, JSON_UTILS);
        stockPurchaseRepository = new StockPurchaseRepositoryMock(RESOURCE_UTILS, JSON_UTILS);
        termPurchaseRepository = new TermPurchaseRepositoryMock(RESOURCE_UTILS, JSON_UTILS);
        accountRepository = new AccountRepositoryMock(RESOURCE_UTILS, JSON_UTILS, null);
        accountService = new AccountService(accountRepository);
        publisher = Mockito.mock(ApplicationContext.class);
        btsProperties = new BtsProperties();
        iDateService = new SystemDateService(publisher, btsProperties);
        financialService = new FinancialService(fundRepository, stockRepository, termRepository,
                fundDailyRepository, stockDailyRepository, termDailyRepository,
                fundPurchaseRepository, stockPurchaseRepository, termPurchaseRepository,
                accountService, iDateService);

        ((AccountRepositoryMock) accountRepository).
                init("test-json/FinancialServiceTest/accounts.json");
        ((FundRepositoryMock) fundRepository).
                init("test-json/FinancialServiceTest/funds.json");
        ((StockRepositoryMock) stockRepository).
                init("test-json/FinancialServiceTest/stocks.json");
        ((TermRepositoryMock) termRepository).
                init("test-json/FinancialServiceTest/terms.json");
        ((FundDailyRepositoryMock) fundDailyRepository).
                init("test-json/FinancialServiceTest/fundDaily's.json");
        ((StockDailyRepositoryMock) stockDailyRepository).
                init("test-json/FinancialServiceTest/stockDaily's.json");
        ((TermDailyRepositoryMock) termDailyRepository).
                init("test-json/FinancialServiceTest/termDaily's.json");
        ((FundPurchaseRepositoryMock) fundPurchaseRepository).
                init("test-json/FinancialServiceTest/fundPurchase's.json");
        ((StockPurchaseRepositoryMock) stockPurchaseRepository).
                init("test-json/FinancialServiceTest/stockPurchase's.json");
        ((TermPurchaseRepositoryMock) termPurchaseRepository).
                init("test-json/FinancialServiceTest/termPurchase's.json");

        //    CustomerCode: AB2121202103281

    }

    @AfterEach
    void tearDown() {
        fundRepository = null;
        stockRepository = null;
        termRepository = null;
        fundDailyRepository = null;
        stockDailyRepository = null;
        termDailyRepository = null;
        fundPurchaseRepository = null;
        stockPurchaseRepository = null;
        termPurchaseRepository = null;

        accountService = null;
        accountRepository = null;
        iDateService = null;
        publisher = null;
        btsProperties = null;
    }

    @Test
    void predict() {
        assertEquals(0d,financialService.predict(null,null));
        Stock stock = stockRepository.findById(1);
        Fund fund = fundRepository.findById(1);
        Term term = termRepository.findById(1);
        financialService.dateService = setDate("2021-04-10");
        StockDaily stockDaily = stockDailyRepository.findAll().get(0);

        assertEquals(0d,financialService.predict(stock,null));
        assertEquals(stockDaily.getPrice()*0.98,financialService.predict(stock,stockDaily));

        assertEquals(0.98,financialService.predict(fund,null));
        assertEquals(0.98,financialService.predict(term,null));
        financialService.dateService = setDate("2021-04-09");
        assertEquals(stockDaily.getPrice()*1.06,financialService.predict(stock,stockDaily));
        assertEquals(1.06,financialService.predict(fund,null));
        assertEquals(1.06,financialService.predict(term,null));

    }

    @Test
    void queryProducts() {
        assertNotNull(financialService.queryProducts(Constant.FUND));
        assertNotNull(financialService.queryProducts(Constant.STOCK));
        assertNotNull(financialService.queryProducts(Constant.TERM));
        assertTrue(financialService.queryProducts(Constant.NO_PRODUCT).size()==0);
    }

    @Test
    void queryFundPurchases() {
        assertNotNull(financialService.queryFundPurchases("AB2121202104031"));
    }

    @Test
    void queryStockPurchases() {
        assertNotNull(financialService.queryStockPurchases("AB2121202103281"));
    }

    @Test
    void queryTermPurchases() {
        assertNotNull(financialService.queryTermPurchases("AB2121202103281"));
    }

    @Test
    void purchaseFund() {
        Period period = Period.ofDays(5);
        Account account = accountRepository.findByAccountNumAndPassword("6161710619136431439", "123456");
        assertEquals(HTTP_NOT_FOUND, financialService.purchaseFund(222L, account, 10, period).getCode());
        assertEquals(HTTP_NOT_ACCEPTABLE,financialService.purchaseFund(11L,account,20000,period).getCode());
        assertEquals(HTTP_OK,financialService.purchaseFund(11L,account,1,period).getCode());
    }

    @SneakyThrows
    @Test
    void purchaseStock() {
        Account account = accountRepository.findByAccountNumAndPassword("6161710619136431439", "123456");
        assertEquals(HTTP_NOT_FOUND,financialService.purchaseStock(222L,account,10).getCode());
        assertEquals(HTTP_NOT_FOUND,financialService.purchaseStock(1L,account,10).getCode());
        IDateService iDateService1 = setDate("2021-04-05");
        financialService.dateService = iDateService1;
        assertEquals(HTTP_NOT_ACCEPTABLE,financialService.purchaseStock(1L,account,10000).getCode());
        assertEquals(HTTP_OK,financialService.purchaseStock(1L,account,1).getCode());
    }


    @Test
    void purchaseTerm() {
        Period period = Period.ofDays(5);
        Account account = accountRepository.findByAccountNumAndPassword("6161710619136431439", "123456");
        assertEquals(HTTP_NOT_FOUND, financialService.purchaseTerm(222L, account, 10, period).getCode());
        assertEquals(HTTP_NOT_ACCEPTABLE,financialService.purchaseTerm(11L,account,20000,period).getCode());
        assertEquals(HTTP_OK,financialService.purchaseTerm(11L,account,10,period).getCode());
    }

    @SneakyThrows
    IDateService setDate(String date){
        ApplicationEventPublisher publisher1 = Mockito.mock(ApplicationContext.class);
        BtsProperties btsProperties1 = new BtsProperties();
        btsProperties1.setLaunchDate(date);
        IDateService iDateService1 = new SystemDateService(publisher1, btsProperties1);
        Method init = iDateService1.getClass().getDeclaredMethod("init");
        init.setAccessible(true);
        init.invoke(iDateService1);
        return iDateService1;
    }
}
