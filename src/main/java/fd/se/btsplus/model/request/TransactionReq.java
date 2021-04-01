package fd.se.btsplus.model.request;

import lombok.Data;

@Data
public class TransactionReq {
    private String pageNum;
    private String pageSize;
    private String params;
}
