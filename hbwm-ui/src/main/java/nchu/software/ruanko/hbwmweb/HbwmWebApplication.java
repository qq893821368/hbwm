package nchu.software.ruanko.hbwmweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"nchu"})
@ComponentScan
@MapperScan("nchu.software.ruanko.hbwmda")
@SpringBootApplication
public class HbwmWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HbwmWebApplication.class, args);
    }

}
