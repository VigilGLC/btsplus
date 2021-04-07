package fd.se.btsplus.utils;

import fd.se.btsplus.model.domain.LoginData;
import fd.se.btsplus.model.response.ResponseWrapper;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;

import java.util.Date;

import static java.net.HttpURLConnection.HTTP_OK;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiExamples {
    private static final JSONUtils utils = new JSONUtils();
    public static final String LoginResponseOk = "{\"code\":200,\"message\":null,\"data\":{\"token\":\"11+1515awsl+10086\",\"expires\":\"2021-04-06 17:43\"}}";
    public static final String CurruserResponseOk = "";
    public static final String LoansResponseOk = "";
    public static final String BillsResponseOk = "";
    public static final String PaymentResponseOk = "";
    public static final String AutoPaymentResponseOk = "";
    public static final String TransactionsResponseOk = "";
    public static final String StocksResponseOk = "";
    public static final String FundsResponseOk = "";
    public static final String TermsResponseOk = "";
    public static final String PurchaseResponseOk = "";
    public static final String FundPurchasesResponseOk = "";
    public static final String StockPurchasesResponseOk = "";
    public static final String TermPurchasesResponseOk = "";

    @Test
    public static void loginResponseOk() {
        final LoginData data = new LoginData("11+1515awsl+10086", new Date());
        System.out.println(utils.write(ResponseWrapper.wrap(HTTP_OK, data)));
    }
}
