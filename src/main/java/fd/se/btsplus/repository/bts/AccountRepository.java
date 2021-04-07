package fd.se.btsplus.repository.bts;

import fd.se.btsplus.model.entity.bts.Account;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("prod")
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAll();
}
