package fd.se.btsplus.controller;

import fd.se.btsplus.bts.http.IBtsHttpCaller;
import fd.se.btsplus.bts.model.request.Param;
import fd.se.btsplus.bts.model.response.BtsTransactionRes;
import fd.se.btsplus.model.response.ResWrapper;
import fd.se.btsplus.model.response.TransactionRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class TransactionController {
    private final IBtsHttpCaller caller;

    @Operation(method = "GET", tags = "Transaction", summary = "交易流水查询")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = TransactionRes.class), mediaType = "application/json")
    })
    @GetMapping("/transaction")
    public ResponseEntity<?> Transaction(@RequestParam String pageNum, @RequestParam String pageSize, @RequestParam String params) {
        BtsTransactionRes res = caller.transaction(Param.of("pageNum", pageNum),
                Param.of("pageSize", pageSize),
                Param.of("params", params));
        return ResponseEntity.
                status(res.getCode()).
                body(ResWrapper.wrap(res.getCode(), TransactionRes.from(res)));
    }
}
