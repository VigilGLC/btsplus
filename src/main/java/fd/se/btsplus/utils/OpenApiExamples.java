package fd.se.btsplus.utils;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiExamples {
    public static final String LoansRespOk = "{\"code\":200,\"message\":null,\"data\":[{\"id\":321," +
            "\"iouNum\":\"L2104031952191\",\"customer\":{\"id\":13,\"code\":\"AB2121202104031\"," +
            "\"name\":\"\u8BF7\u4E0D\u8981\u4FAE\u8FB1\u4E8C\u6B21\u5143\",\"idNum\":\"331355200005085616\"," +
            "\"creator\":null,\"createdTime\":\"2021-04-03 19:33\",\"sex\":0,\"phone\":\"13333333333\"," +
            "\"email\":\"3333333@fudan.edu.cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u952E\u76D8\u4FA0" +
            "\u53EA\u80FD\u51FA\u95E8\u53F3\u8F6C2500\u53F7\"},\"creator\":null,\"createdTime\":\"2021-04-03 19:52\"," +
            "\"loanDate\":\"2021-04-03\",\"productCode\":\"20001\",\"productName\":\"\u4E70\u623F\u8D37\u6B3E\"," +
            "\"amount\":20000.0,\"interest\":66.66,\"total\":20066.66},{\"id\":123,\"iouNum\":\"L2104032014071\"," +
            "\"customer\":{\"id\":13,\"code\":\"AB2121202104031\"," +
            "\"name\":\"\u8BF7\u4E0D\u8981\u4FAE\u8FB1\u4E8C\u6B21\u5143\",\"idNum\":\"331355200005085616\"," +
            "\"creator\":null,\"createdTime\":\"2021-04-03 19:33\",\"sex\":0,\"phone\":\"13333333333\"," +
            "\"email\":\"3333333@fudan.edu.cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u952E\u76D8\u4FA0" +
            "\u53EA\u80FD\u51FA\u95E8\u53F3\u8F6C2500\u53F7\"},\"creator\":null,\"createdTime\":\"2021-04-03 20:14\"," +
            "\"loanDate\":\"2021-04-03\",\"productCode\":\"20005\"," +
            "\"productName\":\"\u8BF7\u952E\u76D8\u4FA0\u51FA\u95E8\u53F3\u8F6C\u8D37\u6B3E\",\"amount\":100000.0," +
            "\"interest\":667.3394,\"total\":100667.3394}]}";

    public static final String StockPurchasesRespOk = "{\"code\":200,\"message\":null," +
            "\"data\":[{\"product\":{\"id\":1,\"name\":\"Bilibili \u80A1\u7968\",\"createdTime\":\"2021-04-03 " +
            "20:14\"},\"daily\":{\"id\":1,\"stock\":{\"id\":1,\"name\":\"Bilibili \u80A1\u7968\"," +
            "\"createdTime\":\"2021-04-03 20:14\"},\"date\":\"2021-04-03\",\"price\":120.0},\"purchase\":{\"id\":1," +
            "\"customer\":{\"id\":13,\"code\":\"AB2121202104031\"," +
            "\"name\":\"\u8BF7\u4E0D\u8981\u4FAE\u8FB1\u4E8C\u6B21\u5143\",\"idNum\":\"331355200005085616\"," +
            "\"creator\":{\"id\":208,\"username\":\"JT2103253794\",\"password\":\"imbus123\",\"role\":1," +
            "\"nickname\":\"\u9AD8\u9F99\u8D85\",\"sex\":1,\"phone\":\"13832789992\",\"email\":\"31937@fudan.edu" +
            ".cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u90AF\u90F82500\u53F7\"}," +
            "\"createdTime\":\"2021-04-03 19:33\",\"sex\":0,\"phone\":\"13333333333\",\"email\":\"3333333@fudan.edu" +
            ".cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u952E\u76D8\u4FA0\u53EA\u80FD\u51FA\u95E8\u53F3" +
            "\u8F6C2500\u53F7\"},\"stock\":{\"id\":1,\"name\":\"Bilibili \u80A1\u7968\",\"createdTime\":\"2021-04-03 " +
            "20:14\"},\"count\":100,\"initPrice\":120.0,\"currDate\":\"2021-04-03\",\"currPrice\":120.0," +
            "\"beginDate\":\"2021-04-03\"}}]}";

    public static final String BillsRespOk = "{\"code\":200,\"message\":null,\"data\":[{\"id\":67," +
            "\"loan\":{\"id\":123,\"iouNum\":\"L2104032014071\",\"customer\":{\"id\":13,\"code\":\"AB2121202104031\"," +
            "\"name\":\"\u8BF7\u4E0D\u8981\u4FAE\u8FB1\u4E8C\u6B21\u5143\",\"idNum\":\"331355200005085616\"," +
            "\"creator\":null,\"createdTime\":\"2021-04-03 19:33\",\"sex\":0,\"phone\":\"13333333333\"," +
            "\"email\":\"3333333@fudan.edu.cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u952E\u76D8\u4FA0" +
            "\u53EA\u80FD\u51FA\u95E8\u53F3\u8F6C2500\u53F7\"},\"creator\":null,\"createdTime\":\"2021-04-03 20:14\"," +
            "\"loanDate\":\"2021-04-03\",\"productCode\":\"20005\"," +
            "\"productName\":\"\u8BF7\u952E\u76D8\u4FA0\u51FA\u95E8\u53F3\u8F6C\u8D37\u6B3E\",\"amount\":100000.0," +
            "\"interest\":667.3394,\"total\":100667.3394},\"creator\":null,\"createdTime\":\"2021-04-03 20:17\"," +
            "\"updatedTime\":\"2021-04-03 20:17\",\"beginDate\":\"2021-04-03\",\"endDate\":\"2021-05-03\"," +
            "\"planNum\":1,\"planAmount\":33222.4798,\"planInterest\":333.3,\"remainAmount\":33222.4798," +
            "\"remainInterest\":333.3,\"status\":1},{\"id\":68,\"loan\":{\"id\":123,\"iouNum\":\"L2104032014071\"," +
            "\"customer\":{\"id\":13,\"code\":\"AB2121202104031\"," +
            "\"name\":\"\u8BF7\u4E0D\u8981\u4FAE\u8FB1\u4E8C\u6B21\u5143\",\"idNum\":\"331355200005085616\"," +
            "\"creator\":null,\"createdTime\":\"2021-04-03 19:33\",\"sex\":0,\"phone\":\"13333333333\"," +
            "\"email\":\"3333333@fudan.edu.cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u952E\u76D8\u4FA0" +
            "\u53EA\u80FD\u51FA\u95E8\u53F3\u8F6C2500\u53F7\"},\"creator\":null,\"createdTime\":\"2021-04-03 20:14\"," +
            "\"loanDate\":\"2021-04-03\",\"productCode\":\"20005\"," +
            "\"productName\":\"\u8BF7\u952E\u76D8\u4FA0\u51FA\u95E8\u53F3\u8F6C\u8D37\u6B3E\",\"amount\":100000.0," +
            "\"interest\":667.3394,\"total\":100667.3394},\"creator\":null,\"createdTime\":\"2021-04-03 20:17\"," +
            "\"updatedTime\":\"2021-04-03 20:17\",\"beginDate\":\"2021-05-03\",\"endDate\":\"2021-06-03\"," +
            "\"planNum\":2,\"planAmount\":33333.2103,\"planInterest\":222.5695,\"remainAmount\":33333.2103," +
            "\"remainInterest\":222.5695,\"status\":1},{\"id\":69,\"loan\":{\"id\":123,\"iouNum\":\"L2104032014071\"," +
            "\"customer\":{\"id\":13,\"code\":\"AB2121202104031\"," +
            "\"name\":\"\u8BF7\u4E0D\u8981\u4FAE\u8FB1\u4E8C\u6B21\u5143\",\"idNum\":\"331355200005085616\"," +
            "\"creator\":null,\"createdTime\":\"2021-04-03 19:33\",\"sex\":0,\"phone\":\"13333333333\"," +
            "\"email\":\"3333333@fudan.edu.cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u952E\u76D8\u4FA0" +
            "\u53EA\u80FD\u51FA\u95E8\u53F3\u8F6C2500\u53F7\"},\"creator\":null,\"createdTime\":\"2021-04-03 20:14\"," +
            "\"loanDate\":\"2021-04-03\",\"productCode\":\"20005\"," +
            "\"productName\":\"\u8BF7\u952E\u76D8\u4FA0\u51FA\u95E8\u53F3\u8F6C\u8D37\u6B3E\",\"amount\":100000.0," +
            "\"interest\":667.3394,\"total\":100667.3394},\"creator\":null,\"createdTime\":\"2021-04-03 20:17\"," +
            "\"updatedTime\":\"2021-04-03 20:17\",\"beginDate\":\"2021-06-03\",\"endDate\":\"2021-07-03\"," +
            "\"planNum\":3,\"planAmount\":33444.3099,\"planInterest\":111.4699,\"remainAmount\":33444.3099," +
            "\"remainInterest\":111.4699,\"status\":1}]}";

    public static final String AutoPaymentRespOk = "{\"code\":200,\"message\":null,\"data\":true}";

    public static final String FundsRespOk = "{\"code\":200,\"message\":null,\"data\":[{\"product\":{\"id\":1," +
            "\"name\":\"Bilibili \u57FA\u91D1\",\"createdTime\":\"2021-04-03 20:14\"},\"daily\":{\"id\":1," +
            "\"fund\":{\"id\":1,\"name\":\"Bilibili \u57FA\u91D1\",\"createdTime\":\"2021-04-03 20:14\"}," +
            "\"date\":\"2021-04-03\",\"rate\":1.01},\"purchase\":null}]}";

    public static final String StocksRespOk = "{\"code\":200,\"message\":null,\"data\":[{\"product\":{\"id\":1," +
            "\"name\":\"Bilibili \u80A1\u7968\",\"createdTime\":\"2021-04-03 20:14\"},\"daily\":{\"id\":1," +
            "\"stock\":{\"id\":1,\"name\":\"Bilibili \u80A1\u7968\",\"createdTime\":\"2021-04-03 20:14\"}," +
            "\"date\":\"2021-04-03\",\"price\":120.0},\"purchase\":null}]}";

    public static final String TermsRespOk = "{\"code\":200,\"message\":null,\"data\":[{\"product\":{\"id\":1," +
            "\"name\":\"Bilibili \u5B9A\u671F\",\"createdTime\":\"2021-04-03 20:14\"},\"daily\":{\"id\":1," +
            "\"term\":{\"id\":1,\"name\":\"Bilibili \u5B9A\u671F\",\"createdTime\":\"2021-04-03 20:14\"}," +
            "\"date\":\"2021-04-03\",\"rate\":1.01},\"purchase\":null}]}";

    public static final String TermPurchasesRespOk = "{\"code\":200,\"message\":null," +
            "\"data\":[{\"product\":{\"id\":1,\"name\":\"Bilibili \u5B9A\u671F\",\"createdTime\":\"2021-04-03 " +
            "20:14\"},\"daily\":{\"id\":1,\"term\":{\"id\":1,\"name\":\"Bilibili \u5B9A\u671F\"," +
            "\"createdTime\":\"2021-04-03 20:14\"},\"date\":\"2021-04-03\",\"rate\":1.01},\"purchase\":{\"id\":1," +
            "\"customer\":{\"id\":13,\"code\":\"AB2121202104031\"," +
            "\"name\":\"\u8BF7\u4E0D\u8981\u4FAE\u8FB1\u4E8C\u6B21\u5143\",\"idNum\":\"331355200005085616\"," +
            "\"creator\":{\"id\":208,\"username\":\"JT2103253794\",\"password\":\"imbus123\",\"role\":1," +
            "\"nickname\":\"\u9AD8\u9F99\u8D85\",\"sex\":1,\"phone\":\"13832789992\",\"email\":\"31937@fudan.edu" +
            ".cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u90AF\u90F82500\u53F7\"}," +
            "\"createdTime\":\"2021-04-03 19:33\",\"sex\":0,\"phone\":\"13333333333\",\"email\":\"3333333@fudan.edu" +
            ".cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u952E\u76D8\u4FA0\u53EA\u80FD\u51FA\u95E8\u53F3" +
            "\u8F6C2500\u53F7\"},\"term\":{\"id\":1,\"name\":\"Bilibili \u5B9A\u671F\",\"createdTime\":\"2021-04-03 " +
            "20:14\"},\"initAmount\":2000.0,\"currAmount\":2000.0,\"currDate\":\"2021-04-03\"," +
            "\"beginDate\":\"2021-04-03\",\"endDate\":\"2021-05-03\"}}]}";

    public static final String LoginRespOk = "{\"code\":200,\"message\":null,\"data\":{\"token\":\"3714sgw+10086\"," +
            "\"expires\":\"2021-04-10 13:49\"}}";

    public static final String TransactionsRespOk = "{\"code\":200,\"message\":null,\"data\":[{\"id\":70," +
            "\"operator\":null,\"operatedTime\":\"2021-03-25 17:17\",\"transactionCode\":\"1621\"," +
            "\"transactionNum\":\"AB212116210202103251717531\"," +
            "\"transactionType\":\"\u4E00\u672C\u901A\u5F00\u6237\",\"account\":{\"id\":null,\"accountNum\":null," +
            "\"password\":null,\"accountType\":null,\"creator\":null,\"customer\":null,\"createdTime\":null," +
            "\"balance\":null},\"reciprocalAccount\":{\"id\":null,\"accountNum\":null,\"password\":null," +
            "\"accountType\":null,\"creator\":null,\"customer\":null,\"createdTime\":null,\"balance\":null}," +
            "\"reciprocalName\":null,\"amount\":null,\"balance\":20000.0},{\"id\":71,\"operator\":null," +
            "\"operatedTime\":\"2021-03-25 18:03\",\"transactionCode\":\"0001\"," +
            "\"transactionNum\":\"AB21210001206202103251803061\"," +
            "\"transactionType\":\"\u8425\u4E1A\u5355\u4F4D\u5F00\u673A\u4F5C\u4E1A\",\"account\":{\"id\":null," +
            "\"accountNum\":null,\"password\":null,\"accountType\":null,\"creator\":null,\"customer\":null," +
            "\"createdTime\":null,\"balance\":null},\"reciprocalAccount\":null,\"reciprocalName\":null,\"amount\":0" +
            ".0,\"balance\":5000.0},{\"id\":72,\"operator\":null,\"operatedTime\":\"2021-03-25 18:03\"," +
            "\"transactionCode\":\"0001\",\"transactionNum\":\"AB21210001206202103251803271\"," +
            "\"transactionType\":\"\u8425\u4E1A\u5355\u4F4D\u5F00\u673A\u4F5C\u4E1A\",\"account\":{\"id\":null," +
            "\"accountNum\":null,\"password\":null,\"accountType\":null,\"creator\":null,\"customer\":null," +
            "\"createdTime\":null,\"balance\":null},\"reciprocalAccount\":null,\"reciprocalName\":null,\"amount\":100" +
            ".0,\"balance\":100.0}]}";

    public static final String PaymentRespOk = "{\"code\":200,\"message\":\"Success!\",\"data\":true}";

    public static final String CurrUserRespOk = "{\"code\":200,\"message\":null,\"data\":{\"id\":200," +
            "\"username\":\"BA2103154781\",\"password\":\"imbus123\",\"role\":2," +
            "\"nickname\":\"\u4E09\u7EC4\u7BA1\u7406\u5458\",\"sex\":1,\"phone\":\"13456890202\"," +
            "\"email\":\"12903821@fudan.edu.cn\",\"address\":\"\u4E0A\u6D77\u540C\u6D4E\"}}";

    public static final String FundPurchasesRespOk = "{\"code\":200,\"message\":null," +
            "\"data\":[{\"product\":{\"id\":1,\"name\":\"Bilibili \u57FA\u91D1\",\"createdTime\":\"2021-04-03 " +
            "20:14\"},\"daily\":{\"id\":1,\"fund\":{\"id\":1,\"name\":\"Bilibili \u57FA\u91D1\"," +
            "\"createdTime\":\"2021-04-03 20:14\"},\"date\":\"2021-04-03\",\"rate\":1.01},\"purchase\":{\"id\":1," +
            "\"customer\":{\"id\":13,\"code\":\"AB2121202104031\"," +
            "\"name\":\"\u8BF7\u4E0D\u8981\u4FAE\u8FB1\u4E8C\u6B21\u5143\",\"idNum\":\"331355200005085616\"," +
            "\"creator\":{\"id\":208,\"username\":\"JT2103253794\",\"password\":\"imbus123\",\"role\":1," +
            "\"nickname\":\"\u9AD8\u9F99\u8D85\",\"sex\":1,\"phone\":\"13832789992\",\"email\":\"31937@fudan.edu" +
            ".cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u90AF\u90F82500\u53F7\"}," +
            "\"createdTime\":\"2021-04-03 19:33\",\"sex\":0,\"phone\":\"13333333333\",\"email\":\"3333333@fudan.edu" +
            ".cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u952E\u76D8\u4FA0\u53EA\u80FD\u51FA\u95E8\u53F3" +
            "\u8F6C2500\u53F7\"},\"fund\":{\"id\":1,\"name\":\"Bilibili \u57FA\u91D1\",\"createdTime\":\"2021-04-03 " +
            "20:14\"},\"initAmount\":2000.0,\"currAmount\":2000.0,\"currDate\":\"2021-04-03\"," +
            "\"beginDate\":\"2021-04-03\",\"endDate\":\"2021-05-03\"}}]}";

    public static final String PurchaseRespOk = "{\"code\":200,\"message\":null,\"data\":true}";

}
