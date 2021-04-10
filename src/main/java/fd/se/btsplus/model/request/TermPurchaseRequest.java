package fd.se.btsplus.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fd.se.btsplus.utils.PeriodDeserializer;
import fd.se.btsplus.utils.PeriodSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TermPurchaseRequest {
    private String accountNum;
    private String password;
    private Double amount;
    @JsonSerialize(using = PeriodSerializer.class)
    @JsonDeserialize(using = PeriodDeserializer.class)
    private Period period;
}
