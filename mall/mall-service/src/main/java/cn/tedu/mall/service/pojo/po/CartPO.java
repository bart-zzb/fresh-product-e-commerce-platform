package cn.tedu.mall.service.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("tb_shopping_cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartPO implements Serializable {
    @TableId(type = IdType.AUTO)
    Long id;

    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modifiedTime;

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
