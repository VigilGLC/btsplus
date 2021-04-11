package fd.se.btsplus.service.impl;

import fd.se.btsplus.config.BtsProperties;
import fd.se.btsplus.model.domain.DateEvent;
import fd.se.btsplus.service.IDateService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class SystemDateService implements IDateService {
    @NonNull
    private final ApplicationEventPublisher publisher;
    @NonNull
    private final BtsProperties btsProperties;

    private Period offset;

    @SneakyThrows
    @PostConstruct
    void init() {
        String dateStr = btsProperties.getLaunchDate();
        if (dateStr == null || (dateStr = dateStr.trim()).isEmpty()) {
            offset = Period.of(0, 0, 0);
            return;
        }
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Date launchDate = format.parse(dateStr);

        LocalDate base = currDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate target = launchDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        offset = Period.between(base, target);
        log.warn(MessageFormat.format("Btsplus launched at: {0}. ",
                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(currDate())));
    }

    @Override
    public Date currDate() {
        final Date date = new Date();
        if (offset == null) {
            return date;
        }
        return IDateService.addDate(date, offset);
    }

    @Override
    public DateEvent emit() {
        final Date newDate = DateUtils.truncate(currDate(), Calendar.DAY_OF_MONTH);
        final Date lastDate = DateUtils.addDays(newDate, -1);
        final DateEvent event = new DateEvent(this, lastDate, newDate);
        publisher.publishEvent(event);
        return event;
    }

    @Scheduled(cron = "0 1 0 * * *")
    void dailyEmit() {
        emit();
    }
}
