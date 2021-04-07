package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.model.entity.bts.Loan;
import fd.se.btsplus.repository.bts.LoanRepository;
import fd.se.btsplus.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class LoanRepositoryMockTest {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private JsonUtils jsonUtils;

    @Test
    void test() {
        final Iterable<Loan> loans = loanRepository.findAll();
        for (Loan loan : loans) {
            System.out.println(jsonUtils.write(loan));
        }
    }
}
