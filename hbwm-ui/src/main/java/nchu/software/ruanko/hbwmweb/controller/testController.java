package nchu.software.ruanko.hbwmweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {
    @GetMapping("/")
    public String index(){
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/reset")
    public String reset(){
        return "reset";
    }

    @GetMapping("/playMusic")
    public String play(){
        return "playMusic";
    }
}
