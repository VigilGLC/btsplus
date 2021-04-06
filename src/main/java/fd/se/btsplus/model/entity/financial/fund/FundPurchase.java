package fd.se.btsplus.model.entity.financial.fund;

import fd.se.btsplus.model.entity.bts.Customer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class FundPurchase {
    @Id
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Fund fund;
    private Double initAmount;

    private Double currAmount;
    private LocalDate beginDate;
    private LocalDate endDate;
}
