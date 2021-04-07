package fd.se.btsplus.utils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiExamples {
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
    private static final JSONUtils utils = new JSONUtils();
}
