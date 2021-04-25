package fd.se.btsplus.interceptor;

import fd.se.btsplus.interceptor.subject.Subject;
import fd.se.btsplus.model.consts.CreditLevel;
import fd.se.btsplus.model.entity.bts.Customer;
import fd.se.btsplus.service.CustomerService;
import fd.se.btsplus.utils.HttpUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreditCheckInterceptorTest {
    private CreditCheckInterceptor creditCheckInterceptor;
    private Subject subject;
    private CustomerService customerService;
    private HttpUtils httpUtils;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object handler;

    @BeforeEach
    void setUp() {
        subject = mock(Subject.class);
        customerService = mock(CustomerService.class);
        httpUtils = mock(HttpUtils.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        creditCheckInterceptor = new CreditCheckInterceptor(subject, customerService, httpUtils);
    }

    @Test
    void testPreHandleCustomerNull() {
        when(subject.getCustomer()).thenReturn(null);
        Assertions.assertFalse(creditCheckInterceptor.preHandle(request, response, handler));
    }

    @Test
    void testPreHandleCreditLevelNull() {
        Customer customer = new Customer();
        when(subject.getCustomer()).thenReturn(customer);
        when(customerService.creditLevel(customer)).thenReturn(null);
        Assertions.assertFalse(creditCheckInterceptor.preHandle(request, response, handler));
    }

    @Test
    void testPreHandlerUrlNull() {
        Customer customer = new Customer();
        when(subject.getCustomer()).thenReturn(customer);
        when(request.getRequestURI()).thenReturn(null);
        when(customerService.creditLevel(customer)).thenReturn(CreditLevel.LEVEL_1);
        Assertions.assertFalse(creditCheckInterceptor.preHandle(request, response, handler));
    }

    @Test
    void testPreHandlerUrlAffordable() {
        Customer customer = new Customer();
        when(subject.getCustomer()).thenReturn(customer);
        when(customerService.creditLevel(customer)).thenReturn(CreditLevel.LEVEL_1);
        for(String product:CreditLevel.LEVEL_1.affordable){
            when(request.getRequestURI()).thenReturn("fund");
            Assertions.assertTrue(creditCheckInterceptor.preHandle(request, response, handler));
        }
    }


    @Test
    void testPreHandleUrlUnknownType() {
        Customer customer = new Customer();
        when(request.getRequestURI()).thenReturn("unknown");
        when(subject.getCustomer()).thenReturn(customer);
        when(customerService.creditLevel(customer)).thenReturn(CreditLevel.LEVEL_1);
        Assertions.assertFalse(creditCheckInterceptor.preHandle(request, response, handler));
    }

    @Test
    void testPreHandlerUrlUnAffordable() {
        Customer customer = new Customer();
        when(request.getRequestURI()).thenReturn("fund");
        when(customerService.creditLevel(customer)).thenReturn(CreditLevel.LEVEL_3);
        Assertions.assertFalse(creditCheckInterceptor.preHandle(request, response, handler));
    }
}