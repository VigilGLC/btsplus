package fd.se.btsplus.service;

import fd.se.btsplus.model.request.financing.FundPurchaseReq;
import fd.se.btsplus.model.request.financing.StockPurchaseReq;
import fd.se.btsplus.model.request.financing.TermPurchaseReq;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@AllArgsConstructor
@Service
public class FinancingService {

    public List<?> queryProducts(String prodType) {
        throw new NotImplementedException();
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
