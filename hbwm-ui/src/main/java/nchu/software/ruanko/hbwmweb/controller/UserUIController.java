package nchu.software.ruanko.hbwmweb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonObject;
import nchu.software.ruanko.hbwmbase.domain.UserInfo;
import nchu.software.ruanko.hbwmbl.impl.DataAnalyzeImpl;
import nchu.software.ruanko.hbwmbl.impl.MusicInfoMapperImpl;
import nchu.software.ruanko.hbwmbl.impl.MusicRankMapperImpl;
import nchu.software.ruanko.hbwmbl.impl.UserImpl;
import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmcommon.model.MusicInfo;
import nchu.software.ruanko.hbwmcommon.model.User;
import nchu.software.ruanko.hbwmutil.util.AttributeUtil;
import nchu.software.ruanko.hbwmutil.util.JacksonUtil;
import nchu.software.ruanko.hbwmutil.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Controller
public class UserUIController {

    @Autowired
    MusicInfoMapperImpl musicInfoMapperImpl;
    @Autowired
    MusicRankMapperImpl musicRankMapperImpl;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        if(!StringUtil.isEmpty(AttributeUtil.getAttributeFromRequest(request, "user", false)))
            return "index";
        return "login";
    }

    @RequestMapping("/register")
    @ResponseBody
    public Map<String, Object> register(HttpServletRequest request, HttpServletResponse response, String account, String password, Model model) throws IOException, ServletException {
        String msg = AttributeUtil.getMsgFromRequest(request);
        if(!StringUtil.isEmpty(msg))
            return JacksonUtil.stringToJson(msg);
        request.getRequestDispatcher("/registerImpl").forward(request, response);
        return JacksonUtil.stringToJson(msg);
    }

    @RequestMapping("/verify")
    public void verify(HttpServletRequest request, HttpServletResponse response, String account, String password, Model model) throws IOException, ServletException {
        String msg = AttributeUtil.getMsgFromRequest(request);
        if(!StringUtil.isEmpty(msg)) {
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
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        if(request.getSession().getAttribute("recommendList") == null)
            request.getRequestDispatcher("/loadMusic").forward(request, response);
        return "home";
    }

    @RequestMapping("/login/qq")
    public void recall(HttpServletRequest request, HttpServletResponse response, String accessToken, String expiresIn, String state, Model model)throws Exception{
        if(StringUtil.isEmpty(request.getQueryString()))
            response.getWriter().print( "<!DOCTYPE html>" +
                                        "<html lang=\"zh-cn\">" +
                                        "<head></head>" +
                                        "<body>" +
                                        "   <script type=\"text/javascript\">" +
                                        "   location.href = location.href.replace('#', '&')" +
                                        ".replace('?&', '?').replace('access_token', 'accessToken').replace('expires_in', 'expiresIn')" +
                                        "   </script>" +
                                        "</body>" +
                                        "</html>");
        else
            request.getRequestDispatcher("/login/qq/transit").forward(request, response);
    }

    @RequestMapping("login/qq/transit")
    public String agent(HttpServletRequest request, HttpServletResponse response, String accessToken, String expiresIn, String state, Model model) throws ServletException, IOException {
        String msg = AttributeUtil.getMsgFromRequest(request);
        if(StringUtil.isEmpty(msg))
            request.getRequestDispatcher("/check/qq").forward(request, response);
        else if(msg.equals("success")){
            return "agent";
        }
        return "error";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        String origin = request.getHeader("referer");
        String account = AttributeUtil.getAttributeFromRequest(request, "user", false);
        if(!StringUtil.isEmpty(account))
            request.getRequestDispatcher("/logoutImpl?account="+account).forward(request, response);
        else
            return "redirect:"+origin;
        return "home";
    }



}
