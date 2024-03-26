package cn.tedu.mall.common.ex;


import cn.tedu.mall.common.web.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常
     */
    @ExceptionHandler(SpSelfException.class)
    public JsonResult businessException1(SpSelfException e)
    {
        log.debug(e.getMessage());
        return JsonResult.failed(e.getMessage());
    }


    /**
     * DTO校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException .class)
    public JsonResult bingException1(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
            }
        }
        log.debug(e.getMessage());
        return JsonResult.failed(sb.toString());
    }
    ///**
    // * 控制器校验异常 直接参数notNull那里
    // */
    //@ExceptionHandler(ConstraintViolationException.class)
    //public JsonResult constraintViolationException1(ConstraintViolationException e)
    //{
    //    log.debug(e.getMessage());
    //    return JsonResult.failed(e.getMessage().split(": ")[1]);
    //}
    /**
     * 兜底异常
     */
    @ExceptionHandler(Exception.class)
    public JsonResult exception1(Exception e)
    {
        e.printStackTrace();
        log.debug(e.getMessage());
        return JsonResult.failed("服务器正忙,请稍后重试");
    }

}
