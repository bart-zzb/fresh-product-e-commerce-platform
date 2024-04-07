package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.dto.CartUpdateDTO;
import cn.tedu.mall.service.pojo.po.CartPO;

import java.util.List;

public interface ICartService {
    void addCart(CartAddDTO cartAddDTO);

    void deleteCartById(Long id);

    void updateCartByCartUpdateDTO(CartUpdateDTO cartUpdateDTO);

    List<CartPO> getCartByUserId(Long userId);
}
