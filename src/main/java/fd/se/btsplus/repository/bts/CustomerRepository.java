package fd.se.btsplus.repository.bts;

import fd.se.btsplus.model.entity.bts.Customer;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Profile("prod")
@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
}
