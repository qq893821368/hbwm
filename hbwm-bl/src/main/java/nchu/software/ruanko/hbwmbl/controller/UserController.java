package nchu.software.ruanko.hbwmbl.controller;

import nchu.software.ruanko.hbwmbl.impl.UserImpl;
import nchu.software.ruanko.hbwmcommon.model.User;
import nchu.software.ruanko.hbwmda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ComponentScan("nchu")
@Controller
public class UserController {
    @Autowired
    UserImpl impl;


    @RequestMapping("/verifyImpl")
    public void verify(HttpServletRequest request, HttpServletResponse response, String account, String password) throws IOException, ServletException {
        String user = impl.query(account);
        String msg = impl.check(account, password, user);
        switch (msg){
            case "not found":
                request.getSession().setAttribute("msg", "未找到该用户, 请检查所输入用户名");
                break;
            case "account or password not be exit":
                request.getSession().setAttribute("msg", "用户名或密码不存在");
                break;
            case "account mismatch":
                request.getSession().setAttribute("msg", "用户名不匹配");
                break;
            case "password mismatch":
                request.getSession().setAttribute("msg", "密码错误, 请检查所输入密码");
                break;
            case "access":
                request.getSession().setAttribute("msg", "access");
                break;
            default:
                request.getSession().setAttribute("msg", "未知错误");
        }
        request.getRequestDispatcher("/verify").forward(request, response);
        return ;
    }


}
