package nchu.software.ruanko.hbwmbl.controller;


import nchu.software.ruanko.hbwmbl.impl.SingerImpl;
import nchu.software.ruanko.hbwmcommon.model.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    SingerImpl impl;
    @GetMapping(value = "/admin/singer")
        public String singer(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
            List<Singer> list = null;
            list = impl.findsinger();
            model.addAttribute("list",list);
        return "singerlist";
    }
    @GetMapping(value = "/select",produces = "text/html;charset=UTF-8")
    public String select(Model model, HttpServletRequest request, HttpServletResponse response,String id) throws ServletException,IOException{
        Singer s = new Singer();
        System.out.println(id);
        int singer_id = Integer.parseInt(id);
        s = impl.findsingerbyid(singer_id);
        model.addAttribute("singer",s);
        return "check";
    }
    @RequestMapping(value = "/increase")
    public String increase(){
        return "addsinger";
    }

    @GetMapping(value = "/addsinger")
    public String add(HttpServletRequest request, HttpServletResponse response,String singer_name,String intro,String exp,String singer_pic,String nation) throws ServletException,IOException{
        boolean list = false;
        list = impl.addsinger(singer_name,intro,exp,singer_pic,nation);
        request.setAttribute("list",list);
        return "redirect:/admin/singer";
    }
   @GetMapping(value = "/delete")
    public String delete(Model model,String btn,HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        boolean list = false;
        String bt = request.getParameter("btn");
        System.out.println(bt);
        list = impl.delete(bt);
        model.addAttribute("list",list);
        return "redirect:/admin/singer";
    }
    @RequestMapping(value = "/alter")
    public String alter(Model model,HttpServletRequest request, HttpServletResponse response,String id)throws ServletException,IOException{
//        System.out.println(id);
//        model.addAttribute("singer_id",id);
//        return "modify";
        Singer sin = new Singer();
        System.out.println(id);
        int singer_id = Integer.parseInt(id);
        sin = impl.findsingerbyid(singer_id);
        model.addAttribute("singer",sin);
        return "modify";
    }
    @GetMapping(value = "/modify")
    public String modify(HttpServletRequest request, HttpServletResponse response,String singer_pic,String singer_name,String nation,String intro,String exp)throws ServletException,IOException{
        boolean list = false;
       String id = request.getParameter("singer_id");
        System.out.println(id);
        int singer_id = Integer.parseInt(id);
        list = impl.modifysinger(singer_pic,singer_id,singer_name,nation,intro,exp);
        request.setAttribute("list",list);
        return "redirect:/admin/singer";
    }


}
