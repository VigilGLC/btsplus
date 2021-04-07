package fd.se.btsplus.repository.financial.fund;

import fd.se.btsplus.model.entity.financial.fund.FundPurchase;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("prod")
@Repository
public interface FundPurchaseRepository extends CrudRepository<FundPurchase, Long> {
    List<FundPurchase> findAll();
}
