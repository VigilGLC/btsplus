package fd.se.btsplus.controller;

import fd.se.btsplus.interceptor.subject.Subject;
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

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Autowired
    private UserController userController;
    private final LoginRequest loginRequest = new LoginRequest();
    private ResponseWrapper responseWrapper;
    @Autowired
    private Subject subject;

    @Test
    void loginWithSuccess() {
        responseWrapper = loginRes("JT2103253794", "imbus123");
        assertTrue(loginSuccess(responseWrapper));
    }
    @Test
    void loginWithFailure(){
        // blank username and password
        responseWrapper = loginRes("", "");
        assertFalse(loginSuccess(responseWrapper));

        // user not exist
        responseWrapper = loginRes("JT", "imbus123");
        assertFalse(loginSuccess(responseWrapper));

        // wrong password
        responseWrapper = loginRes("JT2103253794", "imbus");
        assertFalse(loginSuccess(responseWrapper));
    }
    @Test
    void currLoggedIn() {
        subject.setCurrUser(new User());
        responseWrapper = (ResponseWrapper)(userController.curr().getBody());
        assertNotNull(responseWrapper);
        assertNotNull(responseWrapper.getData());
    }
    // have not logged in
    @Test
    void currNotLoggedIn(){
        responseWrapper = (ResponseWrapper)(userController.curr().getBody());
        assertNotNull(responseWrapper);
        assertNull(responseWrapper.getData());
    }

    ResponseWrapper loginRes(String username, String password){
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        return (ResponseWrapper) (userController.login(loginRequest).getBody());
    }
    boolean loginSuccess(ResponseWrapper loinRes){
        return responseWrapper != null && responseWrapper.getCode() == HTTP_OK &&
                responseWrapper.getData() != null;
    }
}
