package cn.tedu.mall.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserConstants {
    INDIVIDUAL(1, "个人"),

    ENTERPRISE(2 , "企业");

    private final Integer value;

    private final String description;
}