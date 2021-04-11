package fd.se.btsplus.config;

import fd.se.btsplus.interceptor.AccountCheckInterceptor;
import fd.se.btsplus.interceptor.AuthenticationInterceptor;
import fd.se.btsplus.interceptor.AuthorizationInterceptor;
import fd.se.btsplus.interceptor.CreditCheckInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AllArgsConstructor
@Configuration
public class BtsWebConfig implements WebMvcConfigurer {
    private static final String[] AUTH_PATTERNS = new String[]{
            "/user/curr",
            "/customer/**",
            "/loans/**",
            "/transactions/**",
            "/financial/**",
    };
    final AuthenticationInterceptor authenticationInterceptor;
    final AuthorizationInterceptor authorizationInterceptor;
    final AccountCheckInterceptor accountCheckInterceptor;
    final CreditCheckInterceptor creditCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).
                addPathPatterns(AUTH_PATTERNS);
        registry.addInterceptor(authorizationInterceptor).
                addPathPatterns(AUTH_PATTERNS);
        registry.addInterceptor(accountCheckInterceptor).
                addPathPatterns(
                        "/customer/loan/bill/*/payment",
                        "/financial/*/*/purchase"
                );
        registry.addInterceptor(creditCheckInterceptor).
                addPathPatterns("/financial/*/*/purchase");
    }
}


