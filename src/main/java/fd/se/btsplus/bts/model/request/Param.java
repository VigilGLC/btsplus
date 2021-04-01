package fd.se.btsplus.bts.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Param {
    private String query;
    private String value;
}
