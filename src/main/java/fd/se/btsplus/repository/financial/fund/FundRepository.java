package fd.se.btsplus.repository.financial.fund;

import fd.se.btsplus.model.entity.financial.fund.Fund;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundRepository extends CrudRepository<Fund,Long> {
    List<Fund> findAll();
    boolean existsById(long prodId);
}
