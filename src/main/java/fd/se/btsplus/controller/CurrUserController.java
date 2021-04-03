package fd.se.btsplus.controller;

import fd.se.btsplus.bts.http.IBtsHttpCaller;
import fd.se.btsplus.bts.model.response.BtsCurrUserRes;
import fd.se.btsplus.model.request.CurrUserReq;
import fd.se.btsplus.model.response.CurrUserRes;
import fd.se.btsplus.model.response.ResWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CurrUserController {
    private final IBtsHttpCaller caller;

    @Operation(method = "GET", tags = "Current", summary = "当前用户")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(schema = @Schema(implementation = CurrUserReq.class)))
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CurrUserRes.class), mediaType = "application/json")
    })
    @Parameter(in = ParameterIn.HEADER,required = true, name = "login-token", schema = @Schema(type = "string"))
    @GetMapping("/users/current")
    public ResponseEntity<?> currUser(){
        BtsCurrUserRes res = caller.currUser();
        return ResponseEntity.
                status(res.getCode()).
                body(ResWrapper.wrap(res.getCode(), CurrUserRes.from(res)));
    }

}
