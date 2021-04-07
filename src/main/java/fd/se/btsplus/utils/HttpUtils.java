package fd.se.btsplus.utils;

import fd.se.btsplus.model.response.ResponseWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Component
public class HttpUtils {
    private final JsonUtils jsonUtils;

    public String readRequestBody(HttpServletRequest request) {
        try {
            return request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public void writeResponse(HttpServletResponse response, int httpCode, ResponseWrapper resp) {
        response.setStatus(httpCode);
        try {
            response.getWriter().write(jsonUtils.write(resp));
            response.flushBuffer();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
