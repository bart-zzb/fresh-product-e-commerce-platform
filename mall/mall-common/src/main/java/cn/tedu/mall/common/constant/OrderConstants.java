package cn.tedu.mall.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderConstants {
    UNPAID(0, "待付款"),

    PAID(1 , "待发货"),

    DELIVERED(2 , "待收货"),

    TO_BE_RECEIVED(3, "待评价"),

    USER_SHUTDOWN(4,"用户关闭"),

    SELLER_SHUTDOWN(5,"平台关闭(商家)"),

    SYS_SHUTDOWN(6, "系统调度关闭");

    private final Integer value;

    private final String description;
}
