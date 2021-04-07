package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.model.entity.bts.Customer;
import fd.se.btsplus.repository.bts.CustomerRepository;
import fd.se.btsplus.utils.JSONUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomerRepositoryMockTest {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JSONUtils jsonUtils;

    @Test
    void test() {
        final Iterable<Customer> list = customerRepository.findAll();
        for (Customer customer : list) {
            System.out.println(jsonUtils.write(customer));
        }
    }
}
