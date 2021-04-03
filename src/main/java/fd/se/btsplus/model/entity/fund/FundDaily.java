package fd.se.btsplus.model.entity.fund;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
@RequiredArgsConstructor
public class FundDaily {
    @Id
    private Long id;
    @ManyToOne
    private Fund fund;
    private LocalDate date;
    private Long price;
}
