package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.vo.CartCacheVO;

import java.util.List;

public interface ICartCacheRepository {
    List<CartCacheVO> listByUser(Long userId);

    void addCart(Long userId, CartAddDTO cartAddDTO);

    void deleteCart(Long id, Long productSpecId);

    void modifyAmount(Long id, Long productSpecId, Integer productNum);

    void modifyChecked(Long userId, Long productSpecId, Integer productChecked);
}
