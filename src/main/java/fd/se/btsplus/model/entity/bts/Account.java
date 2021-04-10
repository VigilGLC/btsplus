package fd.se.btsplus.model.entity.bts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getId().equals(account.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
