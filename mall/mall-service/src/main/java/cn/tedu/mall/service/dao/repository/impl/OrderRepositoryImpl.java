package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.mapper.OrderMapper;
import cn.tedu.mall.service.dao.repository.IOrderRepository;
import cn.tedu.mall.service.pojo.po.OrderPO;
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
    public List<OrderPO> getOrderByUserId(Long userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tb_user_id", userId);
        return orderMapper.selectList(queryWrapper);
    }
}
