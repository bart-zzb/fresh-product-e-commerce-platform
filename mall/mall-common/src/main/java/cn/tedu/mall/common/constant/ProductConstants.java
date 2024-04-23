package cn.tedu.mall.common.constant;

public enum ProductConstants {
    //商品选中
    CHECKED(1),
    //商品未选中
    UNCHECKED(0);

    private Integer value;

    ProductConstants(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
