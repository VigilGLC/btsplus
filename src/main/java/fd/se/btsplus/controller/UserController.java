package fd.se.btsplus.controller;

import fd.se.btsplus.model.request.LoginRequest;
import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.utils.OpenApiExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;

import static java.net.HttpURLConnection.HTTP_OK;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    @Operation(method = "POST", tags = "Account", summary = "用户登录")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.LoginResponseOk)))
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        final HashMap<String, Object> map = new HashMap<>();
        map.put("token", "d556485");
        map.put("expires", LocalDate.now().toString());
        return ResponseEntity.ok(ResponseWrapper.wrap(HTTP_OK, map));
    }

}
