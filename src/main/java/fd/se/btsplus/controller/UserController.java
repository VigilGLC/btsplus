package fd.se.btsplus.controller;

import fd.se.btsplus.model.request.LoginRequest;
import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.utils.OpenApiExamples;
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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;

import static fd.se.btsplus.model.consts.Constant.LOGIN_TOKEN_HEADER;
import static java.net.HttpURLConnection.HTTP_OK;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @Operation(method = "POST", tags = "User", summary = "用户登录")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.LoginRespOk)))
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        final HashMap<String, Object> map = new HashMap<>();
        map.put("token", "d556485");
        map.put("expires", LocalDate.now().toString());
        return ResponseEntity.ok(ResponseWrapper.wrap(HTTP_OK, map));
    }

    @Operation(method = "GET", tags = "User", summary = "当前用户")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.CurrUserRespOk)))
    @GetMapping("/curr")
    public ResponseEntity<?> curr() {
        throw new NotImplementedException();
    }
}
