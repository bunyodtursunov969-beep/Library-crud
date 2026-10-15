package uz.pdp.handlers;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.exceptions.NotFoundException;

@ControllerAdvice
public class GlobalException {

    /*@ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(HttpServletRequest request, NotFoundException ex){
        return ex.getMessage()+" Error code:  "+404;
    }*/

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException( NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage() );
        return modelAndView;
    }


}
