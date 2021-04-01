package fd.se.btsplus.bts.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BtsTransactionReq {
    private String branchNum;
    private String account;
    private String transactionNum;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String beginDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String endDate;

    private String transactionCode;
}
