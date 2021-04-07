package fd.se.btsplus.model.entity.financial.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import fd.se.btsplus.model.entity.bts.Customer;
import fd.se.btsplus.model.entity.financial.IPurchase;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
public class StockPurchase implements IPurchase {
    @Id
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Stock stock;
    private Integer count;
    private Double price;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date currDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date beginDate;
}
