package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.po.CartPO;

import java.util.List;

public interface ICartRepository {
    CartPO selectCartById(Long id);

    CartPO selectCartByInfo(Long userId, Long productId, Long productSpecId);

    int saveCart(CartPO cartPO);

    int deleteCartById(Long id);

    int updateCart(CartPO cartPO);

    List<CartPO> selectCartByUserId(Long userId);
}
