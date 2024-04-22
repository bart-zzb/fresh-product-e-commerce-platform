package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.constant.RedisConstants;
import cn.tedu.mall.common.util.CalUtils;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.ICartCacheRepository;
import cn.tedu.mall.service.pojo.po.CartCachePO;
import cn.tedu.mall.service.pojo.vo.CartCacheVO;
import cn.tedu.mall.service.pojo.vo.CartTotalVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.*;


@Slf4j
@Primary
@Repository
public class CartCacheRepositoryImpl implements ICartCacheRepository {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    private String getCartKey(Long userId) {
        return RedisConstants.KEY_PREFIX_CART + userId + RedisConstants.DATA;
    }

    private String getProductNumHashKey(Long productSpecsId) {
        return productSpecsId + RedisConstants.PRODUCT_AMOUNT;
    }

    private String getProductCheckedHashKey(Long productSpecsId) {
        return productSpecsId + RedisConstants.PRODUCT_CHECKED;
    }

    private String getProductInfoHashKey(Long productSpecsId) {
        return productSpecsId + RedisConstants.PRODUCT_INFO;
    }

    @Override
    public List<CartCacheVO> listByUser(Long userId) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        //大key e_mall_tb_shopping_cart_用户id_data
        String cartKey = getCartKey(userId);
        //1 一次 通过大key全部查询,用程序来过滤
        //所有商品信息
        List<CartCachePO> result = new ArrayList<>();
        //通过大key获取所有数据
        Map<String, Object> entries = hashOperations.entries(cartKey);
        //判断数据是否为空
        if (!CollectionUtils.isEmpty(entries)) {
            //遍历所有数据,过滤出商品信息
            //过滤条件是 hash_key 是否包含 "_product_info"
            entries.forEach((k, v) -> {
                if (k.contains(RedisConstants.PRODUCT_INFO)) {
                    //商品信息
                    CartCachePO cartCachePO = (CartCachePO) v;
                    //放到所有商品信息的list
                    result.add(cartCachePO);
                }
            });
        }
        log.debug("购物车数据转化为PO后的结果:{}", result);

        result.sort(new Comparator<CartCachePO>() {
            @Override
            public int compare(CartCachePO o1, CartCachePO o2) {
                return o1.getModifiedTime().compareTo(o2.getModifiedTime());
            }
        });

        return PojoConvert.convertList(result, CartCacheVO.class);
    }

    @Override
    public void addCart(Long userId, CartCachePO cartCachePO) {
        log.debug("购物车数据存入缓存:userId-{},购物车数据:{}", userId, cartCachePO);
        //将数据转换成CartCachePO
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();

        //大key
        String cartKey = getCartKey(userId);

        //三个小key
        Long productSpecId = cartCachePO.getTbProductSpecId();
        String productNumHashKey = getProductNumHashKey(productSpecId);
        String productCheckedHashKey = getProductCheckedHashKey(productSpecId);
        String productInfoHashKey = getProductInfoHashKey(productSpecId);

        Map<String, Object> smallMap = new HashMap<>();
        smallMap.put(productNumHashKey, cartCachePO.getAmount());
        smallMap.put(productCheckedHashKey, cartCachePO.getTbProductChecked());
        smallMap.put(productInfoHashKey, cartCachePO);
        hashOperations.putAll(cartKey, smallMap);
    }

    @Override
    public void deleteCart(Long userId, Long productSpecId) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        //获取大key
        String cartKey = getCartKey(userId);
        //获取三个小key
        String productNumHashKey = getProductNumHashKey(productSpecId);
        String productCheckedHashKey = getProductCheckedHashKey(productSpecId);
        String productInfoHashKey = getProductInfoHashKey(productSpecId);
        hashOperations.delete(cartKey, productNumHashKey, productCheckedHashKey, productInfoHashKey);
    }

    @Override
    public void modifyAmount(Long userId, Long productSpecId, Integer productNum) {
        updateKeyValue(userId, productSpecId, productNum, null);
    }

    @Override
    public void modifyChecked(Long userId, Long productSpecId, Integer productChecked) {
        updateKeyValue(userId, productSpecId, null, productChecked);
    }

    @Override
    public CartTotalVO getTotal(Long userId) {
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        //大key e_mall_tb_shopping_cart_用户id_data
        String cartKey = getCartKey(userId);
        //1 一次 通过大key全部查询,用程序来过滤
        //所有商品信息
        List<CartCachePO> result = new ArrayList<>();
        //通过大key获取所有数据
        Map<String, Object> entries = hashOperations.entries(cartKey);
        //判断数据是否为空
        BigDecimal totalPrice = new BigDecimal("0.00");
        Integer totalAmount = 0;
        boolean allChecked = true;

        if (!CollectionUtils.isEmpty(entries)) {
            //遍历所有数据,过滤出商品信息
            //过滤条件是 hash_key 是否包含 "_product_info"
            entries.forEach((k, v) -> {
                if (k.contains(RedisConstants.PRODUCT_INFO)) {
                    //商品信息
                    CartCachePO cartCachePO = (CartCachePO) v;
                    //放到所有商品信息的list
                    result.add(cartCachePO);
                }
            });

            for (CartCachePO cartCachePO : result) {
                if (cartCachePO.getTbProductChecked() == 1) {
                    totalPrice = totalPrice.add(cartCachePO.getProductAmountTotal());
                    totalAmount += cartCachePO.getAmount();
                }else {
                    allChecked = false;
                }
            }
        }

        CartTotalVO cartTotalVO = new CartTotalVO();
        cartTotalVO.setTotalPrice(totalPrice);
        cartTotalVO.setTotalAmount(totalAmount);
        cartTotalVO.setAllChecked(allChecked);
        return cartTotalVO;
    }

    @Override
    public CartTotalVO getTotalByAllCheckedChanged(Long userId, boolean currentAllChecked) {
        List<CartCacheVO> cartCacheVOS = listByUser(userId);
        Integer allChecked = 0;
        if (currentAllChecked){allChecked = 1;}
        log.debug("选中状态修改为" + allChecked);
        for (CartCacheVO cartCacheVO: cartCacheVOS) {
            updateKeyValue(userId, cartCacheVO.getTbProductSpecId(), null, allChecked);
        }
        return getTotal(userId);
    }

    private void updateKeyValue(Long userId, Long productSpecId, Integer productNum, Integer productChecked) {
        String cartKey = getCartKey(userId);
        HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
        String productNumHashKey = getProductNumHashKey(productSpecId);
        String productCheckedHashKey = getProductCheckedHashKey(productSpecId);
        //商品id_num
        if (productNum != null) {
            hashOperations.put(cartKey, productNumHashKey, productNum);
        }
        //商品checked
        if (productChecked != null) {
            hashOperations.put(cartKey, productCheckedHashKey, productChecked);
        }

        //通过大key获取所有数据
        Map<String, Object> entries = hashOperations.entries(cartKey);
        //判断数据是否为空
        if (!CollectionUtils.isEmpty(entries)) {
            String productInfoHashKey = getProductInfoHashKey(productSpecId);
            CartCachePO cartCachePO = (CartCachePO) entries.get(productInfoHashKey);

            if (cartCachePO != null) {
                if (productNum != null) {
                    //更新CachePO值
                    Object productAmount = entries.get(productNumHashKey);
                    //把商品数量转换为Integer并且设置到商品信息
                    cartCachePO.setAmount(Integer.valueOf(String.valueOf(productAmount)));
                    //更新总价
                    cartCachePO.setProductAmountTotal(CalUtils.calTotal(cartCachePO.getPrice(), cartCachePO.getAmount()));
                }

                if (productChecked != null) {
                    Object tbProductChecked = entries.get(productCheckedHashKey);
                    cartCachePO.setTbProductChecked((Integer) tbProductChecked);
                }
                hashOperations.put(cartKey, productInfoHashKey, cartCachePO);
            }
        }
    }
}

