package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.model.entity.bts.Bill;
import fd.se.btsplus.repository.bts.BillRepository;
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
class BillRepositoryMockTest {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private JSONUtils jsonUtils;

    @Test
    void test() {
        final Iterable<Bill> bills = billRepository.findAll();
        for (Bill user : bills) {
            System.out.println(jsonUtils.write(user));
        }
    }
}
