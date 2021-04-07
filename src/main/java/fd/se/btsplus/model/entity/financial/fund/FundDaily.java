package fd.se.btsplus.model.entity.financial.fund;

import com.fasterxml.jackson.annotation.JsonFormat;
import fd.se.btsplus.model.entity.financial.IDaily;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
public class FundDaily implements IDaily {
    @Id
    private Long id;
    @ManyToOne
    private Fund fund;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date date;
    private Double rate;
}
