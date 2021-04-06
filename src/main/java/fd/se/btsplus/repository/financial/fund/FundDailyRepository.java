package fd.se.btsplus.repository.financial.fund;

import fd.se.btsplus.model.entity.financial.fund.FundDaily;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundDailyRepository extends CrudRepository<FundDaily,Long> {
}
