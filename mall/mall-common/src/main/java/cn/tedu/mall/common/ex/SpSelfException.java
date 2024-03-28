package cn.tedu.mall.common.ex;

import cn.tedu.mall.common.constant.ResponseEnum;


public class SpSelfException extends RuntimeException{

    private ResponseEnum responseEnum;

    public SpSelfException() {
    }

    public SpSelfException(String message) {
        super(message);
    }

    public SpSelfException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpSelfException(Throwable cause) {
        super(cause);
    }

    public SpSelfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }

    public void setResponseEnum(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }
}
