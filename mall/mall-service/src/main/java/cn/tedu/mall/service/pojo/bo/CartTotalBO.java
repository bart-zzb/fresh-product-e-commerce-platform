package cn.tedu.mall.service.pojo.bo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartTotalBO implements Serializable {
    BigDecimal TotalPrice;
    //选中商品数量
    Integer totalAmount;
    //是否全部选中
    boolean allChecked;
}
