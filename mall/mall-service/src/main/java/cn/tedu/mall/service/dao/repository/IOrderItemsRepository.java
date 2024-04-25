package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.po.OrderItemsPO;

import java.util.List;

public interface IOrderItemsRepository {

    OrderItemsPO addOrderItemsByProductSpecIdAndAmount(Long tbProductSpecId, Long orderId, Integer amount);

    List<OrderItemsPO> getOrderItemsByOrderId(Long orderId);
}
