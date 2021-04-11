package fd.se.btsplus.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@NoArgsConstructor
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "btsplus", ignoreInvalidFields = true)
public class BtsProperties {
    private String version;
    private String launchDate;
}
