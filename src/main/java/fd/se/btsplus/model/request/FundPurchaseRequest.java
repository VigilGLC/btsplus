package fd.se.btsplus.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;

@Data
@AllArgsConstructor
public class FundPurchaseRequest {
    private String accountNum;
    private String password;
    private Double amount;
    private Duration duration;
}
