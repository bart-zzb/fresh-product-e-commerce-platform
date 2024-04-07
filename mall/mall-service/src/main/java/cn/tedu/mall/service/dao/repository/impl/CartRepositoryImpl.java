package cn.tedu.mall.service.dao.repository.impl;

import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.mapper.CartMapper;
import cn.tedu.mall.service.dao.repository.ICartRepository;
import cn.tedu.mall.service.pojo.dto.CartUpdateDTO;
import cn.tedu.mall.service.pojo.po.CartPO;
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
    public CartPO selectCartById(Long id){
        return cartMapper.selectById(id);
    }

    @Override
    public int saveCart(CartPO cartPO) {
        return cartMapper.insert(cartPO);
    }

    @Override
    public int deleteCartById(Long id){
        return cartMapper.deleteById(id);
    }

    @Override
    public int updateCart(CartUpdateDTO cartUpdateDTO){
        CartPO origCartPO = cartMapper.selectById(cartUpdateDTO);
        if (origCartPO == null){
            throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST,"购物车类别不存在");
        }
        CartPO cartPO = PojoConvert.convert(cartUpdateDTO, CartPO.class);
        return cartMapper.updateById(cartPO);
    }

    @Override
    public List<CartPO> selectCartByUserId(Long userId) {
        return cartMapper.selectCartByUserId(userId);
    }
}
