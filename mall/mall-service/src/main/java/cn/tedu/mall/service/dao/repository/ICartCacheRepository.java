package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.po.CartPO;

import java.util.List;

public interface ICartCacheRepository {
    List<CartPO> listByUser(Long userId);
}
