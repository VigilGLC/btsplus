package fd.se.btsplus.repository.financial.fund;

import fd.se.btsplus.model.entity.financial.fund.Fund;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Profile("prod")
@Repository
public interface FundPurchaseRepository extends CrudRepository<Fund,Long> {
}
