package fd.se.btsplus.controller;

import fd.se.btsplus.bts.http.IBtsHttpCaller;
import fd.se.btsplus.bts.model.response.BtsLoanRes;
import fd.se.btsplus.model.request.LoanReq;
import fd.se.btsplus.model.response.LoanRes;
import fd.se.btsplus.model.response.ResWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
public class LoanController {
    private final IBtsHttpCaller caller;

    @Operation(method = "GET", tags = "Loan", summary = "贷款查询")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            content = @Content(schema = @Schema(implementation = LoanReq.class)))
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = LoanRes.class), mediaType = "application/json")
    })
    @GetMapping("/loan")
    public ResponseEntity<?> loan(@RequestBody LoanReq request) {
        BtsLoanRes res = caller.loan(request.getPageNum(), request.getPageSize(), request.getParams());
        return ResponseEntity.
                status(res.getCode()).
                body(ResWrapper.wrap(res.getCode(), LoanRes.from(res)));
    }
}
