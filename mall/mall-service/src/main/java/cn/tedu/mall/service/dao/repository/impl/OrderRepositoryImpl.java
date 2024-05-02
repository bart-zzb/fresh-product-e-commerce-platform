package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.mapper.OrderMapper;
import cn.tedu.mall.service.dao.repository.IOrderRepository;
import cn.tedu.mall.service.pojo.bo.OrderDetailBO;
import cn.tedu.mall.service.pojo.po.OrderPO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Primary
@Repository
public class OrderRepositoryImpl implements IOrderRepository {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderPO getOrderByIdAndUserId(Long id, Long userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        queryWrapper.eq("tb_user_id", userId);
        return orderMapper.selectOne(queryWrapper);
    }

    @Override
    public int saveOrder(OrderPO orderPO) {
        log.debug("orderPO" + orderPO);
        return orderMapper.updateById(orderPO);
    }

    @Override
    public List<OrderDetailBO> getOrderByUserId(Long userId) {
        List<OrderPO> orderPOS = orderMapper.selectOrderByUserId(userId);
        return PojoConvert.convertList(orderPOS, OrderDetailBO.class);
    }

    @Override
    public OrderPO addBlankOrderByUserId(Long userId) {
        OrderPO orderPO = new OrderPO();
        orderPO.setStatus(0);
        orderPO.setTbUserId(userId);
        orderPO.setTbAddressId(0L);
        orderPO.setPayChannel(0);
        orderMapper.insert(orderPO);
        return orderPO;
    }

    @Override
    public OrderDetailBO getOrderByUserIdAndOrderNo(Long userId, String orderNo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tb_user_id", userId);
        queryWrapper.eq("order_no", orderNo);
        OrderPO orderPO = orderMapper.selectOne(queryWrapper);
        return PojoConvert.convert(orderPO, OrderDetailBO.class);
    }

    @Override
    public List<OrderDetailBO> getOrdersByStatus(Long userId, Integer status) {
        List<OrderPO> orderPOS = orderMapper.selectOrdersByStatus(userId, status);
        return PojoConvert.convertList(orderPOS, OrderDetailBO.class);
    }
}
