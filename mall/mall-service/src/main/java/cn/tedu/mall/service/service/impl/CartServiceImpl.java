package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.util.CalUtils;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.ICartCacheRepository;
import cn.tedu.mall.service.dao.repository.IProductSpecsRepository;
import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.po.CartCachePO;
import cn.tedu.mall.service.pojo.vo.CartCacheVO;
import cn.tedu.mall.service.pojo.vo.CartTotalVO;
import cn.tedu.mall.service.pojo.vo.ProductSpecsVO;
import cn.tedu.mall.service.service.ICartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Primary
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartCacheRepository cartCacheRepository;

    @Autowired
    private IProductSpecsRepository productSpecsRepository;

    @Override
    public List<CartCacheVO> getCartByUserId(Long userId) {
        return cartCacheRepository.listByUser(userId);
    }

    @Override
    public void addCart(Long userId, CartAddDTO cartAddDTO) {
        log.debug("购物车数据缓存入参:userId-{},购物车参数:{}", userId, cartAddDTO);

        ProductSpecsVO productSpecsVO = productSpecsRepository.getProductSpecsById(cartAddDTO.getTbProductSpecId());
        HashMap<String, String> fieldMap = new HashMap<>();
        fieldMap.put("ProductName", "tbProductName");
        fieldMap.put("currentPrice", "price");
        CartCachePO cartCachePO = PojoConvert.convert(productSpecsVO, CartCachePO.class, fieldMap);
        cartCachePO.setAmount(cartAddDTO.getAmount());
        cartCachePO.setTbProductSpecId(cartAddDTO.getTbProductSpecId());
        cartCachePO.setTbProductChecked(1);
        cartCachePO.setCreateTime(LocalDateTime.now());
        cartCachePO.setModifiedTime(LocalDateTime.now());
        cartCachePO.setProductAmountTotal(CalUtils.calTotal(cartCachePO.getPrice(), cartCachePO.getAmount()));

        cartCacheRepository.addCart(userId, cartCachePO);
    }

    @Override
    public void deleteCart(Long userId) {
        cartCacheRepository.deleteCart(userId);
    }

    @Override
    public void modifyAmount(Long userId, Long productSpecId, Integer productNum) {
        cartCacheRepository.modifyAmount(userId, productSpecId, productNum);
    }

    @Override
    public void modifyChecked(Long userId, Long productSpecId, Integer productChecked) {
        cartCacheRepository.modifyChecked(userId, productSpecId, productChecked);
    }

    @Override
    public CartTotalVO getTotal(Long userId) {
        return cartCacheRepository.getTotal(userId);
    }

    @Override
    public CartTotalVO getTotalByAllCheckedChanged(Long userId, boolean currentAllChecked) {
        return cartCacheRepository.getTotalByAllCheckedChanged(userId, currentAllChecked);
    }

    @Override
    public List<CartCacheVO> getCheckedCartByUserId(Long userId) {
        return cartCacheRepository.listCheckedByUserId(userId);
    }

}
