package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.constant.OrderConstants;
import cn.tedu.mall.common.constant.RedisConstants;
import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.IOrderItemsRepository;
import cn.tedu.mall.service.dao.repository.IOrderRepository;
import cn.tedu.mall.service.dao.repository.IProductSpecsRepository;
import cn.tedu.mall.service.pojo.bo.OrderDetailBO;
import cn.tedu.mall.service.pojo.bo.ProductSpecsBO;
import cn.tedu.mall.service.pojo.dto.OrderItemsAddDTO;
import cn.tedu.mall.service.pojo.dto.OrderUpdateConsigneeInfoDTO;
import cn.tedu.mall.service.pojo.dto.OrderUpdatePaidDTO;
import cn.tedu.mall.service.pojo.dto.ProductSpecDeleteDTO;
import cn.tedu.mall.service.pojo.po.OrderItemsPO;
import cn.tedu.mall.service.pojo.po.OrderPO;
import cn.tedu.mall.service.pojo.vo.OrderItemsVO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import cn.tedu.mall.service.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Primary
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderItemsRepository orderItemsRepository;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private IProductSpecsRepository productSpecsRepository;

    @Override
    public OrderDetailBO addOrder(Long userId, List<OrderItemsAddDTO> orderItemsAddDTOS) throws InterruptedException {
        log.debug("商品详情入参{}", orderItemsAddDTOS);
        //用单体项目使用悲观锁/乐观锁, 分布式使用Redisson抢锁,先从数据库查询库存,预减库存,锁释放,超时未支付,库存回退

        //如果传入的商品列表为空, 直接返回
        if (orderItemsAddDTOS.isEmpty()) {
            throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST, ServiceConstant.ORDER_ITEMS_NOT_EXIST);
        }
        //配置userId锁,不同的userId设置不同的userId锁
        String userKey = RedisConstants.KEY_LOCK_USER_PREFIX + userId;
        //抢锁
        RLock userLock = redissonClient.getLock(userKey);
        //设置waitTime为-1,抢不到直接放弃
        boolean tryUserLock = userLock.tryLock(-1, TimeUnit.SECONDS);
        //抢锁成功
        if (tryUserLock) {
            //对List<OrderItemsAddDTO>内各自的商品id抢锁,避免高并发下,多人对同一个商品下单抢购
            try {
                for (OrderItemsAddDTO orderItemsAddDTO : orderItemsAddDTOS) {
                    //获取商品id
                    Long tbProductSpecId = orderItemsAddDTO.getTbProductSpecId();
                    //配置商品SKU id锁, 不同的SKU id, 锁不一样
                    RLock productSpecsLock = redissonClient.getLock(RedisConstants.KEY_LOCK_PRODUCT_SPECS_PREFIX + tbProductSpecId);
                    //抢商品SKU id锁
                    boolean tryProductSpecsLock = productSpecsLock.tryLock(10, TimeUnit.SECONDS);
                    try {
                        if (tryProductSpecsLock) {
                            //TODO 判断productSpecsVO是否存在, productSpecsRepository.getProductSpecsById底层也实现抢锁,加载数据到Redis中,没有则生成空对象存储到Redis中
                            ProductSpecsVO productSpecsVO = productSpecsRepository.getProductSpecsById(tbProductSpecId);
                            if (productSpecsVO == null) {
                                throw new ServiceException(ServiceCode.ERROR_PRODUCT_SPECS_NOT_EXIST, ServiceConstant.PRODUCT_SPECS_NOT_EXIST);
                            }
                            productSpecsRepository.deleteProductSpecsAmountByIdAndAmount(orderItemsAddDTO.getTbProductSpecId(), orderItemsAddDTO.getAmount());
                        }
                    } finally {
                        //释放productSpecs锁
                        productSpecsLock.unlock();
                    }
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
            } finally {
                //释放userId锁
                userLock.unlock();
            }
        }
        throw new ServiceException(ServiceCode.ERROR_ORDER_CREATION_FAILED, ServiceConstant.ORDER_CREATION_FAILED);
    }


    @Override
    public int updateOrder2Paid(OrderUpdatePaidDTO orderUpdatePaidDTO) {
        OrderDetailBO existOrderDetailBO = orderRepository.getOrderByUserIdAndOrderNo(orderUpdatePaidDTO.getTbUserId(), orderUpdatePaidDTO.getOrderNo());
        if (existOrderDetailBO == null && existOrderDetailBO.getId() != null) {
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, ServiceConstant.ORDER_NOT_EXIST);
        }
        OrderPO orderPO = PojoConvert.convert(orderUpdatePaidDTO, OrderPO.class);
        orderPO.setId(existOrderDetailBO.getId());
        orderPO.setStatus(OrderConstants.PAID.getValue());
        updateStatusTime(existOrderDetailBO, orderPO);
        return orderRepository.saveOrder(orderPO);
    }

    @Override
    public int updateOrder(OrderUpdateConsigneeInfoDTO orderUpdateConsigneeInfoDTO) {
        OrderDetailBO existOrderDetailBO = orderRepository.getOrderByUserIdAndOrderNo(orderUpdateConsigneeInfoDTO.getTbUserId(), orderUpdateConsigneeInfoDTO.getOrderNo());
        if (existOrderDetailBO == null && existOrderDetailBO.getId() != null) {
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, ServiceConstant.ORDER_NOT_EXIST);
        }
        OrderPO orderPO = PojoConvert.convert(orderUpdateConsigneeInfoDTO, OrderPO.class);
        orderPO.setId(existOrderDetailBO.getId());
        updateStatusTime(existOrderDetailBO, orderPO);
        return orderRepository.saveOrder(orderPO);
    }

    private void updateStatusTime(OrderDetailBO existOrderDetailBO, OrderPO orderPO) {
        if (orderPO != null && orderPO.getStatus() != null) {
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
