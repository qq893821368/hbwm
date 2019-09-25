package nchu.software.ruanko.hbwmweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {
    @GetMapping("/")
    public String index(){
        return "play";
    }
}
