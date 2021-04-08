package fd.se.btsplus.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class OperationResult {
    private int code;
    private String message;
}
