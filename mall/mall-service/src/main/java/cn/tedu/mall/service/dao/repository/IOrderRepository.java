package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.bo.OrderDetailBO;
import cn.tedu.mall.service.pojo.po.OrderPO;

import java.util.List;

public interface IOrderRepository {
    OrderPO getOrderByIdAndUserId(Long id, Long userId);

    int saveOrder(OrderPO orderPO);

    List<OrderDetailBO> getOrderByUserId(Long userId);

    OrderPO addBlankOrderByUserId(Long userId);

    OrderDetailBO getOrderByUserIdAndOrderNo(Long userId, String orderNo);

    List<OrderDetailBO> getOrdersByStatus(Long userId, Integer status);

    OrderDetailBO getUnpaidOrderByOrderNo(String orderNo);
}
