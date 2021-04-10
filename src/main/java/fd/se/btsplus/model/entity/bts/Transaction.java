package fd.se.btsplus.model.entity.bts;

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
public class Transaction {
    @Id
    private Long id;
    @ManyToOne
    private User operator;
    private Date operatedTime;

    private String transactionCode;
    private String transactionNum;
    private String transactionType;

    @ManyToOne
    private Account account;
    @ManyToOne
    private Account reciprocalAccount;
    private String reciprocalName;
    private Double amount;
    private Double balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
