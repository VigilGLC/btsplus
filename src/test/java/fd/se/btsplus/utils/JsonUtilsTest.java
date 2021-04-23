package fd.se.btsplus.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

class JsonUtilsTest {

    private final JsonUtils jsonUtils = new JsonUtils();

    @BeforeEach
    void setUp() {

    }

    @Test
    void testWriteWithoutException() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String expected = "\"" + simpleDateFormat.format(date) + "\"";
        Assertions.assertEquals(expected, jsonUtils.write(date));
    }


}
