package cn.tedu.mall.common.handler;


import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.web.JsonResult;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
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
    public JsonResult handleTokenExpiredException(TokenExpiredException e) {
        log.debug("全局异常处理器开始处理TokenExpiredException:{}", e.getMessage());
        return JsonResult.fail(ServiceCode.ERR_JWT_EXPIRED, "已过期,请重新登录");
    }

    @ExceptionHandler
    public JsonResult handleTokenExpiredException(JWTVerificationException e) {
        log.debug("全局异常处理器开始处理JWTVerificationException:{}", e.getMessage());
        return JsonResult.fail(ServiceCode.ERR_JWT_NOT_EXIST, "未登录,请重新登录");
    }

    @ExceptionHandler
    public JsonResult handleServiceException(ServiceException e) {
        log.debug("全局异常处理器开始处理ServiceException:{}", e.getServiceCode().getValue() + ":" + e.getMessage());
        return JsonResult.fail(e.getServiceCode(), e.getMessage());
    }

    @ExceptionHandler
    public JsonResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.debug("全局异常处理器开始处理MethodArgumentTypeMismatchException");
        return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, "参数转换失败，请确保参数符合类型:" + e.getMessage());
    }

    @ExceptionHandler
    public JsonResult handleBindException(BindException e) {
        log.debug("全局异常处理器开始处理BindException");
        String message = "请求参数格式错误，" +
                Objects.requireNonNull(e.getFieldError()).getDefaultMessage() +
                "!";
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
