package fd.se.btsplus.model.entity.bts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fd.se.btsplus.model.consts.BillStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bill {
    @Id
    private Long id;
    @ManyToOne
    private Loan loan;
    @ManyToOne
    private User creator;
    private Date createdTime;
    private Date updatedTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private int planNum;
    private Double planAmount;
    private Double planInterest;

    private Double remainAmount;
    private Double remainInterest;

    private BillStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill)) return false;
        Bill account = (Bill) o;
        return getId().equals(account.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
