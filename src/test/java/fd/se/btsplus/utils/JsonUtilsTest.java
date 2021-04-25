package fd.se.btsplus.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class JsonUtilsTest {

    private final JsonUtils jsonUtils = new JsonUtils();

    @BeforeEach
    void setUp() {

    }

    @Test
    void testWrite() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String expected = "\"" + simpleDateFormat.format(date) + "\"";
        Assertions.assertEquals(expected, jsonUtils.write(date));
    }

    @Test
    void testRead() {
        String json = "{\"id\":1,\"name\":\"yyt\"}";
        User user = jsonUtils.read(json, User.class);
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("yyt", user.getName());
    }

    @Test
    void testReadList() {
        String json = "[{\"id\":1,\"name\":\"yyt\"},{\"id\":2,\"name\":\"yyt\"}]";
        List<User> users = jsonUtils.readList(json, User.class);
        int count = 1;
        for (User user : users) {
            Assertions.assertEquals(count, user.getId());
            Assertions.assertEquals("yyt", user.getName());
            count++;
        }
    }

    private static class User {
        private int id;
        private String name;

        User() {
        }

        User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}



