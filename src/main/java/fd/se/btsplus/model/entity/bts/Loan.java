package fd.se.btsplus.model.entity.bts;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class Loan {
    @Id
    private Long id;
    private String iouNum;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private User creator;
    private Date createdTime;

    private Date loanDate;

    private String productCode;
    private String productName;

    private Double amount;
    private Double interest;
    private Double total;
}
