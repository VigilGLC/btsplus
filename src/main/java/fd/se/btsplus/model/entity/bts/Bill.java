package fd.se.btsplus.model.entity.bts;

import fd.se.btsplus.model.consts.BillStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class Bill {
    @Id
    private Long id;
    @ManyToOne
    private Loan loan;
    @ManyToOne
    private User creator;
    private Date createdTime;
    private Date updatedTime;

    private Date beginDate;
    private Date endDate;

    private int planNum;
    private Double planAmount;
    private Double planInterest;

    private Double remainAmount;
    private Double remainInterest;

    private BillStatus status;
}
