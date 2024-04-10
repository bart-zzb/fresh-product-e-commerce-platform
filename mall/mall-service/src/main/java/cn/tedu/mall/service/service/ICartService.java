package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.dto.CartUpdateDTO;
import cn.tedu.mall.service.pojo.vo.CartVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ICartService {
    void addCart(CartAddDTO cartAddDTO);

    void deleteCartById(Long id);

    void updateCartByCartUpdateDTO(CartUpdateDTO cartUpdateDTO);

    List<CartVO> getCartByUserId(Long userId);
}
