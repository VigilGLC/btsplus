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
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@AllArgsConstructor
@Service
public class FinancingService {
    private final FundRepository fundRepository;
    private final StockRepository stockRepository;
    private final TermRepository termRepository;
    private final CustomerService customerService;

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
        // 查询产品是否存在
        if (!fundRepository.existsById(request.getProdId()))
            return false;
        // 查询信誉等级
        if (customerService.creditLevel(request.getCustomerCode()) > 1)
            return false;

        return false;
    }

//    public boolean purchaseTerm(@RequestBody TermPurchaseReq request) {
//        throw new NotImplementedException();
//    }
//
//    public boolean purchaseStock(@RequestBody StockPurchaseReq request) {
//        throw new NotImplementedException();
//    }
}
