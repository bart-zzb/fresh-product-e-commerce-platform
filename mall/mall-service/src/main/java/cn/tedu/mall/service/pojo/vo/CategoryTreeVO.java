package cn.tedu.mall.service.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryTreeVO implements Serializable {
    @ApiModelProperty("类别id")
    Long id;

    @ApiModelProperty("类别名称")
    String categoryName;

    @ApiModelProperty("子级类别")
    List<CategoryTreeVO> children;
}
