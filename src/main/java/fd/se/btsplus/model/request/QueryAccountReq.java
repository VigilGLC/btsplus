package fd.se.btsplus.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QueryAccountReq {
    @Schema(description = "customer code")
    private String customerCode;
    @Schema(description = "id number")
    private String IDNumber;
    @Schema(description = "account number")
    private String accountNum;
    @Schema(description = "password")
    private String password;
}
