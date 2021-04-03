package fd.se.btsplus.controller;

import fd.se.btsplus.bts.http.IBtsHttpCaller;
import fd.se.btsplus.bts.model.request.Param;
import fd.se.btsplus.bts.model.response.BtsCurrUserRes;
import fd.se.btsplus.bts.model.response.BtsLoginRes;
import fd.se.btsplus.bts.model.response.BtsQueryAccountRes;
import fd.se.btsplus.model.request.LoginReq;
import fd.se.btsplus.model.response.CurrUserRes;
import fd.se.btsplus.model.response.LoginRes;
import fd.se.btsplus.model.response.QueryAccountRes;
import fd.se.btsplus.model.response.ResWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class TestController {
    private final IBtsHttpCaller caller;


    //  查看当前用户"/current"
    @Operation(method = "GET", tags = "Current", summary = "当前用户")
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


    //  查看客户信息"/account"
    @Operation(method = "GET", tags = "QueryAccount", summary = "查询客户账户")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = QueryAccountRes.class), mediaType = "application/json")
    })
    @Parameter(in = ParameterIn.HEADER,required = true, name = "login-token", schema = @Schema(type = "string"))
    @GetMapping("/account")
    public ResponseEntity<?> queryAccount(@RequestParam(required = false) String customerCode, @RequestParam(required = false) String IDNumber, @RequestParam(required = false) String accountNum, @RequestParam(required = false)String password) {
        BtsQueryAccountRes res = caller.queryAccount(Param.of("customerCode",customerCode),
                Param.of("IDNumber",IDNumber),
                Param.of("accountNum",accountNum),
                Param.of("password",password)
        );
        return ResponseEntity.
                status(res.getCode()).
                body(ResWrapper.wrap(res.getCode(), QueryAccountRes.from(res)));
    }


}
