package fd.se.btsplus.controller;

import fd.se.btsplus.bts.http.IBtsHttpCaller;
import fd.se.btsplus.bts.model.request.Param;
import fd.se.btsplus.bts.model.response.BtsQueryAccountRes;
import fd.se.btsplus.model.request.QueryAccountReq;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
public class QueryAccountController {
    private final IBtsHttpCaller caller;

    @Operation(method = "GET", tags = "QueryAccount", summary = "查询客户账户")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            content = @Content(schema = @Schema(implementation = QueryAccountReq.class)))
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = QueryAccountRes.class), mediaType = "application/json")
    })
    @Parameter(in = ParameterIn.HEADER,required = true, name = "login-token", schema = @Schema(type = "string"))
    @GetMapping("/account")
    public ResponseEntity<?> queryAccount(@Valid @RequestBody QueryAccountReq request) {
        BtsQueryAccountRes res = caller.queryAccount(Param.of("customerCode",request.getCustomerCode()),
                Param.of("IDNumber",request.getIDNumber()),
                Param.of("accountNum",request.getAccountNum()),
                Param.of("password",request.getPassword())
                );
        return ResponseEntity.
                status(res.getCode()).
                body(ResWrapper.wrap(res.getCode(), QueryAccountRes.from(res)));
    }
}
