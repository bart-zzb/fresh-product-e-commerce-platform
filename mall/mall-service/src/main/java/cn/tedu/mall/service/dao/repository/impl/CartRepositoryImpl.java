package cn.tedu.mall.service.dao.repository.impl;


import cn.tedu.mall.service.dao.mapper.CartMapper;
import cn.tedu.mall.service.dao.repository.ICartRepository;
import cn.tedu.mall.service.pojo.po.CartPO;
import cn.tedu.mall.service.pojo.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class CartRepositoryImpl implements ICartRepository {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public CartPO selectCartById(Long id) {
        return cartMapper.selectById(id);
    }

    @Override
    public CartPO selectCartByInfo(Long userId, Long productId, Long productSpecId) {
        return cartMapper.selectCartByInfo(userId, productId, productSpecId);
    }

    @Override
    public int saveCart(CartPO cartPO) {
        return cartMapper.insert(cartPO);
    }

    @Override
    public int deleteCartById(Long id) {
        return cartMapper.deleteById(id);
    }

    @Override
    public int updateCart(CartPO cartPO) {
        return cartMapper.updateById(cartPO);
    }

    @Override
    public List<CartVO> selectCartByUserId(Long userId) {
        return cartMapper.selectCartByUserId(userId);
    }
}
