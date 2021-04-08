package fd.se.btsplus.controller;

import fd.se.btsplus.interceptor.subject.Subject;
import fd.se.btsplus.model.domain.LoginData;
import fd.se.btsplus.model.entity.bts.User;
import fd.se.btsplus.model.request.LoginRequest;
import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.repository.bts.UserRepository;
import fd.se.btsplus.utils.OpenApiExamples;
import fd.se.btsplus.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

import static fd.se.btsplus.model.consts.Constant.LOGIN_TOKEN_HEADER;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final Subject subject;
    private final TokenUtils tokenUtils;
    private final UserRepository userRepository;

    @Operation(method = "POST", tags = "User", summary = "用户登录")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.LoginRespOk)))
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        User user = userRepository.
                findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user == null) {
            return ResponseEntity.status(HTTP_NOT_FOUND).
                    body(ResponseWrapper.wrap(HTTP_NOT_FOUND, "User not found.", null));
        }
        String token = tokenUtils.generateToken(user);
        Date expireAt = tokenUtils.getExpireAt(token);
        return ResponseEntity.ok(ResponseWrapper.wrap(HTTP_OK, new LoginData(token, expireAt)));
    }

    @Operation(method = "GET", tags = "User", summary = "当前用户")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.CurrUserRespOk)))
    @GetMapping("/curr")
    public ResponseEntity<?> curr() {
        User user = subject.getCurrUser();
        return ResponseEntity.ok(ResponseWrapper.wrap(HTTP_OK, user));
    }
}
