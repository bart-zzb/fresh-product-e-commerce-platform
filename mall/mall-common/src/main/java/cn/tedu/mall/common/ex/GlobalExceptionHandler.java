package cn.tedu.mall.common.ex;


import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.web.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
        log.info("创建全局异常处理器对象：GlobalExceptionHandler");
    }

    @ExceptionHandler
    public JsonResult handleServiceException(ServiceException e) {
        log.debug("全局异常处理器开始处理ServiceException");
        return JsonResult.fail(e);
    }

    @ExceptionHandler
    public JsonResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        log.debug("全局异常处理器开始处理MethodArgumentTypeMismatchException");
        return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, "参数转换失败，请确保参数符合类型:"+ e.getMessage());
    }

    @ExceptionHandler
    public JsonResult handleBindException(BindException e) {
        log.debug("全局异常处理器开始处理BindException");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("请求参数格式错误，");
        stringBuilder.append(e.getFieldError().getDefaultMessage());
        stringBuilder.append("！");
        String message = stringBuilder.toString();
        log.warn(message);
        return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, message);
    }

    @ExceptionHandler
    public JsonResult handleConstraintViolationException(ConstraintViolationException e) {
        log.debug("全局异常处理器开始处理ConstraintViolationException");
        StringBuilder stringBuilder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            stringBuilder.append(constraintViolation.getMessage());
        }
        String message = stringBuilder.toString();
        return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, message);
    }


    @ExceptionHandler
    public JsonResult handleThrowable(Throwable e) {
        log.debug("全局异常处理器开始处理Throwable");
        log.debug("异常跟踪信息如下：", e);
        String message = "服务器忙，请稍后再试!";
        return JsonResult.fail(ServiceCode.ERROR_UNKNOWN, message);
    }

}
