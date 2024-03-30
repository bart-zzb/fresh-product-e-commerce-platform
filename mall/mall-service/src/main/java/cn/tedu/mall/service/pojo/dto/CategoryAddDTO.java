package cn.tedu.mall.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryAddDTO implements Serializable {

    @NotNull(message = "父类别id不能为空")
    @ApiModelProperty(value = "父类别id",required = true,dataType = "Long")
    Long parentId;

    @NotNull(message = "层级类别不能为空")
    @ApiModelProperty(value = "父类别id",required = true,dataType = "Integer")
    Integer level;

    @NotBlank(message = "类别名称不能为空")
    @ApiModelProperty(value = "类别名称",required = true,dataType = "String")
    String categoryName;

    @NotNull(message = "排序不能为空")
    @ApiModelProperty(value = "父类别id",required = true,dataType = "Integer")
    Integer sort;

    @NotNull(message = "是否为父类不能为空")
    @ApiModelProperty(value = "父类别id",required = true,dataType = "Integer")
    Integer isParent;

    @NotNull(message = "是否启用不能为空")
    @ApiModelProperty(value = "父类别id",required = true,dataType = "Integer")
    Integer enable;

    @NotNull(message = "是否展示不能为空")
    @ApiModelProperty(value = "父类别id",required = true,dataType = "Integer")
    Integer isDisplay;
}
