package fd.se.btsplus.controller;

import fd.se.btsplus.model.request.financing.FundPurchaseReq;
import fd.se.btsplus.model.request.financing.StockPurchaseReq;
import fd.se.btsplus.model.request.financing.TermPurchaseReq;
import fd.se.btsplus.service.CustomerService;
import fd.se.btsplus.service.FinancingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@AllArgsConstructor
@RestController
public class FinancingController {
    private final CustomerService customerService;
    private final FinancingService financingService;

    @Operation(method = "GET", tags = "Customer", summary = "查询客户信用等级")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(type = "integer", format = "int64"))
    })
    @Parameter(in = ParameterIn.HEADER, required = true, name = "login-token", schema = @Schema(type = "string"))
    @GetMapping("/customer/credit-level")
    public int creditLevel(@RequestParam String customerCode) {
        return customerService.creditLevel(customerCode);
    }

    @Operation(method = "GET", tags = "Customer", summary = "查询客户罚金数额")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(type = "integer", format = "int64"))
    })
    @Parameter(in = ParameterIn.HEADER, required = true, name = "login-token", schema = @Schema(type = "string"))
    @GetMapping("/customer/fine")
    public long fineAmount(@RequestParam String customerCode) {
        return customerService.fineAmount(customerCode);
    }

    @Operation(method = "GET", tags = "Financing", summary = "查询理财产品")
    @Parameter(in = ParameterIn.HEADER, required = true, name = "login-token", schema = @Schema(type = "string"))
    @GetMapping("/wmprods")
    public ResponseEntity<?> allProducts(@RequestParam String prodType) {
        throw new NotImplementedException();
    }

    @Operation(method = "POST", tags = "Financing", summary = "购买基金")
    @Parameter(in = ParameterIn.HEADER, required = true, name = "login-token", schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(type = "boolean"))
    })
    @PostMapping("/wmprods/fund")
    public boolean purchaseFund(@RequestBody FundPurchaseReq request) {
        financingService.purchaseFund(request);
        throw new NotImplementedException();
    }

    @Operation(method = "POST", tags = "Financing", summary = "购买定期理财")
    @Parameter(in = ParameterIn.HEADER, required = true, name = "login-token", schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(type = "boolean"))
    })
    @PostMapping("/wmprods/term")
    public boolean purchaseTerm(@RequestBody TermPurchaseReq request) {
        throw new NotImplementedException();
    }

    @Operation(method = "POST", tags = "Financing", summary = "购买股票")
    @Parameter(in = ParameterIn.HEADER, required = true, name = "login-token", schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(type = "boolean"))
    })
    @PostMapping("/wmprods/stock")
    public boolean purchaseStock(@RequestBody StockPurchaseReq request) {
        throw new NotImplementedException();
    }

}
