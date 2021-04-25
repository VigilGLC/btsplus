package fd.se.btsplus.interceptor;

import fd.se.btsplus.interceptor.subject.Subject;
import fd.se.btsplus.model.consts.Constant;
import fd.se.btsplus.model.entity.bts.User;
import fd.se.btsplus.utils.HttpUtils;
import fd.se.btsplus.utils.TokenUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationInterceptorTest {
    private static final String PREFIX = "Bear";
    private TokenUtils tokenUtils;

    private AuthenticationInterceptor authenticationInterceptor;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object handler;

    @BeforeEach
    void setUp() {
        tokenUtils = mock(TokenUtils.class);
        HttpUtils httpUtils = mock(HttpUtils.class);
        Subject subject = mock(Subject.class);
        authenticationInterceptor = new AuthenticationInterceptor(tokenUtils, httpUtils, subject);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        handler = new Object();
    }

    void testTokenFails(String token) {
        when(request.getHeader(Constant.LOGIN_TOKEN_HEADER)).thenReturn(token);
        Assertions.assertFalse(authenticationInterceptor.preHandle(request, response, handler));
    }

    @Test
    void testTokenNull() {
        testTokenFails(null);
    }

    @Test
    void testTokenEmpty() {
        testTokenFails("");
    }

    @Test
    void testStartsNotWithPrefix() {
        testTokenFails("bdufnif");
    }

    @Test
    void testUserNull() {
        String token = "Bear fndsifhs";
        when(request.getHeader(Constant.LOGIN_TOKEN_HEADER)).thenReturn("Bear fndsifhs");
        when(tokenUtils.getUser(token.substring(PREFIX.length()).trim())).thenReturn(null);
        Assertions.assertFalse(authenticationInterceptor.preHandle(request, response, handler));
    }

    @Test
    void testUserExist() {
        User user = new User();
        String token = "Bear fndsifhs";
        when(request.getHeader(Constant.LOGIN_TOKEN_HEADER)).thenReturn("Bear fndsifhs");
        when(tokenUtils.getUser(token.substring(PREFIX.length()).trim())).thenReturn(user);
        Assertions.assertTrue(authenticationInterceptor.preHandle(request, response, handler));
    }
}