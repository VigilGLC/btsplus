package fd.se.btsplus.controller;


import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.service.TransactionsService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static fd.se.btsplus.model.consts.Constant.HTTP_POST;
import static fd.se.btsplus.model.consts.Constant.LOGIN_TOKEN_HEADER;
import static java.net.HttpURLConnection.HTTP_OK;

@AllArgsConstructor
@RestController
public class TransactionController {

    private final TransactionsService transactionsService;

    @Operation(method = HTTP_POST, tags = "Transactions", summary = "流水查询")
    @Parameter(in = ParameterIn.HEADER, required = true, name = LOGIN_TOKEN_HEADER, schema = @Schema(type = "string"))
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.TransactionsRespOk)))
    @GetMapping("/transactions")
    public ResponseEntity<?> transactions(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String accountNum,
            @RequestParam(required = false) String transactionNum,
            @RequestParam(required = false) String transactionCode,
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) Date beginDate,
            @RequestParam(required = false) Date endDate

    ) {
        return ResponseEntity.ok(ResponseWrapper.wrap(HTTP_OK,
                transactionsService.query(pageNum, pageSize,
                        accountNum, transactionNum, transactionCode,
                        orderBy, beginDate, endDate)
        ));
    }
}
