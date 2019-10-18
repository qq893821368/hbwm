package nchu.software.ruanko.hbwmweb.controller;

import nchu.software.ruanko.hbwmbl.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class testController {
    @Autowired
    UserImpl impl;

    @GetMapping("/")
    public String index() {
        return "fuck";
    }

    @GetMapping("/fuck")
    public String fuck() {
        return "fuck";
    }

    @RequestMapping("/ModifyUser")
    public String ModifyUser(HttpServletRequest request, HttpServletResponse response, String account) throws ServletException, IOException {
        impl.ModifyUser(request, response, account);
        return "ModifyUser";
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, String account) throws ServletException, IOException {
        impl.update(request, response, account, (String) request.getSession().getAttribute("account"));
        return "update";
    }

    @GetMapping("/gai")
    public String gai() {
        return "gai";
    }

    @GetMapping("/gaivip")
    public String gaivip() {
        return "gaivip";
    }

    @GetMapping("/gaiaddress")
    public String gaiaddress() {
        return "gaiaddress";
    }

    @GetMapping("/gaigender")
    public String gaigender() {
        return "gaigender";
    }

    @GetMapping("/gaiintro")
    public String gaiintro() {
        return "gaiintro";
    }

    @GetMapping("/gaiisPublic")
    public String gaiisPublic() {
        return "gaiisPublic";
    }

    @RequestMapping("/VipUser")
    public String VipUser(HttpServletRequest request, HttpServletResponse response, String account) throws ServletException, IOException {
        impl.ModifyUser(request, response, account);
        return "VipUser";
    }

    @RequestMapping("/UserGender")
    public String UserGender(HttpServletRequest request, HttpServletResponse response, String user_id) throws ServletException, IOException {
        impl.ModifyUserinfo(request, response, user_id);
        return "UserGender";
    }

    @RequestMapping("/UserAddress")
    public String UserAddress(HttpServletRequest request, HttpServletResponse response, String user_id) throws ServletException, IOException {
        impl.ModifyUserinfo(request, response, user_id);
        return "UserAddress";
    }

    @RequestMapping("/UserIspublic")
    public String UserIspublic(HttpServletRequest request, HttpServletResponse response, String user_id) throws ServletException, IOException {
        impl.ModifyUserinfo(request, response, user_id);
        return "UserIspublic";
    }

    @RequestMapping("/UserIntro")
    public String UserIntro(HttpServletRequest request, HttpServletResponse response, String user_id) throws ServletException, IOException {
        impl.ModifyUserinfo(request, response, user_id);
        return "UserIntro";
    }

    @RequestMapping("/updatevip")
    public String updatevip(HttpServletRequest request, HttpServletResponse response, String isvip) throws ServletException, IOException {
        impl.updatevip(request, response, isvip, (String) request.getSession().getAttribute("account"));
        return "update";
    }

    @RequestMapping("/updateaddress")
    public String updateaddress(HttpServletRequest request, HttpServletResponse response, String address) throws ServletException, IOException {
        impl.updateaddress(request, response, address, (String) request.getSession().getAttribute("user_id"));
        return "update";
    }

    @RequestMapping("/updategender")
    public String updategender(HttpServletRequest request, HttpServletResponse response, String gender) throws ServletException, IOException {
        impl.updategender(request, response, gender, (String) request.getSession().getAttribute("user_id"));
        return "update";
    }

    @RequestMapping("/updateintro")
    public String updateintro(HttpServletRequest request, HttpServletResponse response, String intro) throws ServletException, IOException {
        impl.updateintro(request, response, intro, (String) request.getSession().getAttribute("user_id"));
        return "update";
    }

    @RequestMapping("/updateispublic")
    public String updateispublic(HttpServletRequest request, HttpServletResponse response, String ispublic) throws ServletException, IOException {
        impl.updateispublic(request, response, ispublic, (String) request.getSession().getAttribute("user_id"));
        return "update";
    }

    @RequestMapping("/ctrlUser")
    public String ctrl(Model model){
        return "ctrlUserInfo";
    }
}
