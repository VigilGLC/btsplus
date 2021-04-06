package fd.se.btsplus.repository.financial.term;

import fd.se.btsplus.model.entity.financial.term.TermPurchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermPurchaseRepository extends CrudRepository<TermPurchase,Long> {
}
