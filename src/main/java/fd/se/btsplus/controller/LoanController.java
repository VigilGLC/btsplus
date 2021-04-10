package fd.se.btsplus.controller;

import fd.se.btsplus.interceptor.annotations.Authorized;
import fd.se.btsplus.interceptor.subject.Subject;
import fd.se.btsplus.model.consts.Role;
import fd.se.btsplus.model.domain.OperationResult;
import fd.se.btsplus.model.request.AccountRequest;
import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.service.BillService;
import fd.se.btsplus.service.CustomerService;
import fd.se.btsplus.service.LoanService;
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
public class LoanController {
    private final Subject subject;
    private final CustomerService customerService;
    private final LoanService loanService;
    private final BillService billService;

    @Operation(method = HTTP_GET, tags = "Loan", summary = "贷款查询")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.LoansRespOk)))
    @GetMapping("/customer/{idNum}/loans")
    public ResponseEntity<?> loans(@PathVariable String idNum) {
        return ResponseEntity.ok(ResponseWrapper.wrap(HTTP_OK, loanService.queryByIdNum(idNum)));
    }

    @Operation(method = HTTP_GET, tags = "Loan", summary = "账单查询")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.BillsRespOk)))
    @GetMapping("/customer/loan/{iouNum}/bills")
    public ResponseEntity<?> bills(@PathVariable String iouNum) {
        return ResponseEntity.ok(ResponseWrapper.wrap(HTTP_OK, billService.query(iouNum)));
    }

    @Operation(method = HTTP_PUT, tags = "Loan", summary = "偿还账单")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.PaymentRespOk)))
    @PutMapping("/customer/loan/bill/{billId}/payment")
    public ResponseEntity<?> payment(@PathVariable Long billId, @RequestBody AccountRequest request) {
        final OperationResult result = customerService.payBill(billId,
                subject.getAccount(), request.getAmount());
        final int code = result.getCode();
        final String message = result.getMessage();
        Boolean data = code == HTTP_OK;
        return ResponseEntity.status(code).body(ResponseWrapper.wrap(code, message, data));
    }

    @Authorized(required = Role.ADMIN)
    @Operation(method = HTTP_POST, tags = "Loan", summary = "自动还款")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.AutoPaymentRespOk)))
    @PostMapping("/loans/bills/auto-payment")
    public ResponseEntity<?> autoPayment() {
        final OperationResult result = customerService.autoPayBill();
        final int code = result.getCode();
        final String message = result.getMessage();
        Boolean data = code == HTTP_OK;
        return ResponseEntity.status(code).body(ResponseWrapper.wrap(code, message, data));
    }

}
