package fd.se.btsplus.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseWrapper {
    private int code;
    private String message;
    private Object data;

    public static ResponseWrapper wrap(int code, Object data) {
        return wrap(code, data);
    }

    public static ResponseWrapper wrap(int code, String mesasge, Object data) {
        return new ResponseWrapper(code, mesasge, data);
    }
}
