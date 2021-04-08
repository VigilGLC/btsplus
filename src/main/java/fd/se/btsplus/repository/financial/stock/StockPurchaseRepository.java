package fd.se.btsplus.repository.financial.stock;

import fd.se.btsplus.model.entity.financial.stock.Stock;
import fd.se.btsplus.model.entity.financial.stock.StockPurchase;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Profile("prod")
@Repository
public interface StockPurchaseRepository extends CrudRepository<StockPurchase, Long> {
    List<StockPurchase> findAll();

    List<StockPurchase> findByCustomerCode(String customerCode);

    List<StockPurchase> findByStockAndCurrDate(Stock stock, Date lastDate);
}
