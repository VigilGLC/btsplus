package fd.se.btsplus.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Period;

@Data
@AllArgsConstructor
public class TermPurchaseRequest {
    private String accountNum;
    private String password;
    private Double amount;
    private Period period;
}
