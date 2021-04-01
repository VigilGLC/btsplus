package fd.se.btsplus.bts.http;

import fd.se.btsplus.bts.model.response.BtsCurrUserRes;
import fd.se.btsplus.bts.model.response.BtsLoanRes;
import fd.se.btsplus.bts.model.response.BtsLoginRes;

public interface IBtsHttpCaller {

    String BTS_URL = "http://10.176.122.171:8012";

    BtsLoginRes login(String username, String password);

    BtsCurrUserRes currUser();

    BtsLoanRes loan(String pageNum, String pageSize, String params);

}
