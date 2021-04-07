package fd.se.btsplus.service;

import fd.se.btsplus.model.domain.DateEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Slf4j
@AllArgsConstructor
@Service
public class UpdaterService implements ApplicationListener<DateEvent> {
    @Override
    public final void onApplicationEvent(DateEvent event) {
        update();
    }

    public void update() {
        throw new NotImplementedException();
    }
}
