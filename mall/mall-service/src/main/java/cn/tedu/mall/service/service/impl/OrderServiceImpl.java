package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.constant.OrderConstants;
import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.IOrderItemsRepository;
import cn.tedu.mall.service.dao.repository.IOrderRepository;
import cn.tedu.mall.service.pojo.bo.OrderDetailBO;
import cn.tedu.mall.service.pojo.dto.OrderItemsAddDTO;
import cn.tedu.mall.service.pojo.po.OrderItemsPO;
import cn.tedu.mall.service.pojo.po.OrderPO;
import cn.tedu.mall.service.pojo.vo.OrderItemsVO;
import cn.tedu.mall.service.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    public OrderDetailBO addOrder(Long userId, List<OrderItemsAddDTO> orderItemsAddDTOS) {
        log.debug("商品详情入参{}", orderItemsAddDTOS);
        if (orderItemsAddDTOS.isEmpty()) {
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

        BigDecimal total = new BigDecimal(0);
        for (OrderItemsVO orderItemsVO : orderItemsVOS) {
            total = total.add(orderItemsVO.getTotalPrice());
        }
        orderPO.setOrderAmountTotal(total);
        //保存当前orderPO
        orderRepository.saveOrder(orderPO);
        //orderPO转化成orderDetailVO
        OrderDetailBO orderDetailBO = PojoConvert.convert(orderPO, OrderDetailBO.class);
        //将orderItemsVOS赋值到orderDetailVO中
        orderDetailBO.setOrderItemsVOS(orderItemsVOS);
        orderDetailBO.setOrderAmountTotal(total);
        return orderDetailBO;
    }

    @Override
    public int updateOrder(OrderDetailBO orderDetailBO) {
        OrderDetailBO existOrderDetailBO = orderRepository.getOrderByUserIdAndOrderNo(orderDetailBO.getTbUserId(), orderDetailBO.getOrderNo());
        if (existOrderDetailBO == null && existOrderDetailBO.getId() != null) {
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, ServiceConstant.ORDER_NOT_EXIST);
        }
        OrderPO orderPO = PojoConvert.convert(orderDetailBO, OrderPO.class);
        orderPO.setId(existOrderDetailBO.getId());
        updateStatusTime(existOrderDetailBO, orderPO);
        return orderRepository.saveOrder(orderPO);
    }

    private void updateStatusTime(OrderDetailBO existOrderDetailBO, OrderPO orderPO) {
        if(orderPO!=null&&orderPO.getStatus()!=null){
            if (orderPO.getStatus().equals(OrderConstants.PAID.getValue()) && !orderPO.getStatus().equals(existOrderDetailBO.getStatus())) {
                orderPO.setPayTime(LocalDateTime.now());
            } else if (orderPO.getStatus().equals(OrderConstants.DELIVERED.getValue()) && !orderPO.getStatus().equals(existOrderDetailBO.getStatus())) {
                orderPO.setDeliveryTime(LocalDateTime.now());
            } else if (orderPO.getStatus().equals(OrderConstants.TO_BE_RECEIVED.getValue()) && !orderPO.getStatus().equals(existOrderDetailBO.getStatus())) {
                orderPO.setReceiveDeliveryTime(LocalDateTime.now());
            } else if (orderPO.getStatus().equals(OrderConstants.USER_SHUTDOWN.getValue()) && !orderPO.getStatus().equals(existOrderDetailBO.getStatus())) {
                orderPO.setCancelTime(LocalDateTime.now());
            }
        }
    }

    @Override
    public List<OrderDetailBO> getOrderByUserId(Long userId) {
        List<OrderDetailBO> orderDetailBOS = orderRepository.getOrderByUserId(userId);
        for (OrderDetailBO orderDetailBO : orderDetailBOS) {
            setDetailForOrderDetailBO(orderDetailBO);
        }
        return orderDetailBOS;
    }

    @Override
    public OrderDetailBO getOrderByUserIdAndOrderNo(Long userId, String orderNo) {
        OrderDetailBO orderDetailBO = orderRepository.getOrderByUserIdAndOrderNo(userId, orderNo);
        setDetailForOrderDetailBO(orderDetailBO);
        return orderDetailBO;
    }

    @Override
    public List<OrderDetailBO> getOrdersByStatus(Long userId, Integer status) {
        List<OrderDetailBO> orderDetailBOS = orderRepository.getOrdersByStatus(userId, status);
        for (OrderDetailBO orderDetailBO : orderDetailBOS) {
            setDetailForOrderDetailBO(orderDetailBO);
        }
        return orderDetailBOS;
    }

    @Override
    public OrderDetailBO getUnpaidOrderByOrderNo(String orderNo) {
        OrderDetailBO orderDetailBO = orderRepository.getUnpaidOrderByOrderNo(orderNo);
        setDetailForOrderDetailBO(orderDetailBO);
        return orderDetailBO;
    }

    private void setDetailForOrderDetailBO(OrderDetailBO orderDetailBO) {
        if (orderDetailBO != null && orderDetailBO.getId() != null) {
            List<OrderItemsPO> orderItemsPOS = orderItemsRepository.getOrderItemsByOrderId(orderDetailBO.getId());
            List<OrderItemsVO> orderItemsVOS = PojoConvert.convertList(orderItemsPOS, OrderItemsVO.class);
            BigDecimal total = new BigDecimal(0);
            orderDetailBO.setOrderItemsVOS(orderItemsVOS);
            for (OrderItemsVO orderItemsVO : orderItemsVOS) {
                total = total.add(orderItemsVO.getTotalPrice());
            }
            orderDetailBO.setOrderAmountTotal(total);
            for (OrderConstants value : OrderConstants.values()) {
                if (value.getValue().equals(orderDetailBO.getStatus())) {
                    orderDetailBO.setOrderStatus(value.getDescription());
                }
            }
        }
    }

}
