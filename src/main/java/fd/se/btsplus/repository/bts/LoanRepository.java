package fd.se.btsplus.repository.bts;

import fd.se.btsplus.model.entity.bts.Customer;
import fd.se.btsplus.model.entity.bts.Loan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Profile("prod")
@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findAll();

    List<Loan> findByCustomer(Customer customer);

    List<Loan> findByCustomerCode(String customerCode);

    List<Loan> findByCustomerIdNum(String idNum);
}
