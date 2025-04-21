package nttdata.usertest.config;

import nttdata.usertest.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class JwtConfiguration implements WebMvcConfigurer {

    private JwtInterceptor jwtInterceptor;

    public JwtConfiguration(JwtInterceptor jwtInterceptor){
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/v1/**") // Protege todos los endpoints bajo /api
                .excludePathPatterns("/user/**"); // Excepciones para login, etc.
    }
}
