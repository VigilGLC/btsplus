package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.model.entity.bts.Transaction;
import fd.se.btsplus.repository.bts.TransactionRepository;
import fd.se.btsplus.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class TransactionRepositoryMockTest {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private JsonUtils jsonUtils;

    @Test
    void test() {
        final List<Transaction> list = transactionRepository.findAll();
        for (Transaction tx : list) {
            System.out.println(jsonUtils.write(tx));
        }
    }

}


