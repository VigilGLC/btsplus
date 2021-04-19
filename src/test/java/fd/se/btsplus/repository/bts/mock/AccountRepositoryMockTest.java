package fd.se.btsplus.repository.bts.mock;

import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.ResourceUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountRepositoryMockTest {

    @Test
    void testInitPath() {
        AccountRepositoryMock mock =
                new AccountRepositoryMock(new ResourceUtils(), new JsonUtils(), null);
        mock.init("test-json/AccountRepositoryMockTest/accounts0.json");
        Assertions.assertNotEquals(0,mock.findAll().size());
    }
}
