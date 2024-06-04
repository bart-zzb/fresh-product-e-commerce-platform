package cn.tedu.mall.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductConstants {
    CHECKED(1, "商品选中"),

    UNCHECKED(0, "商品未选中");

    private final Integer value;

    private final String description;
}
