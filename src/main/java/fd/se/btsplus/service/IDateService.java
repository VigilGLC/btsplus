package fd.se.btsplus.service;

import fd.se.btsplus.model.domain.DateEvent;
import org.apache.commons.lang3.time.DateUtils;

import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public interface IDateService {

    static boolean dayEqual(Date date1, Date date2) {
        return DateUtils.truncatedEquals(date1, date2, Calendar.DAY_OF_MONTH);
    }

    static boolean dayBefore(Date date1, Date date2) {
        date1 = DateUtils.truncate(date1, Calendar.DAY_OF_MONTH);
        date2 = DateUtils.truncate(date2, Calendar.DAY_OF_MONTH);
        return date1.before(date2);
    }

    static boolean dayAfter(Date date1, Date date2) {
        date1 = DateUtils.truncate(date1, Calendar.DAY_OF_MONTH);
        date2 = DateUtils.truncate(date2, Calendar.DAY_OF_MONTH);
        return date1.after(date2);
    }

    static Date addDate(Date base, Period period) {
        base = DateUtils.addDays(base, period.getDays());
        base = DateUtils.addMonths(base, period.getMonths());
        base = DateUtils.addYears(base, period.getYears());
        return base;
    }


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
