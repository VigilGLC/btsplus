package fd.se.btsplus.model.entity.financial.term;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
@RequiredArgsConstructor
public class TermDaily {
    @Id
    private Long id;
    @ManyToOne
    private Term term;
    private LocalDate date;
    private Double rate;
}
