package fd.se.btsplus.model.entity.bts;

import com.fasterxml.jackson.annotation.JsonFormat;
import fd.se.btsplus.model.consts.BillStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date beginDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    private int planNum;
    private Double planAmount;
    private Double planInterest;

    private Double remainAmount;
    private Double remainInterest;

    private BillStatus status;
}
