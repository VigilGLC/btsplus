package fd.se.btsplus.repository.financial.stock;

import fd.se.btsplus.model.entity.financial.stock.Stock;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("prod")
@Repository
public interface StockRepository extends CrudRepository<Stock, Long> {
    List<Stock> findAll();

    Stock findById(long id);
}
