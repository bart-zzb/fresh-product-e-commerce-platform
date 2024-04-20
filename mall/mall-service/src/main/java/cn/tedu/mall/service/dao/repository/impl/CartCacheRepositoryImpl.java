package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.constant.RedisConstants;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.ICartCacheRepository;
import cn.tedu.mall.service.dao.repository.IProductSpecsRepository;
import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.po.CartCachePO;
import cn.tedu.mall.service.pojo.vo.CartCacheVO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.*;


@Slf4j
@Primary
@Repository
public class CartCacheRepositoryImpl implements ICartCacheRepository {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    private IProductSpecsRepository productSpecsRepository;

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
//            //过滤商品信息
//            result.forEach(po -> {
//                //取出商品id
//                Long productId = po.getTbProductId();
//                //拼接商品数量的hash_key 商品id_num
//                String productNumHashKey = getProductNumHashKey(productId);
//                //从大key对应的所有数据里获取商品数量
//                Object productNum = entries.get(productNumHashKey);
//                //把商品数量转换为Integer并且设置到商品信息
//                po.setAmount(Integer.valueOf(String.valueOf(productNum)));
//                //拼接商品选择状态的hash_key 商品id_checked
//                String productCheckedHashKey = getProductCheckedHashKey(productId);
//                //从大key对应的所有数据里获取商品选中状态
//                Object productChecked = entries.get(productCheckedHashKey);
//                //把商品是否选中转换为Integer并且设置到商品信息
//                po.setTbProductChecked(Integer.valueOf(String.valueOf(productChecked)));
//            });
        }
        log.debug("一次查询所有购物车数据,用户id:{},购物车数据:{}", userId, entries);
        //2 多次 先把hashkey 查出来,然后通过hashkey 查具体的值
        log.debug("购物车数据转化为PO后的结果:{}", result);

        Collections.sort(result, new Comparator<CartCachePO>() {
            @Override
            public int compare(CartCachePO o1, CartCachePO o2) {
                return o1.getModifiedTime().compareTo(o2.getModifiedTime());
            }
        });

        List<CartCacheVO> cartCacheVOS = PojoConvert.convertList(result, CartCacheVO.class);
        return cartCacheVOS;
    }

    @Override
    public void addCart(Long userId, CartAddDTO cartAddDTO) {
        log.debug("购物车数据缓存入参:userId-{},购物车参数:{}",userId,cartAddDTO);

        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        ProductSpecsVO productSpecsVO = productSpecsRepository.getProductSpecsById(cartAddDTO.getTbProductSpecId());
        HashMap<String, String> fieldMap = new HashMap<>();
        fieldMap.put("ProductName","tbProductName");
        fieldMap.put("currentPrice","price");
        CartCachePO cartCachePO = PojoConvert.convert(productSpecsVO, CartCachePO.class, fieldMap);
        cartCachePO.setAmount(cartAddDTO.getAmount());
        cartCachePO.setTbProductSpecId(cartAddDTO.getTbProductSpecId());
        cartCachePO.setTbProductChecked(1);
        cartCachePO.setCreateTime(LocalDateTime.now());
        cartCachePO.setModifiedTime(LocalDateTime.now());

        log.debug("购物车数据存入缓存:userId-{},购物车数据:{}",userId,cartCachePO);

        String cartKey = getCartKey(userId);
        Long productId = cartCachePO.getTbProductSpecId();

        String productNumHashKey = getProductNumHashKey(productId);
        String productCheckedHashKey = getProductCheckedHashKey(productId);
        String productInfoHashKey = getProductInfoHashKey(productId);

        Map<String,Object> smallMap = new HashMap<>();
        smallMap.put(productNumHashKey,cartCachePO.getAmount());
        smallMap.put(productCheckedHashKey,cartCachePO.getTbProductChecked());
        smallMap.put(productInfoHashKey,cartCachePO);
        hashOperations.putAll(cartKey,smallMap);
    }
}

