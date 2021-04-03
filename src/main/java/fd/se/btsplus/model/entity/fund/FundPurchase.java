package fd.se.btsplus.model.entity.fund;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class FundPurchase {
    @Id
    private Long id;
    private String customerCode;
    @ManyToOne
    private Fund fund;
    private long amount;
    private LocalDateTime time;
}
