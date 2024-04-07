package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.dto.CartUpdateDTO;
import cn.tedu.mall.service.pojo.po.CartPO;

import java.util.List;

public interface ICartRepository {
    CartPO selectCartById(Long id);

    int saveCart(CartPO cartPO);

    int deleteCartById(Long id);

    int updateCart(CartUpdateDTO cartUpdateDTO);

    List<CartPO> selectCartByUserId(Long userId);
}
