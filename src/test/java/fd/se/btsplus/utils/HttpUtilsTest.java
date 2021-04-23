package fd.se.btsplus.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HttpUtilsTest {
    private HttpUtils httpUtils;

    @BeforeEach
    void setUp() {
        httpUtils = new HttpUtils(new JsonUtils());

    }

    @Test
    void testReadRequestBody(){

    }
}