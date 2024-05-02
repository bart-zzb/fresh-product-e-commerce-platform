package cn.tedu.mall.service.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailVO implements Serializable {
    Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime modifiedTime;

    String orderNo;

    Integer status;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
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

