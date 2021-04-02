package fd.se.btsplus.bts.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class BtsQueryAccountRes extends BtsBaseRes {
    private int code;
    private long count;
    //    private BtsData data;
    private Object data;
    private boolean flag;
    private String message;

}
