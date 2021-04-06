package fd.se.btsplus.repository.financial.term;

import fd.se.btsplus.model.entity.financial.term.Term;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TermRepository extends CrudRepository<Term, Long> {
    List<Term> findAll();
}
