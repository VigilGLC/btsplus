package fd.se.btsplus.model.response;

import fd.se.btsplus.bts.model.domain.BtsTransaction;
import fd.se.btsplus.bts.model.response.BtsTransactionRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class TransactionRes extends BaseRes{
    private List<BtsTransaction> transactions;
    public static TransactionRes from(BtsTransactionRes res) {
        return new TransactionRes(res.getTransactions());
    }

}
