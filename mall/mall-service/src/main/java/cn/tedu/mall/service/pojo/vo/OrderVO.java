package cn.tedu.mall.service.pojo.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderVO implements Serializable {
    Long id;

    String orderNo;

    Integer status;

    BigDecimal orderAmountTotal;

    Long tbUserId;

    Long tbAddressId;

    String consignee;

    String consigneePhone;

    String consigneeAddress;

    String outTradeNo;

    String tbUserNotes;
}
