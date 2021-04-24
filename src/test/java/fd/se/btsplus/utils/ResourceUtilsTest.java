package fd.se.btsplus.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ResourceUtilsTest {
    private final ResourceUtils resourceUtils = new ResourceUtils();
    @Test
    void testReadFileAsStringSuccess() {
        String expected = "123";

        try {
            Assertions.assertEquals(expected, resourceUtils.readFileAsString("test-resources/test"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void testReadFileAsString() {
        String path = "test-resources/test.txt";
        ResourceUtils resourceUtils = new ResourceUtils();
        try {
            Assertions.assertNull(resourceUtils.readFileAsString(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}