package cn.tedu.mall.service.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import springfox.documentation.spring.web.json.Json;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderAddDTO implements Serializable {
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id",required = true,dataType = "Long")
    Long tbUserId;

    @NotNull(message = "用户地址表id不能为空")
    @ApiModelProperty(value = "用户地址表id",required = true,dataType = "Long")
    Long tbAddressId;

    @NotNull(message = "支付方式不能为空")
    @ApiModelProperty(value = "支付方式",required = true,dataType = "Integer")
    Integer payChannel;

    @ApiModelProperty(value = "备注",required = false,dataType = "String")
    String tbUserNotes;

    @Size(min = 1, message = "订单商品列表不能少于1个")
    @ApiModelProperty(value = "订单列表详情",required = true,dataType = "cn.tedu.mall.service.pojo.dto.OrderItemsAddDTO")
    List<OrderItemsAddDTO> orderItemsAddDTOList;
}
