package fd.se.btsplus.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
public class LoanController {
//    private final IBtsHttpCaller caller;
//
//    @Operation(method = "GET", tags = "Loan", summary = "贷款查询")
//    @Parameter(in = ParameterIn.HEADER, required = true, name = "login-token", schema = @Schema(type = "string"))
//    @ApiResponse(responseCode = "200", content = {
//            @Content(schema = @Schema(implementation = LoanRes.class), mediaType = "application/json")
//    })
//    @GetMapping("/loan")
//    public ResponseEntity<?> loan(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam(required = false) String params) {
//        BtsTransactionRes res = caller.transaction(Param.of("pageNum", pageNum), Param.of("pageSize", pageSize), Param.of("params", params));
//        return ResponseEntity.
//                status(res.getCode()).
//                body(ResWrapper.wrap(res.getCode(), TransactionRes.from(res)));
//    }
}
