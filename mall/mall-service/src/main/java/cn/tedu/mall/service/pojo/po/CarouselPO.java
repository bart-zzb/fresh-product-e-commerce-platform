package cn.tedu.mall.service.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("e_mall.sys_index_carousel")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarouselPO implements Serializable {
    @TableId(type = IdType.AUTO)
    Long id;

    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modifiedTime;

    String imgUrl;

    Integer sort;
}
