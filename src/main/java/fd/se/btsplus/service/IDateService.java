package fd.se.btsplus.service;

import fd.se.btsplus.model.domain.DateEvent;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public interface IDateService {

    Date currDate();

    @SuppressWarnings("UnusedReturnValue")
    DateEvent emit();

    default Date dateAfter(int year, int month, int days, int hours) {
        Date date = currDate();
        date = DateUtils.addYears(date, year);
        date = DateUtils.addMonths(date, month);
        date = DateUtils.addDays(date, days);
        date = DateUtils.addHours(date, hours);
        return date;
    }

    default Date dateAfter(int hours) {
        return dateAfter(0, 0, 0, hours);
    }
}
