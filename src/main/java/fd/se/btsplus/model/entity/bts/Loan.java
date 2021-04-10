package fd.se.btsplus.model.entity.bts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Loan {
    @Id
    private Long id;
    private String iouNum;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private User creator;
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date loanDate;

    private String productCode;
    private String productName;

    private Double amount;
    private Double interest;
    private Double total;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan)) return false;
        Loan loan = (Loan) o;
        return getId().equals(loan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
