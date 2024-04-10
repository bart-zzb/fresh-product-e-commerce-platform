package cn.tedu.mall.service.pojo.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartVO {
    Long id;

    Long tbUserId;

    Long tbProductId;

    String tbProductName;

    Integer tbProductChecked;

    Long tbProductSpecId;

    String specName;

    String imgUrl;

    BigDecimal price;

    Integer amount;

    BigDecimal productAmountTotal;
}
