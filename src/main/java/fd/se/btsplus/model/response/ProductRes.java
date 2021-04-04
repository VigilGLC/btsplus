package fd.se.btsplus.model.response;

import fd.se.btsplus.bts.model.response.BtsCurrUserRes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ProductRes extends BaseRes {
    @Schema(description = "list")
    private List<?> list;

    public static ProductRes from(List<?> list) {
        return new ProductRes(list);
    }

}
