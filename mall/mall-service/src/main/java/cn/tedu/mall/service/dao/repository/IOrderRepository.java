package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.po.OrderPO;
import cn.tedu.mall.service.pojo.vo.OrderVO;

import java.util.List;

public interface IOrderRepository {
    OrderPO getOrderByIdAndUserId(Long id, Long userId);

    int saveOrder(OrderPO orderPO);

    List<OrderVO> getOrderByUserId(Long userId);
}
