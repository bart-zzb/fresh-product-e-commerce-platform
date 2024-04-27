package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.bo.OrderDetailBO;
import cn.tedu.mall.service.pojo.dto.OrderItemsAddDTO;
import cn.tedu.mall.service.pojo.dto.OrderUpdateDTO;
import cn.tedu.mall.service.pojo.vo.OrderDetailVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface IOrderService {
    OrderDetailVO addOrder(Long userId, List<OrderItemsAddDTO> orderItemsAddDTOS);

    void updateOrder(OrderUpdateDTO orderUpdateDTO);

    List<OrderDetailBO> getOrderByUserId(Long userId);

    OrderDetailBO getOrderByUserIdAndOrderNo(Long id, String orderNo);
}
