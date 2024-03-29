package cn.tedu.mall.service.pojo.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryVO implements Serializable {
    Long categoryId;

    Long parentId;

    Integer sort;

    Integer isParent;

    String categoryName;

    Integer enable;

    Integer isDisplay;
}
