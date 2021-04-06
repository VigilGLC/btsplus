package fd.se.btsplus.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AccountController {

//    @Operation(method = "POST", tags = "Account", summary = "用户登录")
//    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
//            content = @Content(schema = @Schema(implementation = LoginReq.class)))
//    @ApiResponse(responseCode = "200", content = {
//            @Content(schema = @Schema(implementation = LoginRes.class), mediaType = "application/json")
//    })
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginReq request) {
//        BtsLoginRes res = caller.login(request.getUsername(), request.getPassword());
//        return ResponseEntity.
//                status(res.getCode()).
//                body(ResWrapper.wrap(res.getCode(), LoginRes.from(res)));
//    }

}
