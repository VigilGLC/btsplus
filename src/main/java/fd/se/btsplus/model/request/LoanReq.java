package fd.se.btsplus.model.request;

import lombok.Data;

@Data
public class LoanReq {
    private String pageNum;
    private String pageSize;
    private String params;
}
