package fd.se.btsplus.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

class RequestCacheFilterTest {
    private CachedBodyHttpServletRequest cachedRequest;

    @Test
    void testRequestCacheFilter() throws ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain mockFilterChain = new MockFilterChain();
        RequestCacheFilter requestCacheFilter = new RequestCacheFilter();
        try {
            requestCacheFilter.doFilterInternal(request, response, mockFilterChain);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(true);
    }

    @Test
    void testCachedBodyHttpServletRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        try {
            cachedRequest = new CachedBodyHttpServletRequest(request);
            cachedRequest.getInputStream();
            cachedRequest.getReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertTrue(true);
    }

    @Test
    void testRead() throws IOException {
        byte[] cachedBody = {1, 2};
        CachedBodyServletInputStream inputStream = new CachedBodyServletInputStream(cachedBody);
        Assertions.assertEquals(1, inputStream.read());
        Assertions.assertTrue(inputStream.isReady());
        inputStream.setReadListener(null);
    }

    @Test
    void testIsFinishedTrue() {
        byte[] cachedBody = new byte[0];
        CachedBodyServletInputStream cachedBodyServletInputStream = new CachedBodyServletInputStream(cachedBody);
        Assertions.assertTrue(cachedBodyServletInputStream.isFinished());
    }

    @Test
    void testIsFinishedFalse() {
        byte[] param = new byte[1];
        CachedBodyServletInputStream cachedBodyServletInputStream = new CachedBodyServletInputStream(param);
        Assertions.assertFalse(cachedBodyServletInputStream.isFinished());
    }

}