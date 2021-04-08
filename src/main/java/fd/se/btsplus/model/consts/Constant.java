package fd.se.btsplus.model.consts;

import fd.se.btsplus.model.entity.bts.Account;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String LOGIN_TOKEN_HEADER = "login-token";


    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    public static final String HTTP_PUT = "PUT";
    public static final String HTTP_DELETE = "DELETE";

    public static final String FUND = "fund";
    public static final String STOCK = "stock";
    public static final String TERM = "term";

    public static final String ASC = "asc";
    public static final String DESC = "desc";

    public static final Account BANK_ACCOUNT = new Account(1L, "77774396",
            null, "储蓄账户", null, null, null,
            129890000.0000);

}
