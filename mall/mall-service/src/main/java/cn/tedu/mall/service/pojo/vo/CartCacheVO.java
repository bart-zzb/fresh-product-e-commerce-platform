package cn.tedu.mall.service.pojo.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartCacheVO implements Serializable {
    Long tbProductId;

    String tbProductName;

    Integer tbProductChecked;

    Long tbProductSpecId;

    String specsName;

    String imgUrl;

    BigDecimal price;

    Integer amount;

    BigDecimal productAmountTotal;
}
