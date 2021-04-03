package fd.se.btsplus.model.entity.term;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class TermPurchase {
    @Id
    private Long id;
    private String customerCode;
    @ManyToOne
    private Term term;
    private long amount;
    private LocalDateTime time;
}
