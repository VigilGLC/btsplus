package fd.se.btsplus.service.impl;

import fd.se.btsplus.config.BtsProperties;
import fd.se.btsplus.service.IDateService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class SystemDateServiceTest {
    private IDateService serviceWithoutDateStr;
    private IDateService systemDateService;

    private ApplicationEventPublisher publisher;
    private BtsProperties btsProperties;

    // launch-date for btsProperties
    private final static String dateStr = "2021-04-20";

    @SneakyThrows
    @BeforeEach
    void setUp() {
        publisher = Mockito.mock(ApplicationContext.class);
        btsProperties = Mockito.mock(BtsProperties.class);
        Mockito.when(btsProperties.getLaunchDate()).thenReturn("").thenReturn(dateStr);

        // test if branch in init()
        serviceWithoutDateStr = new SystemDateService(publisher, btsProperties);
        Method init = serviceWithoutDateStr.getClass().getDeclaredMethod("init");
        init.invoke(serviceWithoutDateStr);

        // create systemDateService for test case
        systemDateService = new SystemDateService(publisher, btsProperties);
        init = systemDateService.getClass().getDeclaredMethod("init");
        init.invoke(systemDateService);
    }

    @AfterEach
    void tearDown() {
        publisher = null;
        btsProperties = null;
        serviceWithoutDateStr = null;
        systemDateService = null;
    }

    @SneakyThrows
    @Test
    void testCurrDate() {
        Date currDate = new Date();
        Date launchDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        LocalDate base = currDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate target = launchDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period offset = Period.between(base, target);

        Date result = systemDateService.currDate();
        assertEquals(currDate.getYear() + offset.getYears(), result.getYear());
        assertEquals(currDate.getMonth() + offset.getMonths(), result.getMonth());
        assertEquals(currDate.getDate() + offset.getDays(), result.getDate());
    }

    @Test
    void testEmit() {
        // done in testDailyEmit()
    }

    @SneakyThrows
    @Test
    void testDailyEmit() {
        Method dailyEmit = systemDateService.getClass().getDeclaredMethod("dailyEmit");
        dailyEmit.invoke(systemDateService);
    }
}
