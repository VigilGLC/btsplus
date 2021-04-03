package fd.se.btsplus.bts.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fd.se.btsplus.auth.Subject;
import fd.se.btsplus.bts.exception.BtsForbiddenException;
import fd.se.btsplus.bts.exception.BtsUnauthorizedException;
import fd.se.btsplus.bts.exception.BtsUnknownException;
import fd.se.btsplus.bts.model.request.Param;
import fd.se.btsplus.bts.model.response.*;
import fd.se.btsplus.model.consts.Constant;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
        final HttpUrl url = buildUrl("/sys/login/restful");
        final RequestBody body = new FormBody.Builder().
                add("username", username).
                add("password", password).build();
        final Request request = buildRequest(HTTP_POST, url, body, false);
        return callBtsRequest(request, BtsLoginRes.class);
    }

    @Override
    public BtsCurrUserRes currUser() {
        final HttpUrl url = buildUrl("/users/current");
        final Request request = buildRequest(HTTP_GET,
                url, null, true);
        return callBtsRequest(request, BtsCurrUserRes.class);
    }

    @Override
    public BtsQueryAccountRes queryAccount(Param... params) {
        final HttpUrl url = buildUrl("/account", params);
        final Request request = buildRequest(HTTP_GET,
                url, null, true);
        return callBtsRequest(request, BtsQueryAccountRes.class);
    }

    @Override
    public BtsLoanRes loan(Param... params) {
        final HttpUrl url = buildUrl("/loan", params);
        final Request request = buildRequest(HTTP_GET,
                url, null, true);
        return callBtsRequest(request, BtsLoanRes.class);
    }

    @Override
    public BtsTransactionRes transaction(Param... params) {
        final HttpUrl url = buildUrl("/transaction", params);
        final Request request = buildRequest(HTTP_GET,
                url, null, true);
        return callBtsRequest(request, BtsTransactionRes.class);
    }


    //region okhttp request, response helpers.
    private final OkHttpClient client = new OkHttpClient();

    private HttpUrl buildUrl(String path, Param... params) {
        final HttpUrl.Builder builder = new HttpUrl.Builder()
                .scheme(IBtsHttpCaller.SCHEME)
                .host(IBtsHttpCaller.HOST)
                .port(IBtsHttpCaller.PORT)
                .addPathSegments(path);

        if (params != null) {
            for (Param param : params) {
                if (param.getQuery() != null && param.getValue() != null &&
                        !param.getQuery().isEmpty()) {
                    builder.addQueryParameter(param.getQuery(), param.getValue());
                }
            }
        }
        return builder.build();
    }

    @SneakyThrows
    private Request buildRequest(String method, HttpUrl url, RequestBody body, boolean withToken) {
        final Request.Builder builder = new Request.Builder();
        if (withToken) {
            builder.header(Constant.LOGIN_TOKEN_HEADER, subject.getLoginToken());
        }
        return builder.url(url).method(method, body).build();
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
