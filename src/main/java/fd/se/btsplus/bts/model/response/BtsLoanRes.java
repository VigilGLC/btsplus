package fd.se.btsplus.bts.model.response;

import fd.se.btsplus.bts.model.domain.BtsLoan;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class BtsLoanRes extends BtsBaseRes {
    private List<BtsLoan> loans;
}
