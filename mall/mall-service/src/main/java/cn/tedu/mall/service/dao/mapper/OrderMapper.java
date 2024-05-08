package cn.tedu.mall.service.dao.mapper;

import cn.tedu.mall.service.pojo.po.OrderPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OrderMapper extends BaseMapper<OrderPO> {
    List<OrderPO> selectOrderByUserId(Long userId);

    List<OrderPO> selectOrdersByStatus(Long userId, Integer status);

    int updateByOrderPO(OrderPO orderPO);
}
