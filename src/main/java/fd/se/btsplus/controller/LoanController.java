package fd.se.btsplus.controller;

import fd.se.btsplus.auth.annotations.Authorized;
import fd.se.btsplus.model.consts.Role;
import fd.se.btsplus.model.request.AccountRequest;
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
public class LoanController {

    @Operation(method = HTTP_GET, tags = "Loan", summary = "贷款查询")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.LoansRespOk)))
    @GetMapping("/customer/{customerCode}/loans")
    public ResponseEntity<?> loans(@PathVariable String customerCode) {
        throw new NotImplementedException();
    }

    @Operation(method = HTTP_GET, tags = "Loan", summary = "账单查询")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.BillsRespOk)))
    @GetMapping("/customer/loan/{iouNum}/bills")
    public ResponseEntity<?> bills(@PathVariable String iouNum) {
        throw new NotImplementedException();
    }

    @Operation(method = HTTP_PUT, tags = "Loan", summary = "偿还账单")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.PaymentRespOk)))
    @PutMapping("/customer/loan/bill/{billId}/payment")
    public ResponseEntity<?> payment(@PathVariable String billId, @RequestBody AccountRequest request) {
        throw new NotImplementedException();
    }

    @Authorized(required = Role.ADMIN)
    @Operation(method = HTTP_POST, tags = "Loan", summary = "自动还款")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.AutoPaymentRespOk)))
    @PostMapping("/loans/bills/auto-payment")
    public ResponseEntity<?> autoPayment() {
        throw new NotImplementedException();
    }

}
