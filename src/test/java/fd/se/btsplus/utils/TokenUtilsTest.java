package fd.se.btsplus.utils;

import fd.se.btsplus.model.entity.bts.User;
import fd.se.btsplus.repository.bts.UserRepository;
import fd.se.btsplus.service.IDateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TokenUtilsTest {
    private TokenUtils tokenUtils;
    private String testToken;
    private IDateService iDateService;
    private UserRepository userRepository;
    private Date currDate;
    private Date oldDate;
    private User user;

    @BeforeEach
    void setUp() {
        //curDate 2021-xx
        currDate = new Date();
        //oldDate 2020-xx,固定时间戳
        oldDate = new Date(0);
        iDateService = mock(IDateService.class);
        userRepository = mock(UserRepository.class);
        tokenUtils = new TokenUtils(iDateService, userRepository);
        user = new User();
        user.setUsername("JT2103253794");
        user.setPassword("imbus123");
    }

    @Test
    void testGenerateToken() {
        when(iDateService.dateAfter(12)).thenReturn(oldDate);
        Assertions.assertNotNull(tokenUtils.generateToken(user));
    }

    @Test
    void testGetUserNull() {
        when(iDateService.dateAfter(12)).thenReturn(oldDate);
        when(iDateService.currDate()).thenReturn(currDate);
        String oldToken = tokenUtils.generateToken(user);
        User real = tokenUtils.getUser(oldToken);
        Assertions.assertNull(real);
    }

    @Test
    void testGetUser() {
        when(iDateService.currDate()).thenReturn(oldDate);
        when(iDateService.dateAfter(12)).thenReturn(oldDate);
        String oldToken = tokenUtils.generateToken(user);
        when(userRepository.findByUsernameAndPassword("JT2103253794", "imbus123")).thenReturn(user);
        User real = tokenUtils.getUser(oldToken);
        Assertions.assertEquals(user.getPassword(),real.getPassword());
        Assertions.assertEquals(user.getUsername(),real.getUsername());
    }

    @Test
    void testGetUserError() {
        Assertions.assertNull(tokenUtils.getUser("13"));
    }

    @Test
    void testGetExpiredAt() {
        when(iDateService.dateAfter(12)).thenReturn(oldDate);
        String token = tokenUtils.generateToken(user);
        Calendar expected = Calendar.getInstance();
        expected.setTime(oldDate);
        Calendar real = Calendar.getInstance();
        real.setTime(tokenUtils.getExpireAt(token));
        Assertions.assertEquals(expected.get(Calendar.YEAR), real.get(Calendar.YEAR));
        Assertions.assertEquals(expected.get(Calendar.MONTH), real.get(Calendar.MONTH));
        Assertions.assertEquals(expected.get(Calendar.DATE), real.get(Calendar.DATE));
        Assertions.assertEquals(expected.get(Calendar.HOUR), real.get(Calendar.HOUR));
        Assertions.assertEquals(expected.get(Calendar.MINUTE), real.get(Calendar.MINUTE));
        Assertions.assertEquals(expected.get(Calendar.SECOND), real.get(Calendar.SECOND));
    }


}