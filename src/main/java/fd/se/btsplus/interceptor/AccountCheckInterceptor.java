package fd.se.btsplus.interceptor;

import fd.se.btsplus.interceptor.subject.Subject;
import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.model.request.AccountRequest;
import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.repository.bts.AccountRepository;
import fd.se.btsplus.utils.HttpUtils;
import fd.se.btsplus.utils.JsonUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

@AllArgsConstructor
@Component
public class AccountCheckInterceptor implements HandlerInterceptor {
    private final Subject subject;

    private final HttpUtils httpUtils;
    private final JsonUtils jsonUtils;
    private final AccountRepository accountRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String body = httpUtils.readRequestBody(request);
        boolean succeed = false;
        String message = "";
        try {
            if (body == null || (body = body.trim()).isEmpty()) {
                message = "No request body read.";
                return false;
            }
            final AccountRequest req = jsonUtils.read(body, AccountRequest.class);
            if (req == null) {
                message = "Json read error.";
                return false;
            }
            final Account account = accountRepository.
                    findByAccountNumAndPassword(req.getAccountNum(), req.getPassword());
            if (account == null) {
                message = "Account not exist.";
                return false;
            }
            succeed = true;
            subject.setAccount(account);
            subject.setCustomer(account.getCustomer());
            return true;
        } finally {
            if (!succeed) {
                httpUtils.writeResponse(response, HTTP_BAD_REQUEST,
                        ResponseWrapper.wrap(HTTP_BAD_REQUEST, message, null));
            }
        }
    }
}
