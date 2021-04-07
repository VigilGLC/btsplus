package fd.se.btsplus.repository.financial.stock;

import fd.se.btsplus.model.entity.financial.stock.StockDaily;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Profile("prod")
@Repository
public interface StockDailyRepository extends CrudRepository<StockDaily,Long> {
}
