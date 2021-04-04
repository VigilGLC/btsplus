package fd.se.btsplus.bts.model.response;

import fd.se.btsplus.bts.model.domain.BtsTransferDatum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class BtsTransferRes extends BtsBaseRes{
    private int code;
    private long count;
    private BtsTransferDatum data;
    private boolean flag;
    private String message;
}
