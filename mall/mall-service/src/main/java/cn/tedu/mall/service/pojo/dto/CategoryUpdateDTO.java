package cn.tedu.mall.service.pojo.dto;


import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;

public class CategoryUpdateDTO {

    @NotBlank(message = "id不能为空")
    @ApiModelProperty(value = "id",required = true,dataType = "Long")
    Long id;

    @NotBlank(message = "父类别id不能为空")
    @ApiModelProperty(value = "父类别id",required = true,dataType = "Long")
    Long parentId;

    @NotBlank(message = "类别层级不能为空")
    @ApiModelProperty(value = "类别层级",required = true,dataType = "Integer")
    Integer level;

    @NotBlank(message = "排序优先级不能为空")
    @Range(min = 1, max = 9, message = "优先级只能是1-9之间")
    @ApiModelProperty(value = "排序优先级",required = true,dataType = "Integer")
    Integer sort;

    @NotBlank(message = "是否为父类不能为空")
    @Range(min = 1, max = 2, message = "优先级只能是1或2")
    @ApiModelProperty(value = "否为父类",required = true,dataType = "Integer")
    Integer isParent;

    @NotBlank(message = "类别名称不能为空")
    @ApiModelProperty(value = "类别名称",required = true,dataType = "String")
    String categoryName;

    @NotBlank(message = "是否启用不能为空")
    @Range(min = 1, max = 2, message = "是否启用只能是1或2")
    @ApiModelProperty(value = "是否启用",required = true,dataType = "String")
    Integer enable;

    @NotBlank(message = "是否展示不能为空")
    @Range(min = 1, max = 2, message = "是否展示只能是1或2")
    @ApiModelProperty(value = "是否展示",required = true,dataType = "String")
    Integer isDisplay;
}
