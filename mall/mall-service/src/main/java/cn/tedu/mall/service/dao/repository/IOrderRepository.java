package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.po.OrderPO;

import java.util.List;

public interface IOrderRepository {
    OrderPO getOrderByIdAndUserId(Long id, Long userId);

    int saveOrder(OrderPO orderPO);

    List<OrderPO> getOrderByUserId(Long userId);
}
