package fd.se.btsplus.model.response;


import fd.se.btsplus.bts.model.response.BtsQueryAccountRes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class QueryAccountRes extends BaseRes {
    @Schema(description = "code")
    private int code;
    @Schema(description = "count")
    private long count;
    @Schema(description = "data")
//    private BtsData data;
    private Object data;

    @Schema(description = "flag")
    private boolean flag;
    @Schema(description = "message")
    private String message;

    public static QueryAccountRes from(BtsQueryAccountRes res) {
        return new QueryAccountRes(res.getCode(),
                res.getCount(),
                res.getData(),
                res.isFlag(),
                res.getMessage());
    }
}
