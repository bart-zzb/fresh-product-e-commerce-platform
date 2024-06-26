package cn.tedu.mall.common.constant;

public class ServiceConstant {
    public static final String CATEGORY_ALREADY_EXISTED = "商品类别已存在";

    public static final String CATEGORY_PARENT_NOT_EXIST = "商品父类不存在";

    public static final String CATEGORY_PARENT_NOT_ENABLE = "商品父类已禁用";

    public static final String CATEGORY_NOT_EXIST = "商品分类不存在";

    public static final String CATEGORY_CHILDREN_IS_EXISTED = "该商品分类还有子类,请先删除子类类别";

    public static final String ORDER_ITEMS_NOT_EXIST = "商品列表不能为空";

    public static final String ORDER_NOT_EXIST = "订单不存在";

    public static final String JWT_NOT_FOUND = "未登录, 请先登录";

    public static final String PRODUCT_ALREADY_EXISTED = "商品SPU已存在";

    public static final String BRAND_NOT_EXIST = "品牌不存在";

    public static final String ERROR_USERNAME_PASSWORD = "用户名或密码错误";

    public static final String PRODUCT_STOCK_NO_ENOUGH = "购买数量超过库存";

    public static final String ERROR_USERNAME_ALREADY_EXIST = "用户名已存在";

    public static final String PAY_FAILED = "支付失败";

    public static final String ADDRESS_UPDATE_FAILED = "更新地址信息不能为空";

    public static final String ADDRESS_INSERT_FAILED = "插入地址信息不能为空";

    public static final String LOAD_DATA_FAILED = "从数据库或者Redis中加载数据异常,请检查日志";

    public static final String PRODUCT_SPECS_NOT_EXIST = "商品SKU不存在";

    public static final String ORDER_CREATION_FAILED = "订单创建失败,请稍后重试";
}
