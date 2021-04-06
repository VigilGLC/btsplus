package fd.se.btsplus.model.entity.financial.stock;

import fd.se.btsplus.model.entity.bts.Customer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
public class StockPurchase {
    @Id
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Stock stock;
    private Integer count;
}
