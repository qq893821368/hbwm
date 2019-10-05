package nchu.software.ruanko.hbwmbl.controller;

import com.aliyuncs.exceptions.ClientException;
import nchu.software.ruanko.hbwmbl.impl.UserImpl;
import nchu.software.ruanko.hbwmcommon.model.User;
import nchu.software.ruanko.hbwmutil.util.EmailUtil;
import nchu.software.ruanko.hbwmutil.util.MessageUtil;
import nchu.software.ruanko.hbwmutil.util.StringUtil;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ComponentScan("nchu")
@Controller
public class UserController {
    @Autowired
    UserImpl impl;

    @RequestMapping("/verifyImpl")
    @ResponseBody
    public Boolean verify(HttpServletRequest request, HttpServletResponse response, String account, String password, Model model) throws IOException, ServletException {
        String user = impl.query(account);
        String msg = impl.check(account, password, user);
        String message;
        switch (msg){
            case "not found":
                message = "未找到该用户, 请检查所输入用户名";
                break;
            case "account or password not be exit":
                message = "用户名或密码不存在";
                break;
            case "account mismatch":
                message = "用户名不匹配";
                break;
            case "password mismatch":
                message = "密码错误, 请检查所输入密码";
                break;
            case "access":
                message = "access";
                break;
            default:
                message = "未知错误";
        }
        request.getSession().setAttribute("msg", message);
        request.getRequestDispatcher("/verify").forward(request, response);
        //response.sendRedirect("/verify");
        if(message.equals("access"))
            return true;
        return false;
    }

    @RequestMapping("/registerImpl")
    @ResponseBody
    public Boolean register(HttpServletRequest request, HttpServletResponse response, User registrant, Model model) throws IOException, ServletException {
        String user = impl.query(registrant.getAccount());
        String message;
        if(!user.equals("not found"))
            message = "Account already exist";
        else
            message = impl.register(registrant);
        request.getSession().setAttribute("msg", message);
        request.getRequestDispatcher("/register").forward(request, response);
        if(message.equals("success"))
            return true;
        return false;
    }

    @ResponseBody
    @RequestMapping("/sendCode")
    public String sendCode(HttpServletRequest request, HttpServletResponse response, String addressee, boolean isPhone) throws EmailException, ClientException {
        String captcha = impl.createCaptcha(true, 6);
        if(impl.sendCaptcha(addressee, isPhone, captcha)){
            request.getSession().setAttribute("captcha", captcha);
            return captcha;
        }
        else {
            request.getSession().setAttribute("msg", "cannot send captcha");
            return null;
        }
    }
}
