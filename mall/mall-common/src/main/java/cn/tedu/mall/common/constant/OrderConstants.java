package cn.tedu.mall.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderConstants {
    UNPAID(0, "待支付"),

    PAID(1 , "已支付"),

    DELIVERED(2 , "已发货"),

    TO_BE_RECEIVED(3, "待收货"),

    USER_SHUTDOWN(4,"用户关闭"),

    SELLER_SHUTDOWN(5,"平台关闭(商家)"),

    SYS_SHUTDOWN(6, "系统调度关闭");

    private final Integer value;

    private final String description;
}
