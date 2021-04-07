package fd.se.btsplus.model.entity.financial.term;

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
public class TermDaily implements IDaily {
    @Id
    private Long id;
    @ManyToOne
    private Term term;
    private Date date;
    private Double rate;
}
