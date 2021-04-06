package fd.se.btsplus.auth;

import fd.se.btsplus.model.entity.bts.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Data
@RequestScope
@Component
public class Subject {
    private User currUser;
}
