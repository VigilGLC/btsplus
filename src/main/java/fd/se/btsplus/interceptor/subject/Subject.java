package fd.se.btsplus.interceptor.subject;

import fd.se.btsplus.model.entity.bts.Account;
import fd.se.btsplus.model.entity.bts.Customer;
import fd.se.btsplus.model.entity.bts.User;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Data
@RequestScope
@Component
public class Subject {
    private User currUser;
    private Account account;
    private Customer customer;
}
