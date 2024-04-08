package cn.tedu.mall.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryUpdateDTO {

    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id",required = true,dataType = "Long")
    Long id;

    @NotNull(message = "排序优先级不能为空")
    @Range(min = 1, max = 9, message = "优先级只能是1-9之间")
    @ApiModelProperty(value = "排序优先级",required = true,dataType = "Integer")
    Integer sort;

    @NotBlank(message = "类别名称不能为空")
    @ApiModelProperty(value = "类别名称",required = true,dataType = "String")
    String categoryName;

    @NotNull(message = "是否启用不能为空")
    @Range(min = 1, max = 2, message = "是否启用只能是1或2")
    @ApiModelProperty(value = "是否启用",required = true,dataType = "Integer")
    Integer enable;

    @NotNull(message = "是否展示不能为空")
    @Range(min = 1, max = 2, message = "是否展示只能是1或2")
    @ApiModelProperty(value = "是否展示",required = true,dataType = "Integer")
    Integer isDisplay;
}
