package cn.tedu.mall.service.pojo.bo;

import cn.tedu.mall.service.pojo.vo.OrderItemsVO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailBO implements Serializable {
    Long id;

    LocalDateTime modifiedTime;

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

    List<OrderItemsVO> orderItemsVOS;

    String orderStatus;

    Integer payChannel;
}