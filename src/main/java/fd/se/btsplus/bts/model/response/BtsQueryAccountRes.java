package fd.se.btsplus.bts.model.response;

import fd.se.btsplus.bts.model.domain.BtsAccountDatum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class BtsQueryAccountRes extends BtsBaseRes {
    private int code;
    private long count;
    private List<BtsAccountDatum> data;
    private boolean flag;
    private String message;
}
