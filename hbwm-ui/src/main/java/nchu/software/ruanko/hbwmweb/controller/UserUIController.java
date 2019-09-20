package nchu.software.ruanko.hbwmweb.controller;

import org.apache.http.HttpResponse;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserUIController {
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String msg = null;
        if((msg = (String) request.getSession().getAttribute("msg")) != null)
            if(msg.equals("access")){
                return "index";
            }
        return "login";
    }

    @RequestMapping("/verify")
    public void verify(Model model, HttpServletRequest request, HttpServletResponse response, String account, String password) throws IOException, ServletException {
        String msg = null;
        if((msg = (String) request.getSession().getAttribute("msg")) != null) {
            if (msg.equals("access")) {
                request.getSession().setAttribute("user", account);
                response.sendRedirect("/home");
            }
            else
                response.sendRedirect("/login");
            //model.addAttribute("message", msg);
            request.getSession().removeAttribute("msg");
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
