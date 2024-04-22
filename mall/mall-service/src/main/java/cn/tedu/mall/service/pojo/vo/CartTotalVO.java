package cn.tedu.mall.service.pojo.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartTotalVO implements Serializable {
    //选中商品总价
    BigDecimal TotalPrice;
    //选中商品数量
    Integer totalAmount;
    //是否全部选中
    boolean allChecked;
}
