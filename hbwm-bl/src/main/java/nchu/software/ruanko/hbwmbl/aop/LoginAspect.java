package nchu.software.ruanko.hbwmbl.aop;

import com.j256.simplemagic.logger.Logger;
import com.j256.simplemagic.logger.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LoginAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* nchu.software.ruanko.hbwmbl.controller.UserController.verify(..))")
    public void login(){}

    @AfterReturning(pointcut = "login()", returning = "flag")
    public void loginRegister(JoinPoint jp, Object flag) throws Throwable{
        String account = (String) jp.getArgs()[2];
        HttpServletRequest request = (HttpServletRequest) jp.getArgs()[0];
        if((boolean)flag)
            logger.info("[" + account + "]登录成功");
        else
            logger.info("[" + account + "]登录失败");
    }
}
