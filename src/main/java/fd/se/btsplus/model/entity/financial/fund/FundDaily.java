package fd.se.btsplus.model.entity.financial.fund;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
public class FundDaily {
    @Id
    private Long id;
    @ManyToOne
    private Fund fund;
    private Date date;
    private Double rate;
}
