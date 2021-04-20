package fd.se.btsplus.service;

import fd.se.btsplus.model.domain.DateEvent;
import org.junit.jupiter.api.Test;

import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class IDateServiceTest {
    private IDateService iDateService;

    @Test
    void testDayEqual() {
        assertTrue(IDateService.dayEqual(
                new Date(2001 - 1900, Calendar.FEBRUARY, 3),
                new Date(2001 - 1900, Calendar.FEBRUARY, 3)
        ));
        assertFalse(IDateService.dayEqual(
                new Date(2001 - 1900, Calendar.FEBRUARY, 3),
                new Date(2021 - 1900, Calendar.FEBRUARY, 3)
        ));
        assertFalse(IDateService.dayEqual(
                new Date(2001 - 1900, Calendar.JANUARY, 3),
                new Date(2001 - 1900, Calendar.FEBRUARY, 3)
        ));
        assertFalse(IDateService.dayEqual(
                new Date(2001 - 1900, Calendar.FEBRUARY, 1),
                new Date(2021 - 1900, Calendar.FEBRUARY, 3)
        ));
    }

    @Test
    void testDayBefore() {
        assertTrue(IDateService.dayBefore(
                new Date(2000 - 1900, Calendar.JANUARY, 1),
                new Date(2001 - 1900, Calendar.FEBRUARY, 3)
        ));
        assertFalse(IDateService.dayBefore(
                new Date(2001 - 1900, Calendar.FEBRUARY, 3),
                new Date(2001 - 1900, Calendar.FEBRUARY, 3)
        ));
        assertFalse(IDateService.dayBefore(
                new Date(2001 - 1900, Calendar.FEBRUARY, 3),
                new Date(2001 - 1900, Calendar.FEBRUARY, 1)
        ));
        assertFalse(IDateService.dayBefore(
                new Date(2001 - 1900, Calendar.FEBRUARY, 3),
                new Date(2001 - 1900, Calendar.JANUARY, 3)
        ));
        assertFalse(IDateService.dayBefore(
                new Date(2001 - 1900, Calendar.FEBRUARY, 3),
                new Date(2000 - 1900, Calendar.FEBRUARY, 3)
        ));
    }

    @Test
    void testDayAfter() {
        assertTrue(IDateService.dayAfter(
                new Date(2001 - 1900, Calendar.FEBRUARY, 3),
                new Date(2000 - 1900, Calendar.JANUARY, 1)
        ));
        assertFalse(IDateService.dayAfter(
                new Date(2001 - 1900, Calendar.FEBRUARY, 3),
                new Date(2001 - 1900, Calendar.FEBRUARY, 3)
        ));
        assertFalse(IDateService.dayAfter(
                new Date(2001 - 1900, Calendar.FEBRUARY, 1),
                new Date(2001 - 1900, Calendar.FEBRUARY, 3)
        ));
        assertFalse(IDateService.dayAfter(
                new Date(2001 - 1900, Calendar.JANUARY, 3),
                new Date(2001 - 1900, Calendar.FEBRUARY, 3)
        ));
        assertFalse(IDateService.dayAfter(
                new Date(2000 - 1900, Calendar.FEBRUARY, 3),
                new Date(2001 - 1900, Calendar.FEBRUARY, 3)
        ));
    }

    @Test
    void testAddDate() {
        Period period = Period.of(20, 2, 17);
        Date base = new Date(2001 - 1900, Calendar.FEBRUARY, 3);

        base = IDateService.addDate(base, period);
        assertEquals(2021, base.getYear() + 1900);
        assertEquals(Calendar.APRIL, base.getMonth());
        assertEquals(20, base.getDate());
    }

    @Test
    void testDayAfter1() {
        iDateService = new IDateServiceImpl();

        Date date = iDateService.dateAfter(20, 2, 17, 11);
        assertEquals(IDateServiceImpl.curr.getYear() + 20, date.getYear());
        assertEquals(IDateServiceImpl.curr.getMonth() + 2, date.getMonth());
        assertEquals(IDateServiceImpl.curr.getDate() + 17, date.getDate());
        assertEquals(IDateServiceImpl.curr.getHours() + 11, date.getHours());

        iDateService = null;
    }

    @Test
    void testDayAfter2() {
        iDateService = new IDateServiceImpl();

        Date date = iDateService.dateAfter(11);
        assertEquals(IDateServiceImpl.curr.getYear(), date.getYear());
        assertEquals(IDateServiceImpl.curr.getMonth(), date.getMonth());
        assertEquals(IDateServiceImpl.curr.getDate(), date.getDate());
        assertEquals(IDateServiceImpl.curr.getHours() + 11, date.getHours());

        iDateService = null;
    }
}

class IDateServiceImpl implements IDateService {
    public final static Date curr = new Date(2001 - 1900, Calendar.FEBRUARY, 3);

    @Override
    public Date currDate() {
        return curr;
    }

    @Override
    public DateEvent emit() {
        return null;
    }
}
