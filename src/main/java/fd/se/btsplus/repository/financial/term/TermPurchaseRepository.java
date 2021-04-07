package fd.se.btsplus.repository.financial.term;

import fd.se.btsplus.model.entity.financial.term.TermPurchase;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("prod")
@Repository
public interface TermPurchaseRepository extends CrudRepository<TermPurchase, Long> {
    List<TermPurchase> findAll();
}
