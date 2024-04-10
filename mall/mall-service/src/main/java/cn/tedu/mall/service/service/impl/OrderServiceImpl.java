package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.service.dao.repository.IOrderRepository;
import cn.tedu.mall.service.pojo.dto.OrderAddDTO;
import cn.tedu.mall.service.pojo.dto.OrderItemsAddDTO;
import cn.tedu.mall.service.pojo.dto.OrderUpdateDTO;
import cn.tedu.mall.service.pojo.po.OrderPO;
import cn.tedu.mall.service.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public void addOrder(OrderAddDTO orderAddDTO) {
        List<OrderItemsAddDTO> orderItemsAddDTOList = orderAddDTO.getOrderItemsAddDTOList();
        if(orderItemsAddDTOList.isEmpty()){
            throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST,"商品列表不能为空");
        }
        //TODO 须调用商品的商品
    }

    @Override
    public void updateOrder(OrderUpdateDTO orderUpdateDTO) {
        OrderPO orderPO = orderRepository.getOrderByIdAndUserId(orderUpdateDTO.getId(), orderUpdateDTO.getTbUserId());
        if(orderPO==null){
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, "订单不存在");
        }
        orderPO.setStatus(orderUpdateDTO.getStatus());
        orderRepository.saveOrder(orderPO);
    }

    @Override
    public List<OrderPO> getOrderByUserId(Long userId) {
        return orderRepository.getOrderByUserId(userId);
    }
}
