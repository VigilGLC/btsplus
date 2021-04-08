package fd.se.btsplus.controller;

import fd.se.btsplus.interceptor.annotations.Authorized;
import fd.se.btsplus.model.consts.Role;
import fd.se.btsplus.model.request.FundPurchaseRequest;
import fd.se.btsplus.model.request.StockPurchaseRequest;
import fd.se.btsplus.model.request.TermPurchaseRequest;
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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static fd.se.btsplus.model.consts.Constant.*;

@Authorized(required = Role.TELLER)
@AllArgsConstructor
@RestController
public class FinancialController {

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
        throw new NotImplementedException();
    }

    //<editor-fold desc="Fund">

    @Operation(method = HTTP_POST, tags = "Financial", summary = "购买基金")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.PurchaseRespOk)))
    @PostMapping("/financial/fund/{fundId}/purchase")
    public ResponseEntity<?> purchaseFund(@PathVariable Long fundId, @RequestBody FundPurchaseRequest request) {
        throw new NotImplementedException();
    }

    @Operation(method = HTTP_GET, tags = "Financial", summary = "查看基金")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.FundPurchasesRespOk)))
    @GetMapping("/customer/{customerCode}/financial/funds/purchases")
    public ResponseEntity<?> fundPurchases(@PathVariable String customerCode) {
        throw new NotImplementedException();
    }

    //</editor-fold>

    //<editor-fold desc="Stock">

    @Operation(method = HTTP_POST, tags = "Financial", summary = "购买股票")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.PurchaseRespOk)))
    @PostMapping("/financial/stock/{stockId}/purchase")
    public ResponseEntity<?> purchaseStock(@PathVariable Long stockId, @RequestBody StockPurchaseRequest request) {
        throw new NotImplementedException();
    }

    @Operation(method = HTTP_GET, tags = "Financial", summary = "查看股票")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.StockPurchasesRespOk)))
    @GetMapping("/customer/{customerCode}/financial/stocks/purchases")
    public ResponseEntity<?> stockPurchases(@PathVariable String customerCode) {
        throw new NotImplementedException();
    }

    //</editor-fold>

    //<editor-fold desc="Term">

    @Operation(method = HTTP_POST, tags = "Financial", summary = "购买定期理财")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.PurchaseRespOk)))
    @PostMapping("/financial/term/{termId}/purchase")
    public ResponseEntity<?> purchaseTerm(@PathVariable Long termId, @RequestBody TermPurchaseRequest request) {
        throw new NotImplementedException();
    }

    @Operation(method = HTTP_GET, tags = "Financial", summary = "查看定期理财")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.TermPurchasesRespOk)))
    @GetMapping("/customer/{customerCode}/financial/terms/purchases")
    public ResponseEntity<?> termPurchases(@PathVariable String customerCode) {
        throw new NotImplementedException();
    }
    //</editor-fold>
}
