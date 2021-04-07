package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.repository.bts.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class TransactionRepositoryMockTest {
    private TransactionRepository transactionRepository;

    @Test
    void test() {
        transactionRepository.findAll();
    }

}


