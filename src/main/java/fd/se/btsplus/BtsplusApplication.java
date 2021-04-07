package fd.se.btsplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BtsplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BtsplusApplication.class, args);
    }

}
