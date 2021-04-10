package fd.se.btsplus.model.entity.bts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fd.se.btsplus.model.consts.Role;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
