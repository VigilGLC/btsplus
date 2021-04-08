package fd.se.btsplus.service.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;


class SystemDateServiceTest {

    @Test
    void testDayOfTheMonth() {
        final Date date = new Date();
        final Date truncated = DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
        System.out.println(truncated);
    }
}
