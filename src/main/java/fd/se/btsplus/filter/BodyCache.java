package fd.se.btsplus.filter;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Data
@RequestScope
@Component
public class BodyCache {
    private String body;
}
