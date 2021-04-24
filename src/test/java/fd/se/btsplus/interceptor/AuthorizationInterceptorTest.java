package fd.se.btsplus.interceptor;

import fd.se.btsplus.interceptor.annotations.Authorized;
import fd.se.btsplus.interceptor.subject.Subject;
import fd.se.btsplus.model.consts.Role;
import fd.se.btsplus.model.entity.bts.User;
import fd.se.btsplus.utils.HttpUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthorizationInterceptorTest {
    private Subject subject;
    private HttpUtils httpUtils;
    private AuthorizationInterceptor authorizationInterceptor;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object handler;

    @BeforeEach
    void setUp() {
        subject = mock(Subject.class);
        httpUtils = mock(HttpUtils.class);
        authorizationInterceptor = new AuthorizationInterceptor(subject, httpUtils);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    void testHandlerNotHandlerMethod() {
        handler = new Object();
        Assertions.assertTrue(authorizationInterceptor.preHandle(request, response, handler));
    }

    @Test
    void testMethodAnnotationNull() throws NoSuchMethodException {
        Method method = Authorized.class.getDeclaredMethod("required");
        handler = new HandlerMethod("handlerMethod", method);
        authorizationInterceptor.preHandle(request, response, handler);
        Assertions.assertTrue(authorizationInterceptor.preHandle(request, response, handler));
    }

    @Test
    void testMethodAnnotationNotNull() throws NoSuchMethodException {
        Method method = TestClass.class.getDeclaredMethod("required");
        handler = new HandlerMethod("handlerMethod", method);
        authorizationInterceptor.preHandle(request, response, handler);
        Assertions.assertFalse(authorizationInterceptor.preHandle(request, response, handler));
    }

    @Test
    void testUserNotNull() throws NoSuchMethodException {
        Method method = TestClass.class.getDeclaredMethod("required");
        handler = new HandlerMethod("handlerMethod", method);
        authorizationInterceptor.preHandle(request, response, handler);
        User user = new User();
        user.setRole(Role.ADMIN);
        when(subject.getCurrUser()).thenReturn(user);
        Assertions.assertTrue(authorizationInterceptor.preHandle(request,response,handler));
    }
}


class TestClass {

    @Authorized(required = Role.ADMIN)
    Role required() {
        return Role.ADMIN;
    }
}
