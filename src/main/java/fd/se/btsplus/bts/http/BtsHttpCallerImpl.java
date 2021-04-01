package fd.se.btsplus.bts.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fd.se.btsplus.auth.Subject;
import fd.se.btsplus.bts.exception.BtsForbiddenException;
import fd.se.btsplus.bts.exception.BtsUnauthorizedException;
import fd.se.btsplus.bts.exception.BtsUnknownException;
import fd.se.btsplus.bts.model.domain.BtsLoan;
import fd.se.btsplus.bts.model.response.BtsBaseRes;
import fd.se.btsplus.bts.model.response.BtsCurrUserRes;
import fd.se.btsplus.bts.model.response.BtsLoanRes;
import fd.se.btsplus.bts.model.response.BtsLoginRes;
import fd.se.btsplus.model.consts.Constant;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.URL;

import static fd.se.btsplus.model.consts.Constant.HTTP_GET;
import static fd.se.btsplus.model.consts.Constant.HTTP_POST;
import static java.net.HttpURLConnection.*;

@Profile("prod")
@Slf4j
@Service
@AllArgsConstructor
public final class BtsHttpCallerImpl implements IBtsHttpCaller {

    private final Subject subject;

    @Override
    public BtsLoginRes login(String username, String password) {
        final RequestBody body = new FormBody.Builder().
                add("username", username).
                add("password", password).build();
        final Request request = buildRequest(HTTP_POST,
                "/sys/login/restful", body, false);
        return callBtsRequest(request, BtsLoginRes.class);
    }

    @Override
    public BtsCurrUserRes currUser() {
        throw new NotImplementedException();
    }

    @Override
    public BtsLoanRes loan(String pageNum, String pageSize, String params){
        final RequestBody body = new FormBody.Builder().
                add("pageNum", pageNum).
                add("pageSize", pageSize).
                add("params", params).
                build();
        final Request request = buildRequest(HTTP_GET,
                "/loan", body, true);
        return callBtsRequest(request, BtsLoanRes.class);
    }


    @SneakyThrows
    private <T extends BtsBaseRes> T callBtsRequest(Request request, Class<T> clazz) {
        T res = clazz.newInstance();
        try (Response response = callRequest(request)) {
            if (response.code() == HTTP_OK) {
                if (response.body() != null) {
                    res = readJSON(response.body().string(), clazz);
                }
            }
            res.setCode(response.code());
        } catch (JsonProcessingException e) {
            log.error(String.format("JSON Read Error: %s", e.getMessage()), e);
        } catch (IOException e) {
            log.error(String.format("Unknown Error: %s", e.getMessage()), e);
        }
        return res;
    }


    //region okhttp request, response helpers.
    private final OkHttpClient client = new OkHttpClient();

    @SneakyThrows
    private Request buildRequest(String method, String path, RequestBody body, boolean withToken) {
        final Request.Builder builder = new Request.Builder();
        if (withToken) {
            builder.header(Constant.LOGIN_TOKEN_HEADER, subject.getLoginToken());
        }
        return builder.url(new URL(new URL(BTS_URL), path)).method(method, body).build();
    }

    private Response callRequest(Request request) {
        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new BtsUnknownException(e.getMessage(), e);
        }
        switch (response.code()) {
            case HTTP_UNAUTHORIZED:
                throw new BtsUnauthorizedException(String.format("BTS Service Unauthorized: %s", request.url()), null);
            case HTTP_FORBIDDEN:
                throw new BtsForbiddenException(String.format("BTS Service Forbidden: %s", request.url()), null);
            case HTTP_NOT_FOUND:
                throw new BtsForbiddenException(String.format("BTS Service Not Found: %s", request.url()), null);
        }
        return response;
    }
    //endregion

    //region JSON helpers.
    private static <T> T readJSON(String json, Class<T> clazz) throws JsonProcessingException {
        return MAPPER.readValue(json, clazz);
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerModule(new JavaTimeModule());
    }
    //endregion
}
