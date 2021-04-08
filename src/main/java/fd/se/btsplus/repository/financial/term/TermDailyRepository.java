package fd.se.btsplus.repository.financial.term;

import fd.se.btsplus.model.entity.financial.term.Term;
import fd.se.btsplus.model.entity.financial.term.TermDaily;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Profile("prod")
@Repository
public interface TermDailyRepository extends CrudRepository<TermDaily, Long> {
    List<TermDaily> findAll();
    TermDaily findByTermAndDate(Term term, Date date);
}
