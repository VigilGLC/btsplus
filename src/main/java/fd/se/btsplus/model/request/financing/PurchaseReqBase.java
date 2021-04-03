package fd.se.btsplus.model.request.financing;

import lombok.Data;

@Data
public abstract class PurchaseReqBase {
    private int prodId;
    private long amount;
    private String customerCode;
}
