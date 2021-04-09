package fd.se.btsplus.controller;

import fd.se.btsplus.interceptor.annotations.Authorized;
import fd.se.btsplus.interceptor.subject.Subject;
import fd.se.btsplus.model.consts.Role;
import fd.se.btsplus.model.domain.OperationResult;
import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.model.request.FundPurchaseRequest;
import fd.se.btsplus.model.request.StockPurchaseRequest;
import fd.se.btsplus.model.request.TermPurchaseRequest;
import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.service.FinancialService;
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

import static fd.se.btsplus.model.consts.Constant.*;
import static java.net.HttpURLConnection.HTTP_OK;

@Authorized(required = Role.TELLER)
@AllArgsConstructor
@RestController
public class FinancialController {
    private final Subject subject;

    private final FinancialService financialService;

    @Operation(method = HTTP_GET, tags = "Financial", summary = "理财产品")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = {
                    @ExampleObject(value = OpenApiExamples.StocksRespOk),
                    @ExampleObject(value = OpenApiExamples.FundsRespOk),
                    @ExampleObject(value = OpenApiExamples.TermsRespOk)
            }))
    @GetMapping("/financial/{prodType}")
    public ResponseEntity<?> products(@PathVariable String prodType) {
        return ResponseEntity.ok(ResponseWrapper.wrap(HTTP_OK, financialService.queryProducts(prodType)));
    }

    //<editor-fold desc="Fund">

    @Operation(method = HTTP_POST, tags = "Financial", summary = "购买基金")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.PurchaseRespOk)))
    @PostMapping("/financial/fund/{fundId}/purchase")
    public ResponseEntity<?> purchaseFund(@PathVariable long fundId, @RequestBody FundPurchaseRequest request) {
        Account account = subject.getAccount();
        OperationResult result = financialService.purchaseFund(fundId, account, request.getAmount(),
                request.getPeriod());
        final int code = result.getCode();
        Boolean data = code == HTTP_OK;
        return ResponseEntity.status(code).body(data);
    }

    @Operation(method = HTTP_GET, tags = "Financial", summary = "查看基金")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.FundPurchasesRespOk)))
    @GetMapping("/customer/{customerCode}/financial/funds/purchases")
    public ResponseEntity<?> fundPurchases(@PathVariable String customerCode) {
        return ResponseEntity.ok(ResponseWrapper.wrap(HTTP_OK, financialService.queryFundPurchases(customerCode)));
    }

    //</editor-fold>

    //<editor-fold desc="Stock">

    @Operation(method = HTTP_POST, tags = "Financial", summary = "购买股票")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.PurchaseRespOk)))
    @PostMapping("/financial/stock/{stockId}/purchase")
    public ResponseEntity<?> purchaseStock(@PathVariable long stockId, @RequestBody StockPurchaseRequest request) {
        Account account = subject.getAccount();
        OperationResult result = financialService.purchaseStock(stockId, account, request.getCount());
        final int code = result.getCode();
        Boolean data = code == HTTP_OK;
        return ResponseEntity.status(code).body(data);

    }

    @Operation(method = HTTP_GET, tags = "Financial", summary = "查看股票")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.StockPurchasesRespOk)))
    @GetMapping("/customer/{customerCode}/financial/stocks/purchases")
    public ResponseEntity<?> stockPurchases(@PathVariable String customerCode) {
        return ResponseEntity.ok(ResponseWrapper.wrap(HTTP_OK, financialService.queryStockPurchases(customerCode)));
    }

    //</editor-fold>

    //<editor-fold desc="Term">

    @Operation(method = HTTP_POST, tags = "Financial", summary = "购买定期理财")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.PurchaseRespOk)))
    @PostMapping("/financial/term/{termId}/purchase")
    public ResponseEntity<?> purchaseTerm(@PathVariable long termId, @RequestBody TermPurchaseRequest request) {
        Account account = subject.getAccount();
        OperationResult result = financialService.purchaseTerm(termId, account, request.getAmount(),
                request.getPeriod());
        final int code = result.getCode();
        Boolean data = code == HTTP_OK;
        return ResponseEntity.status(code).body(data);
    }

    @Operation(method = HTTP_GET, tags = "Financial", summary = "查看定期理财")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.TermPurchasesRespOk)))
    @GetMapping("/customer/{customerCode}/financial/terms/purchases")
    public ResponseEntity<?> termPurchases(@PathVariable String customerCode) {
        return ResponseEntity.ok(ResponseWrapper.wrap(HTTP_OK, financialService.queryTermPurchases(customerCode)));
    }
    //</editor-fold>
}
