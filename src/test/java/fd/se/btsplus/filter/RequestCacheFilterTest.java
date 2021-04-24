package fd.se.btsplus.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RequestCacheFilterTest {


    @Test
    void testIsFinishedTrue() {
        byte[] param = new byte[0];
        CachedBodyServletInputStream cachedBodyServletInputStream = new CachedBodyServletInputStream(param);
        Assertions.assertTrue(cachedBodyServletInputStream.isFinished());
    }

    @Test
    void testIsFinishedFalse() {
        byte[] param = new byte[1];
        CachedBodyServletInputStream cachedBodyServletInputStream = new CachedBodyServletInputStream(param);
        Assertions.assertFalse(cachedBodyServletInputStream.isFinished());
    }

}