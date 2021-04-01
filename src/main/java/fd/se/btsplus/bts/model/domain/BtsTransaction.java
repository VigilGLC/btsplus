package fd.se.btsplus.bts.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class BtsTransaction {
    private String id;
    private String operator;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String operatorTime;

    private String transactionCode;
    private String branchNum;
    private String transactionNum;
    private String account;
    private String reciprocalAccount;
    private String reciprocalName;
    private String amount;
    private String currency;
    private String balance;
    private String tellerName;
    private String branchName;
    private String transactionType;
    private String password;
    private String verifyType;
    private String depositId;
    private String idNumber;
}
