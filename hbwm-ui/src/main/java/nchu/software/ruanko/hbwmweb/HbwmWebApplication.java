package nchu.software.ruanko.hbwmweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan("nchu.software.ruanko")
@SpringBootApplication
public class HbwmWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HbwmWebApplication.class, args);
    }

}
