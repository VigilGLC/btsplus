package fd.se.btsplus.interceptor;

import fd.se.btsplus.interceptor.annotations.Authorized;
import fd.se.btsplus.interceptor.subject.Subject;
import fd.se.btsplus.model.entity.bts.User;
import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.utils.HttpUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

@AllArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final Subject subject;
    private final HttpUtils httpUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean succeed = true;
        try {
            if (!(handler instanceof HandlerMethod)) {
                return true;
            }
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Method method = handlerMethod.getMethod();
            Authorized annotation;
            if (null == (annotation = (method.getAnnotation(Authorized.class)))) {
                annotation = method.getDeclaringClass().getAnnotation(Authorized.class);
            }
            if (annotation == null) {
                return true;
            }

            final User user = subject.getCurrUser();
            if (user == null) {
                succeed = false;
                return false;
            }
            succeed = user.getRole().satisfy(annotation.required());
            return succeed;
        } finally {
            if (!succeed) {
                httpUtils.writeResponse(response, HTTP_UNAUTHORIZED,
                        ResponseWrapper.wrap(
                                HTTP_UNAUTHORIZED, "Authorization Failed.", null));
            }
        }
    }
}
