package fd.se.btsplus.model.response;

import fd.se.btsplus.bts.model.domain.BtsLoan;
import fd.se.btsplus.bts.model.response.BtsLoanRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class LoanRes extends BaseRes{
    private List<BtsLoan> loans;
    public static LoanRes from(BtsLoanRes res) {
        return new LoanRes(res.getLoans());
    }

}
