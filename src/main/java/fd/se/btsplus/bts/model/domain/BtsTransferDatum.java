package fd.se.btsplus.bts.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BtsTransferDatum {
    private String accountNum;
    private int accountType;
    private String accountTypeName;
    private String backup;
    private double balance;
    private String branchNum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;
    private int creator;
    private String customerCode;
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String interestTime;
    private int loanProduct;
    private String password;
    private double provisionBalance;
    private double provisionProduct;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String provisionTime;
    private int state;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String stateChangeTime;
    private String transactionCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String updateTime;
    private int updater;
    private String voucherName;
    private int voucherType;
}
