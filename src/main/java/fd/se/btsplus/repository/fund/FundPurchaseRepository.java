package fd.se.btsplus.repository.fund;

import fd.se.btsplus.model.entity.fund.Fund;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundPurchaseRepository extends CrudRepository<Fund,Long> {
}
