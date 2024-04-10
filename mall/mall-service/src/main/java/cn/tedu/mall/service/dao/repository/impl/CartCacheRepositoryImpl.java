package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.service.dao.repository.ICartCacheRepository;
import cn.tedu.mall.service.pojo.po.CartPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

import java.util.List;


@Slf4j
@Primary
@Repository
public class CartCacheRepositoryImpl implements ICartCacheRepository {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    private final String KEY_PREFIX_CART = "cart:";

    @Override
    public List<CartPO> listByUser(Long userId){
        log.debug("开始处理【根据用户查询购物车数据的列表】的缓存数据访问，用户ID：{}", userId);
        String key = KEY_PREFIX_CART + userId;
        //TODO 根据用户id查询购物车信息
        return null;
    }
}
