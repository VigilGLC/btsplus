package fd.se.btsplus.model.entity.financial.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fd.se.btsplus.model.entity.bts.Customer;
import fd.se.btsplus.model.entity.financial.IPurchase;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockPurchase implements IPurchase {
    @Id
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Stock stock;
    private Integer count;
    private Double initPrice;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date currDate;
    private Double currPrice;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date beginDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockPurchase)) return false;
        StockPurchase that = (StockPurchase) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
