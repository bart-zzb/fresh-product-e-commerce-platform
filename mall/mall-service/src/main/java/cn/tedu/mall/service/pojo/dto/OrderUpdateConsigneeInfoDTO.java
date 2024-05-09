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
public class OrderUpdateConsigneeInfoDTO implements Serializable {
    @ApiModelProperty(value = "用户id",required = true,dataType = "Long")
    Long tbUserId;

    @NotBlank(message = "订单编号不能为空")
    @ApiModelProperty(value = "订单编号",required = true,dataType = "String")
    String orderNo;

    String consignee;

    String consigneePhone;

    String consigneeAddress;
}
