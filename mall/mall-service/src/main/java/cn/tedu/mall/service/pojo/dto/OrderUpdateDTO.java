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
public class OrderUpdateDTO implements Serializable {
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id",required = true,dataType = "Long")
    Long id;

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id",required = true,dataType = "Long")
    Long tbUserId;

    @NotNull(message = "订单状态不能为空")
    @Range(min = 0, max = 6, message = "订单状态只能是0-6之间")
    @ApiModelProperty(value = "订单状态",required = true,dataType = "Integer")
    Integer status;
}
