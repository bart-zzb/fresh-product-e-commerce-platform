package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.util.CalUtils;
import cn.tedu.mall.service.dao.mapper.*;
import cn.tedu.mall.service.dao.repository.IOrderItemsRepository;
import cn.tedu.mall.service.pojo.po.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Primary
@Repository
public class OrderItemsRepositoryImpl implements IOrderItemsRepository {
    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private ProductSpecsMapper productSpecsMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public OrderItemsPO addOrderItemsByProductSpecIdAndAmount(Long tbProductSpecId, Long orderId, Integer amount) {
        ProductSpecsPO productSpecsPO = productSpecsMapper.selectById(tbProductSpecId);
        ProductPO productPO = productMapper.selectById(productSpecsPO.getTbProductId());
        BrandPO brandPO = brandMapper.selectById(productPO.getTbBrandId());
        CategoryPO categoryPO = categoryMapper.selectById(productPO.getTbCategoryId());
        OrderItemsPO orderItemsPO = new OrderItemsPO();
        orderItemsPO.setTbOrderId(orderId);
        orderItemsPO.setTbProductId(productSpecsPO.getTbProductId());
        orderItemsPO.setTbProductName(productPO.getProductName());
        orderItemsPO.setTbProductSpecId(tbProductSpecId);
        orderItemsPO.setSpecName(productSpecsPO.getSpecsName());
        orderItemsPO.setImgUrl(productSpecsPO.getImgUrl());
        orderItemsPO.setPrice(productSpecsPO.getCurrentPrice());
        orderItemsPO.setAmount(amount);
        orderItemsPO.setTbCategoryId(productPO.getTbCategoryId());
        orderItemsPO.setTbCategoryName(categoryPO.getCategoryName());
        orderItemsPO.setTbBrandId(productPO.getTbBrandId());
        orderItemsPO.setTbBrandName(brandPO.getName());
        BigDecimal totalPrice = CalUtils.calTotal(productSpecsPO.getCurrentPrice(), amount);
        orderItemsPO.setIftIntegration(totalPrice.intValue());
        orderItemsPO.setTotalPrice(totalPrice);

        orderItemsMapper.insert(orderItemsPO);
        return orderItemsPO;
    }

    @Override
    public List<OrderItemsPO> getOrderItemsByOrderId(Long orderId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tb_order_id", orderId);
        return orderItemsMapper.selectList(queryWrapper);
    }
}
