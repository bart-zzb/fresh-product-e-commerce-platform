package cn.tedu.mall.service.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    BigDecimal price;

    Integer amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    BigDecimal totalPrice;
}
