package cn.tedu.mall.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderUpdatePaidDTO implements Serializable {
    @ApiModelProperty(value = "用户id", required = true, dataType = "Long")
    Long tbUserId;

    @NotBlank(message = "订单编号不能为空")
    @ApiModelProperty(value = "订单编号", required = true, dataType = "String")
    String orderNo;

    Integer payChannel;
}
