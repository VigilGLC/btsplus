package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.repository.bts.TransactionRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@AllArgsConstructor
@SpringBootTest
class TransactionRepositoryMockTest {

    @Autowired
    private final TransactionRepository transactionRepository;



    @Test
    void test(){
        transactionRepository.findAll();
    }

}


