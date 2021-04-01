package fd.se.btsplus.controller;

import fd.se.btsplus.bts.http.IBtsHttpCaller;
import fd.se.btsplus.bts.model.response.BtsTransactionRes;
import fd.se.btsplus.model.request.TransactionReq;
import fd.se.btsplus.model.response.TransactionRes;
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
public class TransactionController {
    private final IBtsHttpCaller caller;

    @Operation(method = "GET", tags = "Transaction", summary = "交易流水查询")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            content = @Content(schema = @Schema(implementation = TransactionReq.class)))
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = TransactionRes.class), mediaType = "application/json")
    })
    @GetMapping("/Transaction")
    public ResponseEntity<?> Transaction(@RequestBody TransactionReq request) {
        BtsTransactionRes res = caller.transaction(request.getPageNum(), request.getPageSize(), request.getParams());
        return ResponseEntity.
                status(res.getCode()).
                body(ResWrapper.wrap(res.getCode(), TransactionRes.from(res)));
    }
}