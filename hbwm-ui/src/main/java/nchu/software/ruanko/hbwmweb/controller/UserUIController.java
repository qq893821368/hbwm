package nchu.software.ruanko.hbwmweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserUIController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
