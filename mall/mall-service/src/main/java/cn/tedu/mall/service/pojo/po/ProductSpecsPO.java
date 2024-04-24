package cn.tedu.mall.service.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import springfox.documentation.spring.web.json.Json;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("e_mall.tb_product_specs")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSpecsPO implements Serializable {
    @TableId(type = IdType.AUTO)
    Long id;

    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modifiedTime;

    Long tbProductId;

    String specsName;

    String imgUrl;

    Json productSpecs;

    BigDecimal currentPrice;

    BigDecimal originalPrice;

    Integer amount;

    Integer sales;

    Integer status;

    String info;
}