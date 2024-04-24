package cn.tedu.mall.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemsAddDTO implements Serializable {
    @NotNull(message = "SKU id不能为空")
    @ApiModelProperty(value = "SKU id",required = true,dataType = "Long")
    Long tbProductSpecId;

    @Range(min = 1, message = "商品数量必须为1以上")
    @ApiModelProperty(value = "商品数量",required = true,dataType = "Integer")
    Integer amount;
}
