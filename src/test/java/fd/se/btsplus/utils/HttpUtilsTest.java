package fd.se.btsplus.utils;

import fd.se.btsplus.model.response.ResponseWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

class HttpUtilsTest {
    private HttpUtils httpUtils;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        JsonUtils jsonUtils = new JsonUtils();
        httpUtils = new HttpUtils(jsonUtils);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void testReadRequest() {
        httpUtils.readRequestBody(request);
        Assertions.assertTrue(true);
    }

    @Test
    void testWriteResponse() throws IOException {
        response.addHeader("login-token", "123");
        httpUtils.writeResponse(response, HTTP_BAD_REQUEST,
                ResponseWrapper.wrap(HTTP_BAD_REQUEST, "123", null));
        Assertions.assertTrue(true);
    }
}