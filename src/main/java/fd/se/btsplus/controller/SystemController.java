package fd.se.btsplus.controller;


import fd.se.btsplus.model.response.ResponseWrapper;
import fd.se.btsplus.service.IDateService;
import fd.se.btsplus.utils.OpenApiExamples;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fd.se.btsplus.model.consts.Constant.HTTP_GET;
import static java.net.HttpURLConnection.HTTP_OK;

@AllArgsConstructor
@RestController
@RequestMapping("/system")
public class SystemController {
    private final IDateService dateService;

    @Operation(method = HTTP_GET, tags = "System", summary = "获取时间")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
            examples = @ExampleObject(value = OpenApiExamples.SYSTEM_TIME_RES)))
    @GetMapping("/time")
    public ResponseEntity<?> time() {
        return ResponseEntity.ok(ResponseWrapper.wrap(HTTP_OK, dateService.currDate()));
    }

}
