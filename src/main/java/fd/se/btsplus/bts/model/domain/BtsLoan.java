package fd.se.btsplus.bts.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BtsLoan {
    private String iouNum;
    private String customerCode;
    private String customerName;
    private String productCode;
    private String productName;
    private String totalAmount;
    private String totalInterest;
    private String balance;
    private String overdueBalance;
    private String accountStatus;
    private String loanStatus;
    private String loanSettleStatus;
    private String loanCancelStatus;
    private String loanDate;
    private String dueDate;
    private String repaymentMethod;
    private String creator;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;
}
