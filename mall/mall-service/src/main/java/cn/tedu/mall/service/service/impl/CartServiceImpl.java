package cn.tedu.mall.service.service.impl;

import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.ex.ServiceException;
import cn.tedu.mall.common.util.CalUtils;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.service.dao.repository.ICartCacheRepository;
import cn.tedu.mall.service.dao.repository.ICartRepository;
import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.dto.CartUpdateDTO;
import cn.tedu.mall.service.pojo.po.CartPO;
import cn.tedu.mall.service.pojo.vo.CartCacheVO;
import cn.tedu.mall.service.pojo.vo.CartVO;
import cn.tedu.mall.service.service.ICartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Primary
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private ICartCacheRepository cartCacheRepository;

    @Override
    public List<CartCacheVO> getCartByUserId(Long id) {
        return cartCacheRepository.listByUser(id);
    }

    @Override
    public void addCart(Long id, CartAddDTO cartAddDTO) {
        cartCacheRepository.addCart(id, cartAddDTO);
    }

//使用数据库存储方案
//    @Autowired
//    private ICartRepository cartRepository;
//
//    @Override
//    public void addCart(CartAddDTO cartAddDTO) {
//        CartPO cartPO = cartRepository.selectCartByInfo(cartAddDTO.getTbUserId(), cartAddDTO.getTbProductId(), cartAddDTO.getTbProductSpecId());
//        if(cartPO!=null){
//            BeanUtils.copyProperties(cartAddDTO, cartPO);
//            BigDecimal calTotal = CalUtils.calTotal(cartPO.getPrice(), cartPO.getAmount());
//            cartPO.setProductAmountTotal(calTotal);
//            cartRepository.saveCart(cartPO);
//        }else{
//            //TODO 根据商品id查询相关信息增加到购物车当中
//        }
//    }
//
//    @Override
//    public void deleteCartById(Long id) {
//        cartRepository.deleteCartById(id);
//    }
//
//    @Override
//    public void updateCartByCartUpdateDTO(CartUpdateDTO cartUpdateDTO) {
//        CartPO origCartPO = cartRepository.selectCartById(cartUpdateDTO.getId());
//        if (origCartPO == null){
//            throw new ServiceException(ServiceCode.ERROR_BAD_REQUEST, ServiceConstant.CART_NOT_EXIST);
//        }
//        CartPO cartPO = PojoConvert.convert(cartUpdateDTO, CartPO.class);
//        BigDecimal calTotal = CalUtils.calTotal(origCartPO.getPrice(), cartUpdateDTO.getAmount());
//        cartPO.setProductAmountTotal(calTotal);
//        cartRepository.updateCart(cartPO);
//    }
//
//    @Override
//    public List<CartVO> getCartByUserId(Long userId) {
//        return cartRepository.selectCartByUserId(userId);
//    }
}
