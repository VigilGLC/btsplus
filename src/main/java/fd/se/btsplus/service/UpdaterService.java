package fd.se.btsplus.service;

import fd.se.btsplus.model.consts.BillStatus;
import fd.se.btsplus.model.domain.Available;
import fd.se.btsplus.model.domain.DateEvent;
import fd.se.btsplus.model.entity.bts.Bill;
import fd.se.btsplus.model.entity.financial.fund.Fund;
import fd.se.btsplus.model.entity.financial.fund.FundDaily;
import fd.se.btsplus.model.entity.financial.fund.FundPurchase;
import fd.se.btsplus.model.entity.financial.stock.Stock;
import fd.se.btsplus.model.entity.financial.stock.StockDaily;
import fd.se.btsplus.model.entity.financial.stock.StockPurchase;
import fd.se.btsplus.model.entity.financial.term.Term;
import fd.se.btsplus.model.entity.financial.term.TermDaily;
import fd.se.btsplus.model.entity.financial.term.TermPurchase;
import fd.se.btsplus.repository.bts.BillRepository;
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
    private final Available available;
    private final FinancialService financialService;

    private final FundRepository fundRepository;
    private final StockRepository stockRepository;
    private final TermRepository termRepository;

    private final FundDailyRepository fundDailyRepository;
    private final StockDailyRepository stockDailyRepository;
    private final TermDailyRepository termDailyRepository;

    private final FundPurchaseRepository fundPurchaseRepository;
    private final StockPurchaseRepository stockPurchaseRepository;
    private final TermPurchaseRepository termPurchaseRepository;

    private final BillRepository billRepository;

    @Override
    public final void onApplicationEvent(DateEvent event) {
        Date lastDate = event.getLastDate();
        Date newDate = event.getNewDate();
        update(lastDate, newDate);
    }

    public void update(Date lastDate, Date newDate) {
        available.stop();

        updateFundDaily(lastDate, newDate);
        updateStockDaily(lastDate, newDate);
        updateTermDaily(lastDate, newDate);
        updateBills(lastDate, newDate);

        available.resume();
    }

    private void updateFundDaily(Date lastDate, Date newDate) {
        List<FundDaily> toSave = new ArrayList<>();
        fundRepository.findAll().forEach(fund -> {
            if (fundDailyRepository.findByFundAndDate(fund, newDate) == null) {
                FundDaily newDaily = new FundDaily();
                newDaily.setFund(fund);
                newDaily.setDate(newDate);
                newDaily.setRate(financialService.predict(fund, null));
                toSave.add(newDaily);
            }
        });
        fundDailyRepository.saveAll(toSave);
        List<FundPurchase> toSaveP = new ArrayList<>();
        fundDailyRepository.findAll().forEach(fundDaily -> {
            final Fund fund = fundDaily.getFund();
            final List<FundPurchase> fundPurchases = fundPurchaseRepository.
                    findByFundAndCurrDateAndEndDateAfter(fund, lastDate, newDate);
            for (FundPurchase purchase : fundPurchases) {
                purchase.setCurrDate(newDate);
                purchase.setCurrAmount(purchase.getCurrAmount() * fundDaily.getRate());
            }
            toSaveP.addAll(fundPurchases);
        });
        fundPurchaseRepository.saveAll(toSaveP);
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
        List<StockPurchase> toSaveP = new ArrayList<>();
        stockDailyRepository.findAll().forEach(stockDaily -> {
            final Stock stock = stockDaily.getStock();
            final List<StockPurchase> fundPurchases = stockPurchaseRepository.
                    findByStockAndCurrDate(stock, lastDate);
            for (StockPurchase purchase : fundPurchases) {
                purchase.setCurrDate(newDate);
                purchase.setCurrPrice(stockDaily.getPrice());
            }
            toSaveP.addAll(fundPurchases);
        });
        stockPurchaseRepository.saveAll(toSaveP);
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
        List<TermPurchase> toSaveP = new ArrayList<>();
        termDailyRepository.findAll().forEach(termDaily -> {
            final Term term = termDaily.getTerm();
            final List<TermPurchase> termPurchases = termPurchaseRepository.
                    findByTermAndCurrDateAndEndDateAfter(term, lastDate, newDate);
            for (TermPurchase purchase : termPurchases) {
                purchase.setCurrDate(newDate);
                purchase.setCurrAmount(purchase.getCurrAmount() * termDaily.getRate());
            }
            toSaveP.addAll(termPurchases);
        });
        termPurchaseRepository.saveAll(toSaveP);
    }

    private void updateBills(Date lastDate, Date newDate) {
        final List<Bill> bills = billRepository.
                findByEndDateAndStatus(lastDate, BillStatus.UNPAID_BEFORE);
        bills.forEach(bill -> bill.setStatus(BillStatus.UNPAID_PENALIZED));
        billRepository.saveAll(bills);
    }
}
