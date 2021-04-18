package fd.se.btsplus.service.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void init() {
    }

    @Test
    void currDate() {
    }

    @Test
    void emit() {
    }

    @Test
    void dailyEmit() {
    }
}
