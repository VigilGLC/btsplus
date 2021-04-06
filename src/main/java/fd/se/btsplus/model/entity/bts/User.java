package fd.se.btsplus.model.entity.bts;

import fd.se.btsplus.model.consts.Role;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
    private Role role;

    private String nickname;
    private int sex;
    private String phone;
    private String email;
    private String address;
}
