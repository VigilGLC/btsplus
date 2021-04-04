package fd.se.btsplus.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fd.se.btsplus.bts.http.IBtsHttpCaller;
import fd.se.btsplus.bts.model.domain.AccountDto;
import fd.se.btsplus.bts.model.domain.BtsAccountDatum;
import fd.se.btsplus.bts.model.domain.BtsLoan;
import fd.se.btsplus.bts.model.request.Param;
import fd.se.btsplus.bts.model.response.BtsLoanRes;
import fd.se.btsplus.bts.model.response.BtsQueryAccountRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomerService {

    private final IBtsHttpCaller caller;


    public int creditLevel(String customerCode) {
        BtsQueryAccountRes queryAccountRes = caller.queryAccount(Param.of("customerCode", customerCode));
        List<BtsAccountDatum> data = queryAccountRes.getData();
        int balance_count = 0;
        for (BtsAccountDatum accountDatum : data) {
            for (AccountDto accountDto : accountDatum.getAccountDtos()) {
                balance_count += accountDto.getBalance();
            }
        }
        String pageNum = "1";
        String pageSize = "100";
        ObjectMapper json = new ObjectMapper();
        HashMap<String, String> map = new HashMap<>();
        map.put("customerCode", customerCode);
        String params = null;
        try {
            params = json.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        BtsLoanRes loanRes = caller.loan(Param.of("pageNum", pageNum), Param.of("pageSize", pageSize), Param.of("params", params));
        int loan_count = 0;
        for (BtsLoan btsLoan : loanRes.getList()) {
            loan_count += Integer.parseInt(btsLoan.getBalance());
        }
        int count = balance_count - loan_count;
        if (count > 500000) return 1;
        else if (count >= 0) return 2;
        else return 3;
    }

    public long fineAmount(String customerCode) {
        return 0;
    }
}
