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
    private static final String[] PATTERNS = new String[]{
            "/user/curr",
            "/customer/**",
            "/loans/**",
            "/transactions/**",
            "/financial/**",
    };
    final AuthenticationInterceptor authenticationInterceptor;
    final AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).
                addPathPatterns(PATTERNS).
                excludePathPatterns("/user/login");
        registry.addInterceptor(authorizationInterceptor).
                addPathPatterns(PATTERNS).
                excludePathPatterns("/user/login");
    }
}


