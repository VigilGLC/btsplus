package fd.se.btsplus.model.entity.bts;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
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
}
