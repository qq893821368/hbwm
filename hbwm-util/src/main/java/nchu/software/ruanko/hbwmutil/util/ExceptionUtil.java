package nchu.software.ruanko.hbwmutil.util;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ComponentScan("nchu.software.ruanko.hbwmui")
@ControllerAdvice
public class ExceptionUtil {
    @ExceptionHandler()
    public ModelAndView IllegalArgumentExceptionHnadler(IllegalArgumentException exception){
        ModelAndView modelAndView = new ModelAndView("illegalArgument");
        modelAndView.addObject("errCoz",exception.getCause());
        modelAndView.addObject("errMsg",exception.getMessage());
        return modelAndView;
    }

}
