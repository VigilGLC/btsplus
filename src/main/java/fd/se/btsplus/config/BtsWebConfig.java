package fd.se.btsplus.config;

import fd.se.btsplus.auth.AuthenticationInterceptor;
import fd.se.btsplus.auth.AuthorizationInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AllArgsConstructor
@Configuration
public class BtsWebConfig implements WebMvcConfigurer {
    final AuthenticationInterceptor authenticationInterceptor;
    final AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).
                addPathPatterns().
                excludePathPatterns("/login").
                excludePathPatterns("/**");
        registry.addInterceptor(authorizationInterceptor).
                addPathPatterns().
                excludePathPatterns("/login").
                excludePathPatterns("/**");
    }
}


