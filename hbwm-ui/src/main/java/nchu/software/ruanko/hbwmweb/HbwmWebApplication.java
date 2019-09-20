package nchu.software.ruanko.hbwmweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan(basePackages = {"nchu"})
@MapperScan("nchu.software.ruanko.hbwmda")
@SpringBootApplication
public class HbwmWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HbwmWebApplication.class, args);
    }

}
