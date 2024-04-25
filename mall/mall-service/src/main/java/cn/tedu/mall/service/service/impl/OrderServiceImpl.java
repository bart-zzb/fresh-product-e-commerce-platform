package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.util.CalUtils;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.IOrderItemsRepository;
import cn.tedu.mall.service.dao.repository.IOrderRepository;
import cn.tedu.mall.service.dao.repository.IProductSpecsRepository;
import cn.tedu.mall.service.pojo.dto.OrderItemsAddDTO;
import cn.tedu.mall.service.pojo.dto.OrderUpdateDTO;
import cn.tedu.mall.service.pojo.po.OrderItemsPO;
import cn.tedu.mall.service.pojo.po.OrderPO;
import cn.tedu.mall.service.pojo.vo.OrderDetailVO;
import cn.tedu.mall.service.pojo.vo.OrderItemsVO;
import cn.tedu.mall.service.pojo.vo.OrderVO;
import cn.tedu.mall.service.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Primary
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderItemsRepository orderItemsRepository;

    @Override
    public OrderDetailVO addOrder(Long userId, List<OrderItemsAddDTO> orderItemsAddDTOS) {
        log.debug("商品详情入参{}", orderItemsAddDTOS);
        if(orderItemsAddDTOS.isEmpty()){
            throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST, ServiceConstant.ORDER_ITEMS_NOT_EXIST);
        }
        //生成空订单, 获取订单id, 生成OrderPO
        OrderPO orderPO = orderRepository.addBlankOrderByUserId(userId);

        List<OrderItemsPO> orderItemsPOS = new ArrayList<>();
        List<OrderItemsVO> orderItemsVOS = new ArrayList<>();
        //根据商品SKUid, 订单id, 商品SKU数量生成订单详情信息, 并封装成orderItemsVOS
        for (OrderItemsAddDTO orderItemsAddDTO : orderItemsAddDTOS) {
            OrderItemsPO orderItemsPO = orderItemsRepository.addOrderItemsByProductSpecIdAndAmount(orderItemsAddDTO.getTbProductSpecId(), orderPO.getId(), orderItemsAddDTO.getAmount());
            orderItemsPOS.add(orderItemsPO);
            orderItemsVOS.add(PojoConvert.convert(orderItemsPO, OrderItemsVO.class));
        }

        //orderPO转化成orderDetailVO
        OrderDetailVO orderDetailVO = PojoConvert.convert(orderPO, OrderDetailVO.class);
        //将orderItemsVOS赋值到orderDetailVO中
        orderDetailVO.setOrderItemsVOS(orderItemsVOS);
        BigDecimal total = new BigDecimal(0);
        for (OrderItemsVO orderItemsVO : orderItemsVOS) {
            total = total.add(orderItemsVO.getTotalPrice());
        }
        orderDetailVO.setOrderAmountTotal(total);
        return orderDetailVO;
    }

    @Override
    public void updateOrder(OrderUpdateDTO orderUpdateDTO) {
        OrderPO orderPO = orderRepository.getOrderByIdAndUserId(orderUpdateDTO.getId(), orderUpdateDTO.getTbUserId());
        if(orderPO==null){
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, ServiceConstant.ORDER_NOT_EXIST);
        }
        orderPO.setStatus(orderUpdateDTO.getStatus());
        orderRepository.saveOrder(orderPO);
    }

    @Override
    public List<OrderVO> getOrderByUserId(Long userId) {
        return orderRepository.getOrderByUserId(userId);
    }

    @Override
    public OrderDetailVO getOrderByUserIdAndOrderNo(Long userId, String orderNo) {
        OrderPO orderPO = orderRepository.getOrderByUserIdAndOrderNo(userId, orderNo);
        OrderDetailVO orderDetailVO = PojoConvert.convert(orderPO, OrderDetailVO.class);
        if (orderPO!=null && orderPO.getId()!=null){
            List<OrderItemsPO> orderItemsPOS = orderItemsRepository.getOrderItemsByOrderId(orderPO.getId());
            List<OrderItemsVO> orderItemsVOS = PojoConvert.convertList(orderItemsPOS, OrderItemsVO.class);
            if (orderDetailVO != null){
                BigDecimal total = new BigDecimal(0);
                orderDetailVO.setOrderItemsVOS(orderItemsVOS);
                for (OrderItemsVO orderItemsVO : orderItemsVOS) {
                    total = total.add(orderItemsVO.getTotalPrice());
                }
                orderDetailVO.setOrderAmountTotal(total);
            }
        }
        return orderDetailVO;
    }
}
