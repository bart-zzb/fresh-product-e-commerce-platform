package cn.tedu.mall.service.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("e_mall.tb_order_items")
public class OrderItemsPO implements Serializable {
    @TableId(type = IdType.AUTO)
    Long id;

    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modifiedTime;

    Long tbOrderId;

    Long tbProductId;

    String tbProductName;

    Long tbProductSpecId;

    String specName;

    String imgUrl;

    BigDecimal price;

    Integer amount;

    Long tbCategoryId;

    String tbCategoryName;

    Long tbBrandId;

    String tbBrandName;

    Integer iftIntegration;
}
