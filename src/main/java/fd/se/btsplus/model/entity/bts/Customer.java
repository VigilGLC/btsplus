package fd.se.btsplus.model.entity.bts;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Customer {
    @Id
    private Long id;
    private String code;
    private String name;
    @ManyToOne
    private User creator;
    private LocalDateTime createdTime;

    private int sex;
    private String phone;
    private String email;
    private String address;
}
