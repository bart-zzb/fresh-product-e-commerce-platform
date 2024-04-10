package cn.tedu.mall.service.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import springfox.documentation.spring.web.json.Json;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("e_mall.tb_order")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderPO implements Serializable {
    @TableId(type = IdType.AUTO)
    Long id;

    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime modifiedTime;

    String orderNo;

    Integer status;

    Json productMap;

    BigDecimal productAmountTotal;

    BigDecimal orderAmountTotal;

    BigDecimal freight;

    //积分
    Integer integration;

    BigDecimal integrationAmount;

    Long tbUserId;

    Long tbAddressId;

    String consignee;

    String consigneePhone;

    String consigneeAddress;

    Integer payChannel;

    String outTradeNo;

    LocalDateTime payTime;

    LocalDateTime deliveryTime;

    LocalDateTime completeTime;

    LocalDateTime cancelTime;

    String tbUserNotes;
}
