package nchu.software.ruanko.hbwmbase.config;

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] loginPage = {"/","/login", "/bootstrap/**", "/layui/**", "/register"};
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(loginPage);
    }
}
