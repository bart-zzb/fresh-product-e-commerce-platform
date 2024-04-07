package cn.tedu.mall.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartAddDTO implements Serializable {

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id",required = true,dataType = "Long")
    Long tbUserId;

    @NotNull(message = "SPU id不能为空")
    @ApiModelProperty(value = "SPU id",required = true,dataType = "Long")
    Long tbProductId;

    @NotBlank(message = "SPU名称不能为空")
    @ApiModelProperty(value = "SPU名称",required = true,dataType = "String")
    String tbProductName;

    @NotNull(message = "SKU id不能为空")
    @ApiModelProperty(value = "SKU id",required = true,dataType = "Long")
    Long tbProductSpecId;

    @NotBlank(message = "SKU规格不能为空")
    @ApiModelProperty(value = "SKU规格",required = true,dataType = "String")
    String specName;

    @NotBlank(message = "图片路径不能为空")
    @ApiModelProperty(value = "图片路径",required = true,dataType = "String")
    String imgUrl;

    @NotNull(message = "价格不能为空")
    @Pattern(regexp = "^\\d*(\\.\\d+)?$", message = "请输入正小数或零")
    @ApiModelProperty(value = "价格",required = true,dataType = "BigDecimal")
    BigDecimal price;

    @NotBlank(message = " 数量不能为空")
    @Pattern(regexp = "^\\d+$", message = "请输入正整数或零")
    @ApiModelProperty(value = "数量",required = true,dataType = "Integer")
    Integer amount;
}
