package fd.se.btsplus.repository.financial.stock;

import fd.se.btsplus.model.entity.financial.stock.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock,Long> {
    List<Stock> findAll();
}
