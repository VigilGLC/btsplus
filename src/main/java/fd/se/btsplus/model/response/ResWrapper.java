package fd.se.btsplus.model.response;

import fd.se.btsplus.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "wrap")
public class ResWrapper {
    private int code;
    private BaseRes res;
}
