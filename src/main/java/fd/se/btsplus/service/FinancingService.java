package fd.se.btsplus.service;

import fd.se.btsplus.model.request.financing.FundPurchaseReq;
import fd.se.btsplus.model.request.financing.StockPurchaseReq;
import fd.se.btsplus.model.request.financing.TermPurchaseReq;
import fd.se.btsplus.repository.fund.FundRepository;
import fd.se.btsplus.repository.stock.StockRepository;
import fd.se.btsplus.repository.term.TermRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@AllArgsConstructor
@Service
public class FinancingService {
    private FundRepository fundRepository;
    private StockRepository stockRepository;
    private TermRepository termRepository;

    public List<?> queryProducts(String prodType) {
        switch (prodType){
            case "fund":
                return fundRepository.findAll();
            case "stock":
                return stockRepository.findAll();
            case "term":
                return termRepository.findAll();
        }
        return null;
    }

    public boolean purchaseFund(@RequestBody FundPurchaseReq request) {
        throw new NotImplementedException();
    }

    public boolean purchaseTerm(@RequestBody TermPurchaseReq request) {
        throw new NotImplementedException();
    }

    public boolean purchaseStock(@RequestBody StockPurchaseReq request) {
        throw new NotImplementedException();
    }
}
