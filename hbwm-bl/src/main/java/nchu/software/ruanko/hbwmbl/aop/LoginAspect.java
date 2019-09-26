package nchu.software.ruanko.hbwmbl.aop;

import com.j256.simplemagic.logger.Logger;
import com.j256.simplemagic.logger.LoggerFactory;
import nchu.software.ruanko.hbwmbl.impl.DataAnalyzeImpl;
import nchu.software.ruanko.hbwmutil.util.EmailUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@ComponentScan("nchu.software.ruanko.hbwmbl")
@Component
@Aspect
public class LoginAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DataAnalyzeImpl impl;

    @Pointcut("execution(* nchu.software.ruanko.hbwmbl.controller.UserController.verify(..))")
    public void login(){}

    @AfterReturning(pointcut = "login()", returning = "flag")
    public void loginRegister(JoinPoint jp, Object flag) throws Throwable{
        String account = (String) jp.getArgs()[2];
        //HttpServletRequest request = (HttpServletRequest) jp.getArgs()[0];
        String logging = impl.visit(account, (Boolean)flag);
        EmailUtil.sendCaptcha("893821368@qq.com", "7zU9");
        logger.info(logging);
    }
}
