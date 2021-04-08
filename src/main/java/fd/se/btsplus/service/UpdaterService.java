package fd.se.btsplus.service;

import fd.se.btsplus.model.domain.DateEvent;
import fd.se.btsplus.model.entity.financial.fund.FundDaily;
import fd.se.btsplus.model.entity.financial.stock.StockDaily;
import fd.se.btsplus.model.entity.financial.term.TermDaily;
import fd.se.btsplus.repository.financial.fund.FundDailyRepository;
import fd.se.btsplus.repository.financial.fund.FundRepository;
import fd.se.btsplus.repository.financial.stock.StockDailyRepository;
import fd.se.btsplus.repository.financial.stock.StockRepository;
import fd.se.btsplus.repository.financial.term.TermDailyRepository;
import fd.se.btsplus.repository.financial.term.TermRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UpdaterService implements ApplicationListener<DateEvent> {
    private final FinancialService financialService;
    private final FundRepository fundRepository;
    private final StockRepository stockRepository;
    private final TermRepository termRepository;
    private final FundDailyRepository fundDailyRepository;
    private final StockDailyRepository stockDailyRepository;
    private final TermDailyRepository termDailyRepository;

    @Override
    public final void onApplicationEvent(DateEvent event) {
        Date lastDate = event.getLastDate();
        Date newDate = event.getNewDate();
        update(lastDate, newDate);
    }

    public void update(Date lastDate, Date newDate) {
        updateFundDaily(lastDate, newDate);
        updateStockDaily(lastDate, newDate);
        updateTermDaily(lastDate, newDate);
    }

    private void updateFundDaily(Date lastDate, Date newDate) {
        List<FundDaily> toSave = new ArrayList<>();
        fundRepository.findAll().forEach(
                fund -> {
                    if (fundDailyRepository.findByFundAndDate(fund, newDate) == null) {
                        FundDaily newDaily = new FundDaily();
                        newDaily.setFund(fund);
                        newDaily.setDate(newDate);
                        newDaily.setRate(financialService.predict(fund, null));
                        toSave.add(newDaily);
                    }
                }
        );
        fundDailyRepository.saveAll(toSave);
    }

    private void updateStockDaily(Date lastDate, Date newDate) {
        List<StockDaily> toSave = new ArrayList<>();
        stockRepository.findAll().forEach(
                stock -> {
                    if (stockDailyRepository.findByStockAndDate(stock, newDate) == null) {
                        StockDaily newDaily = new StockDaily();
                        newDaily.setStock(stock);
                        newDaily.setDate(newDate);
                        StockDaily lastDaily = stockDailyRepository.findByStockAndDate(stock, lastDate);
                        if (lastDaily == null) {
                            lastDaily = new StockDaily();
                        }
                        newDaily.setPrice(financialService.predict(stock, lastDaily));
                        toSave.add(newDaily);
                    }
                }
        );
        stockDailyRepository.saveAll(toSave);
    }

    private void updateTermDaily(Date lastDate, Date newDate) {
        List<TermDaily> toSave = new ArrayList<>();
        termRepository.findAll().forEach(
                term -> {
                    if (termDailyRepository.findByTermAndDate(term, newDate) == null) {
                        TermDaily newDaily = new TermDaily();
                        newDaily.setTerm(term);
                        newDaily.setDate(newDate);
                        newDaily.setRate(financialService.predict(term, null));
                        toSave.add(newDaily);
                    }
                }
        );
        termDailyRepository.saveAll(toSave);
    }


}
