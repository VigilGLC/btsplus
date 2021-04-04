package fd.se.btsplus.bts.http;

import fd.se.btsplus.bts.model.request.Param;
import fd.se.btsplus.bts.model.response.*;

public interface IBtsHttpCaller {

    String SCHEME = "http";
    String HOST = "10.176.122.171";
    int PORT = 8012;

    BtsLoginRes login(String username, String password);

    BtsCurrUserRes currUser();

    BtsQueryAccountRes queryAccount(Param... params);

    BtsLoanRes loan(Param... params);

    BtsTransactionRes transaction(Param... params);

    BtsTransferRes transfer(Param... params);

}
