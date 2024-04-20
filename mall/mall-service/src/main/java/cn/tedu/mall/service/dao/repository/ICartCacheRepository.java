package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.vo.CartCacheVO;

import java.util.List;

public interface ICartCacheRepository {
    List<CartCacheVO> listByUser(Long userId);

    void addCart(Long userId, CartAddDTO cartAddDTO);
}
