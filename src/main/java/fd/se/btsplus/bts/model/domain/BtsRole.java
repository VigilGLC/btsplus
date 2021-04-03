package fd.se.btsplus.bts.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BtsRole {
    private long id;
    private long creator;
    private String name;
    private int roleType;
    private String description;
}
