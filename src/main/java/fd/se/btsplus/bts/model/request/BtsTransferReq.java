package fd.se.btsplus.bts.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class BtsTransferReq {
    private String account;
    private String password;
    private String reciprocalAccount;
    private String amount;
    private String transactionCode;
    private String currency;
    private String operator;
}
