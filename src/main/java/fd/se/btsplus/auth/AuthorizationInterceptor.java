package fd.se.btsplus.auth;

import fd.se.btsplus.auth.annotations.Authorized;
import fd.se.btsplus.model.consts.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final Subject subject;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
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
        } else {
            final List<Role> roles = Arrays.stream(annotation.required()).collect(Collectors.toList());
            if (roles.contains(Role.ANY)) {
                return true;
            }
            return subject.getCurrUser().getRoles().containsAll(roles);
        }
    }
}
