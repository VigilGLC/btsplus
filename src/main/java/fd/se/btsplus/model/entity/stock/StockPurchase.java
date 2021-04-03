package fd.se.btsplus.model.entity.stock;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class StockPurchase {
    @Id
    private Long id;
    private String customerCode;
    @ManyToOne
    private Stock stock;
    private long amount;
}
