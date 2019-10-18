package nchu.software.ruanko.hbwmweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminUIController {

    @RequestMapping("/uploadMusic")
    public String uploadMusic(){
        return "admin/uploadMusic";
    }

    @GetMapping("/echarts")
    public String echarts(){
        return "admin/echartsDemo";
    }


}
