package fd.se.btsplus.repository.financial.stock;

import fd.se.btsplus.model.entity.financial.stock.StockPurchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPurchaseRepository extends CrudRepository<StockPurchase,Long> {
}
