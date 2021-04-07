package fd.se.btsplus.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountRequest {
    private String accountNum;
    private String password;
    private Double amount;
}
