package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.dto.OrderAddDTO;
import cn.tedu.mall.service.pojo.dto.OrderUpdateDTO;
import cn.tedu.mall.service.pojo.vo.OrderVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IOrderService {
    void addOrder(OrderAddDTO orderAddDTO);

    void updateOrder(OrderUpdateDTO orderUpdateDTO);

    List<OrderVO> getOrderByUserId(Long userId);
}
