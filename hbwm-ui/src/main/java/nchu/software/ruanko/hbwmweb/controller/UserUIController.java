package nchu.software.ruanko.hbwmweb.controller;

import nchu.software.ruanko.hbwmbl.impl.DataAnalyzeImpl;
import nchu.software.ruanko.hbwmbl.impl.UserImpl;
import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmcommon.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller
public class UserUIController {
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        String msg = null;
        if((msg = (String) request.getSession().getAttribute("msg")) != null)
            if(msg.equals("access")){
                return "index";
            }
        return "login";
    }

    @RequestMapping("/register")
    public void register(HttpServletRequest request, HttpServletResponse response, User registrant, Model model) throws IOException, ServletException {
        String msg = null;
        try{
            msg = (String) request.getSession().getAttribute("msg");
        }catch (NullPointerException NPE){
            msg = null;
        }finally {
            request.getSession().removeAttribute("msg");
        }
        if(msg != null){
            if(msg.equals("success")){
                request.getSession().setAttribute("user", registrant.getAccount());
                response.sendRedirect("/home");
            }
            else {
                request.getSession().setAttribute("message", msg);
                response.sendRedirect("/register");
            }
            return ;
        }
        request.getRequestDispatcher("/registerImpl").forward(request, response);
        return ;
    }

    @RequestMapping("/verify")
    public void verify(HttpServletRequest request, HttpServletResponse response, String account, String password, Model model) throws IOException, ServletException {
        String msg = null;
        try{
            msg = (String) request.getSession().getAttribute("msg");
        }catch (NullPointerException NPE){
            msg = null;
        }finally {
            request.getSession().removeAttribute("msg");
        }
        if(msg != null) {
            if (msg.equals("access")) {
                request.getSession().setAttribute("user", account);
                response.sendRedirect("/home");
            }
            else{
                request.getSession().setAttribute("message", msg);
                response.sendRedirect("/login");
            }
            return ;
        }
        request.getRequestDispatcher("/verifyImpl").forward(request, response);
        return ;
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest request, HttpServletResponse response, String account){
        return "home";
    }
}
