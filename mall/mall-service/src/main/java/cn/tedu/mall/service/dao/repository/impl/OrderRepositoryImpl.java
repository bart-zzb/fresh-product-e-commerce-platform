package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.mapper.OrderMapper;
import cn.tedu.mall.service.dao.repository.IOrderRepository;
import cn.tedu.mall.service.pojo.dto.OrderItemsAddDTO;
import cn.tedu.mall.service.pojo.po.OrderItemsPO;
import cn.tedu.mall.service.pojo.po.OrderPO;
import cn.tedu.mall.service.pojo.vo.OrderVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return orderMapper.updateById(orderPO);
    }

    @Override
    public List<OrderVO> getOrderByUserId(Long userId) {
        return orderMapper.selectOrderByUserId(userId);
    }

    @Override
    public void updateOrderByOrderItemsPOS(List<OrderItemsPO> orderItemsPOS) {

    }

    @Override
    public Long addBlankOrderByUserId(Long userId) {
        OrderPO orderPO = new OrderPO();
        orderPO.setStatus(0);
        orderPO.setTbUserId(userId);
        orderPO.setTbAddressId(0L);
        orderPO.setPayChannel(0);
        orderMapper.insert(orderPO);
        return orderPO.getId();
    }
}
