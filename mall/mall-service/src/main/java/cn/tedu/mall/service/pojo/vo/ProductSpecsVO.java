package cn.tedu.mall.service.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import springfox.documentation.spring.web.json.Json;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSpecsVO implements Serializable {
    Long id;

    Long tbProductId;

    String ProductName;

    String specsName;

    String imgUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    BigDecimal currentPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    BigDecimal originalPrice;

    Integer amount;

    Integer sales;

    Integer status;

    String unit;

    String info;
}
