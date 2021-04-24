package fd.se.btsplus.interceptor;

import fd.se.btsplus.interceptor.subject.Subject;
import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.model.request.AccountRequest;
import fd.se.btsplus.repository.bts.AccountRepository;
import fd.se.btsplus.utils.HttpUtils;
import fd.se.btsplus.utils.JsonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountCheckInterceptorTest {

    private HttpUtils httpUtils;
    private JsonUtils jsonUtils;
    private AccountRepository accountRepository;
    private AccountCheckInterceptor accountCheckInterceptor;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object handler;

    @BeforeEach
    void setUp() {
        Subject subject = mock(Subject.class);
        httpUtils = mock(HttpUtils.class);
        jsonUtils = mock(JsonUtils.class);
        accountRepository = mock(AccountRepository.class);
        accountCheckInterceptor = new AccountCheckInterceptor(subject, httpUtils, jsonUtils, accountRepository);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        handler = new Object();
    }

    void testRequestBody(String body) {
        when(httpUtils.readRequestBody(request)).thenReturn(body);
        Assertions.assertFalse(accountCheckInterceptor.preHandle(request, response, handler));
    }

    @Test
    void testBodyNull() {
        testRequestBody(null);
    }

    @Test
    void testBodyEmpty() {
        testRequestBody("");
    }

    @Test
    void testJsonReadError() {
        testRequestBody("121313");
    }

    @Test
    void testAccountNotExist() {
        String body = "{\"accountNumber\":\"6671710619136431438\",\"password\":\"123456\",\"amount\":20.1}";
        when(httpUtils.readRequestBody(request)).thenReturn(body);
        AccountRequest accountRequest = new AccountRequest("6671710619136431438", "123456", 20.1);
        Account account = new Account();
        when(jsonUtils.read(body, AccountRequest.class)).thenReturn(accountRequest);
        when(accountRepository.findByAccountNumAndPassword(accountRequest.getAccountNum(), accountRequest.getPassword())).thenReturn(null);
        Assertions.assertFalse(accountCheckInterceptor.preHandle(request, response, handler));
    }

    @Test
    void testAccountExist() {
        String body = "{\"accountNumber\":\"6671710619136431438\",\"password\":\"123456\",\"amount\":20.1}";
        when(httpUtils.readRequestBody(request)).thenReturn(body);
        AccountRequest accountRequest = new AccountRequest("6671710619136431438", "123456", 20.1);
        Account account = new Account();
        when(jsonUtils.read(body, AccountRequest.class)).thenReturn(accountRequest);
        when(accountRepository.findByAccountNumAndPassword(accountRequest.getAccountNum(), accountRequest.getPassword())).thenReturn(account);
        Assertions.assertTrue(accountCheckInterceptor.preHandle(request, response, handler));
    }
}