package fd.se.btsplus.model.consts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static fd.se.btsplus.model.consts.CreditLevel.*;

class CreditLevelTest {

    @Test
    void testLevel1() {
        Assertions.assertEquals(LEVEL_1, CreditLevel.evaluate(50_0001.0, 0.1));
        Assertions.assertEquals(LEVEL_1, CreditLevel.evaluate(50_0001.0, 0.9));
        Assertions.assertNotEquals(LEVEL_1, CreditLevel.evaluate(50_0001.0, 1.001));
        Assertions.assertNotEquals(LEVEL_1, CreditLevel.evaluate(49_000.0, 100.0));
    }

    @Test
    void testLevel2() {
        Assertions.assertEquals(LEVEL_2, CreditLevel.evaluate(50_0001.0, 50_002.0));
        Assertions.assertNotEquals(LEVEL_2, CreditLevel.evaluate(1.0, 20.0));
        Assertions.assertEquals(LEVEL_2, CreditLevel.evaluate(100.1, 100.099));
        Assertions.assertNotEquals(LEVEL_2, CreditLevel.evaluate(0.99, 1.01));
    }

    @Test
    void testLevel3() {
        Assertions.assertEquals(LEVEL_3, CreditLevel.evaluate(0d, 0.1d));
        Assertions.assertNotEquals(LEVEL_3, CreditLevel.evaluate(50d, 49.9d));
    }
}
