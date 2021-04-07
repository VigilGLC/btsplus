package fd.se.btsplus.auth;

import fd.se.btsplus.model.consts.Constant;
import fd.se.btsplus.model.entity.bts.User;
import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.utils.JsonUtils;
import fd.se.btsplus.utils.TokenUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

@Slf4j
@AllArgsConstructor
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static final String PREFIX = "Bear";
    private final TokenUtils tokenUtils;
    private final JsonUtils jsonUtils;
    private final Subject subject;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean succeed = false;
        String token = request.getHeader(Constant.LOGIN_TOKEN_HEADER);
        try {
            if (token == null || (token = token.trim()).isEmpty()) {
                return false;
            }
            if (!token.startsWith(PREFIX)) {
                return false;
            }
            token = token.substring(PREFIX.length()).trim();
            final User user = tokenUtils.getUser(token);
            if (user == null) {
                return false;
            }
            succeed = true;
            subject.setCurrUser(user);
            return true;
        } finally {
            if (!succeed) {
                response.setStatus(HTTP_UNAUTHORIZED);
                final ResponseWrapper resp = ResponseWrapper.
                        wrap(HTTP_UNAUTHORIZED, "Authentication Failed.", null);
                try {
                    response.getWriter().write(jsonUtils.write(resp));
                    response.flushBuffer();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}
