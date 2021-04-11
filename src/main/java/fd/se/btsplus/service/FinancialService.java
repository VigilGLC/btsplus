package fd.se.btsplus.service;

import fd.se.btsplus.model.consts.Constant;
import fd.se.btsplus.model.domain.OperationResult;
import fd.se.btsplus.model.domain.ProductDatum;
import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.model.entity.financial.IDaily;
import fd.se.btsplus.model.entity.financial.IProduct;
import fd.se.btsplus.model.entity.financial.fund.Fund;
import fd.se.btsplus.model.entity.financial.fund.FundPurchase;
import fd.se.btsplus.model.entity.financial.stock.Stock;
import fd.se.btsplus.model.entity.financial.stock.StockDaily;
import fd.se.btsplus.model.entity.financial.stock.StockPurchase;
import fd.se.btsplus.model.entity.financial.term.Term;
import fd.se.btsplus.model.entity.financial.term.TermPurchase;
import fd.se.btsplus.repository.financial.fund.FundDailyRepository;
import fd.se.btsplus.repository.financial.fund.FundPurchaseRepository;
import fd.se.btsplus.repository.financial.fund.FundRepository;
import fd.se.btsplus.repository.financial.stock.StockDailyRepository;
import fd.se.btsplus.repository.financial.stock.StockPurchaseRepository;
import fd.se.btsplus.repository.financial.stock.StockRepository;
import fd.se.btsplus.repository.financial.term.TermDailyRepository;
import fd.se.btsplus.repository.financial.term.TermPurchaseRepository;
import fd.se.btsplus.repository.financial.term.TermRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static fd.se.btsplus.service.IDateService.addDate;
import static java.net.HttpURLConnection.*;


@AllArgsConstructor
@Service
public class FinancialService {
    private final FundRepository fundRepository;
    private final StockRepository stockRepository;
    private final TermRepository termRepository;

    private final FundDailyRepository fundDailyRepository;
    private final StockDailyRepository stockDailyRepository;
    private final TermDailyRepository termDailyRepository;

    private final FundPurchaseRepository fundPurchaseRepository;
    private final StockPurchaseRepository stockPurchaseRepository;
    private final TermPurchaseRepository termPurchaseRepository;

    private final AccountService accountService;
    private final IDateService dateService;


    /**
     * @return new price for stock; new rate for fund and term.
     */
    public Double predict(IProduct product, IDaily daily) {
        Date date = dateService.currDate();
        Calendar calendar = DateUtils.toCalendar(date);

        if (product instanceof Stock && daily instanceof StockDaily) {
            StockDaily stockDaily = (StockDaily) daily;
            Double lastPrice = stockDaily.getPrice();
            if (lastPrice == null) {
                lastPrice = 120d;
            }
            if (calendar.get(Calendar.DAY_OF_WEEK) % 2 == 0) {
                return lastPrice * 1.06;
            } else {
                return lastPrice * 0.98;
            }
        }
        if (product instanceof Fund || product instanceof Term) {
            if (calendar.get(Calendar.DAY_OF_WEEK) % 2 == 0) {
                return 1.06;
            } else {
                return 0.98;
            }
        }

        return 0d;
    }

    public List<ProductDatum> queryProducts(String prodType) {
        final Date date = DateUtils.truncate(dateService.currDate(), Calendar.DAY_OF_MONTH);
        switch (prodType) {
            case Constant.FUND:
                return fundRepository.findAll().stream().map(fund -> new ProductDatum(fund,
                        fundDailyRepository.findByFundAndDate(fund, date), null)).
                        collect(Collectors.toList());
            case Constant.STOCK:
                return stockRepository.findAll().stream().map(stock -> new ProductDatum(stock,
                        stockDailyRepository.findByStockAndDate(stock, date), null)).
                        collect(Collectors.toList());
            case Constant.TERM:
                return termRepository.findAll().stream().map(term -> new ProductDatum(term,
                        termDailyRepository.findByTermAndDate(term, date), null)).
                        collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<ProductDatum> queryFundPurchases(String customerCode) {
        return fundPurchaseRepository.findByCustomerCode(customerCode).
                stream().map(fp -> new ProductDatum(fp.getFund(),
                fundDailyRepository.findByFundAndDate(fp.getFund(), fp.getCurrDate()), fp)).
                collect(Collectors.toList());
    }

    public List<ProductDatum> queryStockPurchases(String customerCode) {
        return stockPurchaseRepository.findByCustomerCode(customerCode).
                stream().map(fp -> new ProductDatum(fp.getStock(),
                stockDailyRepository.findByStockAndDate(fp.getStock(), fp.getCurrDate()), fp)).
                collect(Collectors.toList());
    }

    public List<ProductDatum> queryTermPurchases(String customerCode) {
        return termPurchaseRepository.findByCustomerCode(customerCode).
                stream().map(fp -> new ProductDatum(fp.getTerm(),
                termDailyRepository.findByTermAndDate(fp.getTerm(), fp.getCurrDate()), fp)).
                collect(Collectors.toList());
    }

    public OperationResult purchaseFund(Long fundId, Account account, double amount, Period period) {
        Fund fund = fundRepository.findById(fundId.longValue());
        if (fund == null) {
            return OperationResult.of(HTTP_NOT_FOUND, "product not found");
        }
        if (account.getBalance() < amount) {
            return OperationResult.of(HTTP_NOT_ACCEPTABLE, "balance not enough");
        }
        OperationResult result = pay(account, amount);
        if (result.getCode() != HTTP_OK) return result;

        //save fund purchase record
        FundPurchase fundPurchase = new FundPurchase();
        Date date = dateService.currDate();
        Date endDate = addDate(date, period);

        fundPurchase.setCustomer(account.getCustomer());
        fundPurchase.setFund(fund);
        fundPurchase.setInitAmount(amount);
        fundPurchase.setCurrAmount(amount);
        fundPurchase.setCurrDate(date);
        fundPurchase.setBeginDate(date);
        fundPurchase.setEndDate(endDate);

        fundPurchaseRepository.save(fundPurchase);
        //success
        return OperationResult.of(HTTP_OK, "success");
    }

    public OperationResult purchaseStock(Long stockId, Account account, int count) {
        Stock stock = stockRepository.findById(stockId.longValue());
        if (stock == null) {
            return OperationResult.of(HTTP_NOT_FOUND, "product not found");
        }
        Date date = dateService.currDate();
        StockDaily stockDaily = stockDailyRepository.findByStockAndDate(stock, date);
        if (stockDaily == null) {
            return OperationResult.of(HTTP_NOT_FOUND, "StockDaily not exists.");
        }
        double price = stockDaily.getPrice();
        if (account.getBalance() < count * price) {
            return OperationResult.of(HTTP_NOT_ACCEPTABLE, "balance not enough");
        }
        OperationResult result = pay(account, count * price);
        if (result.getCode() != HTTP_OK) return result;

        //save stock purchase record
        StockPurchase stockPurchase = new StockPurchase();

        stockPurchase.setCustomer(account.getCustomer());
        stockPurchase.setStock(stock);
        stockPurchase.setCount(count);
        stockPurchase.setInitPrice(price);
        stockPurchase.setCurrDate(date);
        stockPurchase.setCurrPrice(price);
        stockPurchase.setBeginDate(date);

        stockPurchaseRepository.save(stockPurchase);
        //success
        return OperationResult.of(HTTP_OK, "success");
    }

    public OperationResult purchaseTerm(Long termId, Account account, double amount, Period period) {
        Term term = termRepository.findById(termId.longValue());
        if (term == null) {
            return OperationResult.of(HTTP_NOT_FOUND, "product not found");
        }
        if (account.getBalance() < amount) {
            return OperationResult.of(HTTP_NOT_ACCEPTABLE, "balance not enough");
        }
        OperationResult result = pay(account, amount);
        if (result.getCode() != HTTP_OK) return result;

        //save term purchase record
        TermPurchase termPurchase = new TermPurchase();
        Date date = dateService.currDate();
        Date endDate = addDate(date, period);

        termPurchase.setCustomer(account.getCustomer());
        termPurchase.setTerm(term);
        termPurchase.setInitAmount(amount);
        termPurchase.setCurrAmount(amount);
        termPurchase.setCurrDate(date);
        termPurchase.setBeginDate(date);
        termPurchase.setEndDate(endDate);

        termPurchaseRepository.save(termPurchase);
        //success
        return OperationResult.of(HTTP_OK, "success");
    }

    private OperationResult pay(Account account, double amount) {
        return accountService.transfer(account, null, amount);
    }
}
