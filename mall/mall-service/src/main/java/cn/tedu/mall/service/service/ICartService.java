package cn.tedu.mall.service.service;

import cn.tedu.mall.service.pojo.bo.CartCacheBO;
import cn.tedu.mall.service.pojo.bo.CartTotalBO;
import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface ICartService {
    List<CartCacheBO> getCartByUserId(Long userId);

    void addCart(Long userId, CartAddDTO cartAddDTO);

    void deleteCart(Long userId);

    void modifyAmount(Long userId, Long productSpecId, Integer productNum);

    void modifyChecked(Long userId, Long productSpecId, Integer productChecked);

    CartTotalBO getTotal(Long userId);

    CartTotalBO getTotalByAllCheckedChanged(Long userId, boolean currentAllChecked);

    List<CartCacheBO> getCheckedCartByUserId(Long userid);
}
