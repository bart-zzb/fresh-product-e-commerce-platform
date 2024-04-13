package cn.tedu.mall.service.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import springfox.documentation.spring.web.json.Json;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("e_mall.tb_product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductPO implements Serializable {
    @TableId(type = IdType.AUTO)
    Long id;

    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modifiedTime;

    String productName;

    Json attributeList;

    Long tbCategoryId;

    Long tbBrandId;

    Integer sales;

    Integer status;

    String info;
}
