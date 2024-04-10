package cn.tedu.mall.service.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@TableName("e_mall.tb_category")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryPO implements Serializable {

    @TableId(type = IdType.AUTO)
    Long id;

    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modifiedTime;

    Long parentId;

    Integer level;

    Integer sort;

    Integer isParent;

    String categoryName;

    Integer enable;

    Integer isDisplay;
}
