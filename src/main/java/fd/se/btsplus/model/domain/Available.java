package fd.se.btsplus.model.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Component
public class Available {
    private volatile boolean ready = true;

    public boolean ready() {
        return ready;
    }

    public void stop() {
        ready = false;
    }

    public void resume() {
        ready = true;
    }
}
