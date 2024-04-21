package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.dto.CartUpdateDTO;
import cn.tedu.mall.service.pojo.vo.CartCacheVO;
import cn.tedu.mall.service.pojo.vo.CartTotalVO;
import cn.tedu.mall.service.pojo.vo.CartVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ICartService {
    List<CartCacheVO> getCartByUserId(Long userId);

    void addCart(Long userId, CartAddDTO cartAddDTO);

    void deleteCart(Long userId, Long productSpecId);

    void modifyAmount(Long userId, Long productSpecId, Integer productNum);

    void modifyChecked(Long userId, Long productSpecId, Integer productChecked);

    CartTotalVO getTotal(Long userId);

//使用数据库存储方案
//    void addCart(CartAddDTO cartAddDTO);
//
//    void deleteCartById(Long id);
//
//    void updateCartByCartUpdateDTO(CartUpdateDTO cartUpdateDTO);
//
//    List<CartVO> getCartByUserId(Long userId);
}
