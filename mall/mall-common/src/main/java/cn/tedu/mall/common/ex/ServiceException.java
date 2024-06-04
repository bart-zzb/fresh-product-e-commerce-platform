package cn.tedu.mall.common.ex;

import cn.tedu.mall.common.constant.ServiceCode;
import lombok.Getter;

/**
 * 业务异常
 */
@Getter
public class ServiceException extends RuntimeException {

    private final ServiceCode serviceCode;

    /**
     * 创建业务异常对象
     *
     * @param serviceCode 业务状态码
     * @param message     描述文本
     */
    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }

}
