package fd.se.btsplus.model.entity.bts;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
public class Customer {
    @Id
    private Long id;
    private String code;
    private String name;
    private String idNum;
    @ManyToOne
    private User creator;
    private Date createdTime;

    private int sex;
    private String phone;
    private String email;
    private String address;
}
