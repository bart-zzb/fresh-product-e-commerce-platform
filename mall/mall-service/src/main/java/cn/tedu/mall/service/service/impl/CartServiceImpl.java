package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.ICartRepository;
import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.dto.CartUpdateDTO;
import cn.tedu.mall.service.pojo.po.CartPO;
import cn.tedu.mall.service.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public void addCart(CartAddDTO cartAddDTO) {
        CartPO cartPO = PojoConvert.convert(cartAddDTO, CartPO.class);
        cartRepository.saveCart(cartPO);
    }

    @Override
    public void deleteCartById(Long id) {
        cartRepository.deleteCartById(id);
    }

    @Override
    public void updateCartByCartUpdateDTO(CartUpdateDTO cartUpdateDTO) {
        cartRepository.updateCart(cartUpdateDTO);
    }

    @Override
    public List<CartPO> getCartByUserId(Long userId) {
        return cartRepository.selectCartByUserId(userId);
    }
}
