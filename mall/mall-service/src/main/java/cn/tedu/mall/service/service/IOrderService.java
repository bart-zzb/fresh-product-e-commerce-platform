package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.bo.OrderDetailBO;
import cn.tedu.mall.service.pojo.dto.OrderItemsAddDTO;
import cn.tedu.mall.service.pojo.dto.OrderUpdateDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface IOrderService {
    OrderDetailBO addOrder(Long userId, List<OrderItemsAddDTO> orderItemsAddDTOS);

    int updateOrder(OrderDetailBO orderDetailBO);

    List<OrderDetailBO> getOrderByUserId(Long userId);

    OrderDetailBO getOrderByUserIdAndOrderNo(Long userId, String orderNo);

    List<OrderDetailBO> getOrdersByStatus(Long userId, Integer status);
}
