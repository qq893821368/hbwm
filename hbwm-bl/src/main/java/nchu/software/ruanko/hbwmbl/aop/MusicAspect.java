package nchu.software.ruanko.hbwmbl.aop;

import com.j256.simplemagic.logger.Logger;
import com.j256.simplemagic.logger.LoggerFactory;
import nchu.software.ruanko.hbwmbl.impl.DataAnalyzeImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan("nchu.software.ruanko.hbwmbl")
@Component
@Aspect
public class MusicAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DataAnalyzeImpl impl;

    @Pointcut("execution(* nchu.software.ruanko.hbwmbl.controller.UserController.getMusicInfoList(..))")
    public void play(){}

    @AfterReturning(pointcut = "play()", returning = "flag")
    public void loginRegister(JoinPoint jp, Object flag) throws Throwable{
        int[] ids = (int[]) jp.getArgs()[1];
        for(int id: ids){
            String logging = impl.play(id);
            logger.info(logging);
            impl.play(id);
        }
    }
}
