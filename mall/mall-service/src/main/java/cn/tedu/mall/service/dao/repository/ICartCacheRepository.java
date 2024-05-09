package cn.tedu.mall.service.dao.repository;

import cn.tedu.mall.service.pojo.bo.CartCacheBO;
import cn.tedu.mall.service.pojo.bo.CartTotalBO;
import cn.tedu.mall.service.pojo.po.CartCachePO;
import cn.tedu.mall.service.pojo.vo.CartCacheVO;
import cn.tedu.mall.service.pojo.vo.CartTotalVO;

import java.util.List;

public interface ICartCacheRepository {
    List<CartCacheBO> listByUser(Long userId);

    void addCart(Long userId, CartCachePO cartCachePO);

    void deleteCart(Long id);

    void modifyAmount(Long id, Long productSpecId, Integer productNum);

    void modifyChecked(Long userId, Long productSpecId, Integer productChecked);

    CartTotalBO getTotal(Long userId);

    CartTotalBO getTotalByAllCheckedChanged(Long userId, boolean currentAllChecked);

    List<CartCacheBO> listCheckedByUserId(Long userId);
}
