package fd.se.btsplus.model.entity.bts;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Account {
    @Id
    private Long id;
    private String accountNum;
    private String password;
    private String accountType;

    @ManyToOne
    private User creator;
    @ManyToOne
    private Customer customer;
    private LocalDateTime createdTime;

    private Long balance;
}
