package cn.tedu.mall.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartAddDTO implements Serializable {

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id",required = true,dataType = "Long")
    Long tbUserId;

    @NotNull(message = "SKU id不能为空")
    @ApiModelProperty(value = "SKU id",required = true,dataType = "Long")
    Long tbProductSpecId;

    @NotNull(message = " 数量不能为空")
    @Range(min = 0, message = "数量必须是零或者正整数")
    @ApiModelProperty(value = "数量",required = true,dataType = "Integer")
    Integer amount;
}
