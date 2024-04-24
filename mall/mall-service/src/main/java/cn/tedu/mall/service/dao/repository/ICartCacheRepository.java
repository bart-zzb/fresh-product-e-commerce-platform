package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.po.CartCachePO;
import cn.tedu.mall.service.pojo.vo.CartCacheVO;
import cn.tedu.mall.service.pojo.vo.CartTotalVO;

import java.util.List;

public interface ICartCacheRepository {
    List<CartCacheVO> listByUser(Long userId);

    void addCart(Long userId, CartCachePO cartCachePO);

    void deleteCart(Long id);

    void modifyAmount(Long id, Long productSpecId, Integer productNum);

    void modifyChecked(Long userId, Long productSpecId, Integer productChecked);

    CartTotalVO getTotal(Long userId);

    CartTotalVO getTotalByAllCheckedChanged(Long userId, boolean currentAllChecked);

    List<CartCacheVO> listCheckedByUserId(Long userId);
}
