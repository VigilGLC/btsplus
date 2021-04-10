package fd.se.btsplus.model.entity.financial.fund;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fd.se.btsplus.model.entity.financial.IDaily;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FundDaily implements IDaily {
    @Id
    private Long id;
    @ManyToOne
    private Fund fund;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date date;
    private Double rate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FundDaily)) return false;
        FundDaily fundDaily = (FundDaily) o;
        return getId().equals(fundDaily.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
