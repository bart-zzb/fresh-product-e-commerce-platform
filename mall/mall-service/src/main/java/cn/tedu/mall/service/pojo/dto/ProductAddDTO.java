package cn.tedu.mall.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductAddDTO implements Serializable {
    @NotBlank(message = "商品SPU不能为空")
    @ApiModelProperty(value = "商品SPU", required = true, dataType = "String")
    String productName;

    @NotNull(message = "商品类别id不能为空")
    @ApiModelProperty(value = "商品类别id", required = true, dataType = "Long")
    Long tbCategoryId;

    @NotNull(message = "品牌id不能为空")
    @ApiModelProperty(value = "品牌id", required = true, dataType = "Long")
    Long tbBrandId;

    @NotNull(message = "是否启用不能为空")
    @Range(min = 0, max = 1, message = "是否启用只能是0或1")
    @ApiModelProperty(value = "是否启用", required = true, dataType = "Integer")
    Integer status;

    @ApiModelProperty(value = "商品SPU备注", required = false, dataType = "String")
    String info;
}
