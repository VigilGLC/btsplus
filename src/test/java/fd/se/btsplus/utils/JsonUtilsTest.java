package fd.se.btsplus.utils;

import fd.se.btsplus.model.request.TermPurchaseRequest;
import org.junit.jupiter.api.Test;

class JsonUtilsTest {

    private final JsonUtils jsonUtils = new JsonUtils();

    @Test
    void test() {
        final TermPurchaseRequest req = jsonUtils.read("{\n" +
                "  \"accountNum\": \"6161710619136431439\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"amount\": 200,\n" +
                "  \"period\": \"1-2-1\"\n" +
                "}", TermPurchaseRequest.class);
    }
}
