package nchu.software.ruanko.hbwmutil.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginAntiInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        HttpSession session = request.getSession();
        if(session.getAttribute("user")!=null){
            response.sendRedirect("/index");
            return false;
        }
        else
            return true;
    }
}
