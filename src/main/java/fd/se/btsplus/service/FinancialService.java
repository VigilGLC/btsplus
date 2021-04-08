package fd.se.btsplus.service;

import fd.se.btsplus.model.domain.OperationResult;
import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.model.entity.financial.IDaily;
import fd.se.btsplus.model.entity.financial.IProduct;
import fd.se.btsplus.model.entity.financial.fund.Fund;
import fd.se.btsplus.model.entity.financial.stock.Stock;
import fd.se.btsplus.model.entity.financial.stock.StockDaily;
import fd.se.btsplus.model.entity.financial.term.Term;
import fd.se.btsplus.repository.financial.fund.FundRepository;
import fd.se.btsplus.repository.financial.stock.StockRepository;
import fd.se.btsplus.repository.financial.term.TermRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class FinancialService {
    private final FundRepository fundRepository;
    private final StockRepository stockRepository;
    private final TermRepository termRepository;

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

    public List<IProduct> queryProducts(String prodType) {
        throw new NotImplementedException();
    }

    public OperationResult purchaseFund(Long fundId, Account account, double amount, Period period) {
        throw new NotImplementedException();
    }

    public OperationResult purchaseStock(Long stockId, Account account, int count) {
        throw new NotImplementedException();
    }

    public OperationResult purchaseTerm(Long termId, Account account, double amount, Period period) {
        throw new NotImplementedException();
    }

}
