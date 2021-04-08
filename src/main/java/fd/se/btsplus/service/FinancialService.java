package fd.se.btsplus.service;

import fd.se.btsplus.model.entity.financial.IDaily;
import fd.se.btsplus.model.entity.financial.IProduct;
import fd.se.btsplus.model.entity.financial.fund.Fund;
import fd.se.btsplus.model.entity.financial.stock.Stock;
import fd.se.btsplus.model.entity.financial.stock.StockDaily;
import fd.se.btsplus.model.entity.financial.term.Term;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
@Service
public class FinancialService {

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


}
