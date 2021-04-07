package fd.se.btsplus.model.entity.bts;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date loanDate;

    private String productCode;
    private String productName;

    private Double amount;
    private Double interest;
    private Double total;
}
