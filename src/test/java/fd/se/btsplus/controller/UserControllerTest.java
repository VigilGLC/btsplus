package fd.se.btsplus.controller;

import fd.se.btsplus.interceptor.subject.Subject;
import fd.se.btsplus.model.domain.LoginData;
import fd.se.btsplus.model.entity.bts.User;
import fd.se.btsplus.model.request.LoginRequest;
import fd.se.btsplus.model.response.ResponseWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;
    private LoginRequest loginRequest = new LoginRequest();
    private ResponseWrapper responseWrapper;
    @Autowired
    private Subject subject;
    // login successfully
    @Test
    void login() {
        loginRequest.setUsername("JT2103253794");
        loginRequest.setPassword("imbus123");
        responseWrapper = (ResponseWrapper) (userController.login(loginRequest).getBody());
        assertEquals(HTTP_OK, responseWrapper.getCode());
        assertNotNull(responseWrapper.getData());
    }
    @Test
    void loginWithUserNotExisted(){
        loginRequest.setUsername("JT");
        loginRequest.setPassword("imbus123");
        responseWrapper = (ResponseWrapper) (userController.login(loginRequest).getBody());
        assertEquals(HTTP_NOT_FOUND, responseWrapper.getCode());
        assertNull(responseWrapper.getData());
    }
    @Test
    void loginWithWrongPassword(){
        loginRequest.setUsername("JT2103253794");
        loginRequest.setPassword("imbus");
        responseWrapper = (ResponseWrapper) (userController.login(loginRequest).getBody());
        assertEquals(HTTP_NOT_FOUND, responseWrapper.getCode());
        assertNull(responseWrapper.getData());
    }
    @Test
    void loginWithBlank(){
        loginRequest.setUsername("");
        loginRequest.setPassword("");
        responseWrapper = (ResponseWrapper) (userController.login(loginRequest).getBody());
        assertEquals(HTTP_NOT_FOUND, responseWrapper.getCode());
        assertNull(responseWrapper.getData());
    }
    // have logged in
    @Test
    void curr() {
        subject.setCurrUser(new User());
        responseWrapper = (ResponseWrapper)(userController.curr().getBody());
        assertNotNull(responseWrapper.getData());
    }
    // have not logged in
    @Test
    void currNotLoggedIn(){
        responseWrapper = (ResponseWrapper)(userController.curr().getBody());
        assertNull(responseWrapper.getData());
    }
}
