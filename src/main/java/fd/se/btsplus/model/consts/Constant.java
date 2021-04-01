package fd.se.btsplus.model.consts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String LOGIN_TOKEN_HEADER = "login-header";


    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";
    public static final String HTTP_PUT = "PUT";
    public static final String HTTP_DELETE = "DELETE";

    // loan status code
    public static final int UNISSUED = 1;
    public static final int NORMAL = 2;
    public static final int LOSS = 3;
    public static final int ABNORMAL = 4;

}
