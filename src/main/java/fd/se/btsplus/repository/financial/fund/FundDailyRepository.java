package fd.se.btsplus.repository.financial.fund;

import fd.se.btsplus.model.entity.financial.fund.Fund;
import fd.se.btsplus.model.entity.financial.fund.FundDaily;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Profile("prod")
@Repository
public interface FundDailyRepository extends CrudRepository<FundDaily, Long> {
    List<FundDaily> findAll();
    FundDaily findByFundAndDate(Fund fund, Date date);

}
