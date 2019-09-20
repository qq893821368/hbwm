package nchu.software.ruanko.hbwmbase.config;

import nchu.software.ruanko.hbwmutil.interceptor.LoginAntiInterceptor;
import nchu.software.ruanko.hbwmutil.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

@ComponentScan("nchu.software.ruanko.hbwmutil")
@Configuration
public class WebConfig implements WebMvcConfigurer  {
    @Autowired
    LoginInterceptor loginInterceptor;
    @Autowired
    LoginAntiInterceptor loginAntiInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] loginPage = {"/verify", "/verifyImpl", "/login", "/bootstrap/**", "/layui/**", "/register"};
        String[] antiLoginPage = {"/login"};

        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(loginPage);
        //registry.addInterceptor(loginAntiInterceptor).addPathPatterns(antiLoginPage);
    }
}
