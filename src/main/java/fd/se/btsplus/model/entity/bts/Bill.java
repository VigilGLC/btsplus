package fd.se.btsplus.model.entity.bts;

import fd.se.btsplus.model.consts.BillStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Bill {
    @Id
    private Long id;
    @ManyToOne
    private User creator;
    private LocalDateTime createdTime;

    private LocalDate beginDate;
    private LocalDate endDate;
    private LocalDate updateDate;

    private int planNum;
    private Double planPrincipal;
    private Double planInterest;

    private Double remainPrincipal;
    private Double remainInterest;

    private BillStatus status;
}
