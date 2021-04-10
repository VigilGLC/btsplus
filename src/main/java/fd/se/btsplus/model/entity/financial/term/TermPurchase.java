package fd.se.btsplus.model.entity.financial.term;

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
public class TermPurchase implements IPurchase {
    @Id
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Term term;
    private Double initAmount;

    private Double currAmount;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date currDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date beginDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TermPurchase)) return false;
        TermPurchase that = (TermPurchase) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
