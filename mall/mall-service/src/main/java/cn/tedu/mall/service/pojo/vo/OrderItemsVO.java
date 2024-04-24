package cn.tedu.mall.service.pojo.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemsVO implements Serializable {
    Long id;

    Long tbProductId;

    String tbProductName;

    Long tbProductSpecId;

    String specsName;

    String imgUrl;

    BigDecimal price;

    Integer amount;

    BigDecimal totalPrice;
}
