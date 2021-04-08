package fd.se.btsplus.model.entity.bts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private Date createdTime;

    private Double balance;
}
