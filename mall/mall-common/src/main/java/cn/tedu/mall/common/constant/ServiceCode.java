package cn.tedu.mall.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务状态码
 */
@AllArgsConstructor
@Getter
public enum ServiceCode {

    /**
     * 操作成功
     */
    OK(20000),
    /**
     * 错误：请求参数格式错误
     */
    ERROR_BAD_REQUEST(40000),
    /**
     * 错误：未认证
     */
    ERROR_UNAUTHORIZED(40100),
    /**
     * 错误：未认证，因为被禁用
     */
    ERROR_UNAUTHORIZED_DISABLED(40101),
    /**
     * 错误：禁止访问，用于无权限
     */
    ERROR_FORBIDDEN(40300),
    /**
     * 错误：数据不存在
     */
    ERROR_NOT_FOUND(40400),
    /**
     * 错误：数据冲突
     */
    ERROR_CONFLICT(40900),
    /**
     * 错误：未知的插入数据失败
     */
    ERROR_INSERT(50000),
    /**
     * 错误：未知的删除数据失败
     */
    ERROR_DELETE(50100),
    /**
     * 错误：未知的修改数据失败
     */
    ERROR_UPDATE(50200),
    /**
     * 错误：JWT已过期
     */
    ERR_JWT_EXPIRED(60000),
    /**
     * 错误：JWT验证签名失败，可能使用了伪造的JWT
     */
    ERR_JWT_SIGNATURE(60100),
    /**
     * 错误：JWT格式错误
     */
    ERR_JWT_MALFORMED(60200),
    /**
     * 错误：登录名或密码错误
     */
    ERR_USERNAME_PASSWORD(60300),
    /**
     * 错误：JWT不存在
     */
    ERR_JWT_NOT_EXIST(60400),

    ERR_USERNAME_ALREADY_EXIST(60500),

    ERR_ORDER_ALREADY_PAID(70000),

    ERR_ORDER_NOT_EXIST(70100),
    /**
     * 错误：上传的文件为空（没有选择有效的文件）
     */
    ERROR_UPLOAD_EMPTY(90000),
    /**
     * 错误：上传的文件类型有误
     */
    ERROR_UPLOAD_INVALID_TYPE(90100),
    /**
     * 错误：上传的文件超出限制
     */
    ERROR_UPLOAD_EXCEED_MAX_SIZE(90200),
    /**
     * 错误：其它异常
     */
    ERROR_UNKNOWN(99999),

    ERROR_STOCK_NO_ENOUGH(30000),

    ERROR_PAY_FAILED(30100),

    ERROR_ADDRESS_UPDATE_FAILED(30200),

    ERROR_ADDRESS_INSERT_FAILED(30300),

    JSON_PROCESSING_FAILED(30400),

    ERROR_LOAD_DATA_FAILED(30500),

    ERROR_PRODUCT_SPECS_NOT_EXIST(30600),

    ERROR_ORDER_CREATION_FAILED(30700);
    /**
     * 枚举对象的值
     */
    private final Integer value;

}
