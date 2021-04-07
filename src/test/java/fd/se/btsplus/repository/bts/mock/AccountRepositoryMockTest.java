package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.repository.bts.AccountRepository;
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
class AccountRepositoryMockTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JsonUtils jsonUtils;

    @Test
    void test() {
        final Iterable<Account> list = accountRepository.findAll();
        for (Account account : list) {
            System.out.println(jsonUtils.write(account));
        }
    }
}
