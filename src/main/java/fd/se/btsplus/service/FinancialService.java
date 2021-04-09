package fd.se.btsplus.service;

import fd.se.btsplus.model.domain.OperationResult;
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
import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.repository.financial.fund.FundPurchaseRepository;
import fd.se.btsplus.repository.financial.fund.FundRepository;
import fd.se.btsplus.repository.financial.stock.StockPurchaseRepository;
import fd.se.btsplus.repository.financial.stock.StockRepository;
import fd.se.btsplus.repository.financial.term.TermPurchaseRepository;
import fd.se.btsplus.repository.financial.term.TermRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.net.HttpURLConnection.*;

@AllArgsConstructor
@Service
public class FinancialService {
    private final FundRepository fundRepository;
    private final StockRepository stockRepository;
    private final TermRepository termRepository;

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

    public List<?> queryProducts(String prodType) {
        switch (prodType) {
            case "fund":
                return fundRepository.findAll();
            case "stock":
                return stockRepository.findAll();
            case "term":
                return termRepository.findAll();
        }
        return new ArrayList<>();
    }

    public List<FundPurchase> queryFundPurchases(String customerCode){
        return fundPurchaseRepository.findByCustomerCode(customerCode);
    }
    public List<StockPurchase> queryStockPurchases(String customerCode){
        return stockPurchaseRepository.findByCustomerCode(customerCode);
    }
    public List<TermPurchase> queryTermPurchases(String customerCode){
        return termPurchaseRepository.findByCustomerCode(customerCode);
    }

    public OperationResult purchaseFund(long fundId, Account account, double amount, Period period) {
        Fund fund = fundRepository.findById(fundId);
        if (fund == null) {
            return OperationResult.of(HTTP_NOT_FOUND, "product not found");
        } else {
            if (account.getBalance() < amount)
                return OperationResult.of(HTTP_NOT_ACCEPTABLE, "balance not enough");
            else {
                pay();
                FundPurchase fundPurchase = new FundPurchase();
                fundPurchaseRepository.save(fundPurchase);
                return OperationResult.of(HTTP_OK, "success");
            }
        }
    }


    public OperationResult purchaseStock(long stockId, Account account, int count) {
        Stock stock = stockRepository.findById(stockId);
        if (stock == null) {
            return OperationResult.of(HTTP_NOT_FOUND, "product not found");
        } else {
            if (account.getBalance() < count)
                return OperationResult.of(HTTP_NOT_ACCEPTABLE, "balance not enough");
            else {
                pay();
                StockPurchase stockPurchase = new StockPurchase();
                stockPurchaseRepository.save(stockPurchase);
                return OperationResult.of(HTTP_OK, "success");
            }
        }
    }



    public OperationResult purchaseTerm(long termId, Account account, double amount, Period period) {
        Term term = termRepository.findById(termId);
        if (term == null) {
            return OperationResult.of(HTTP_NOT_FOUND, "product not found");
        } else {
            if (account.getBalance() < amount)
                return OperationResult.of(HTTP_NOT_ACCEPTABLE, "balance not enough");
            else {
                pay();
                TermPurchase termPurchase = new TermPurchase();
                termPurchaseRepository.save(termPurchase);
                return OperationResult.of(HTTP_OK, "success");
            }
        }
    }

    public void pay() {


    }






}
