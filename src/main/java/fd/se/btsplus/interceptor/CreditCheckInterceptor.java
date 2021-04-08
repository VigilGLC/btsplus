package fd.se.btsplus.interceptor;

import fd.se.btsplus.interceptor.subject.Subject;
import fd.se.btsplus.model.consts.CreditLevel;
import fd.se.btsplus.model.entity.bts.Customer;
import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.service.CustomerService;
import fd.se.btsplus.utils.HttpUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

import static fd.se.btsplus.model.consts.Constant.*;
import static java.net.HttpURLConnection.HTTP_NOT_ACCEPTABLE;

@AllArgsConstructor
@Component
public class CreditCheckInterceptor implements HandlerInterceptor {
    private final Subject subject;
    private final CustomerService customerService;
    private final HttpUtils httpUtils;

    private static String extractProduct(String url) {
        if (url == null) {
            return null;
        }
        final List<String> choices = Arrays.asList(FUND, STOCK, TERM);
        for (String part : choices) {
            if (url.contains(part)) {
                return part;
            }
        }
        return null;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final Customer customer = subject.getCustomer();
        boolean succeed = false;
        String message = "";
        try {
            if (customer == null) {
                message = "Customer not exists.";
                return false;
            }
            final String url = request.getRequestURI();
            final String product = extractProduct(url);
            final CreditLevel creditLevel = customerService.creditLevel(customer);
            if (creditLevel == null) {
                message = "Credit level not exist.";
                return false;
            }
            if (!creditLevel.affordable.contains(product)) {
                message = "Credit level not valid.";
                return false;
            }
            succeed = true;
            message = "Success";
            return true;
        } finally {
            if (!succeed) {
                httpUtils.writeResponse(response, HTTP_NOT_ACCEPTABLE,
                        ResponseWrapper.wrap(HTTP_NOT_ACCEPTABLE, message, null));
            }
        }
    }
}
