package fd.se.btsplus.model.request.financing;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TermPurchaseReq extends PurchaseReqBase{
    private String time;
}
