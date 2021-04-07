package fd.se.btsplus.service.impl;

import fd.se.btsplus.model.domain.DateEvent;
import fd.se.btsplus.service.IDateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Profile("!test")
@Slf4j
@AllArgsConstructor
@Service
public class SystemDateService implements IDateService {
    private final ApplicationEventPublisher publisher;

    @Override
    public Date currDate() {
        return new Date();
    }

    @Override
    public DateEvent emit() {
        final Date newDate = currDate();
        final Date lastDate = DateUtils.addDays(newDate, -1);
        final DateEvent event = new DateEvent(this, lastDate, newDate);
        publisher.publishEvent(event);
        return event;
    }

    @Scheduled(cron = "1 0 * * *")
    void dailyEmit() {
        emit();
    }
}
