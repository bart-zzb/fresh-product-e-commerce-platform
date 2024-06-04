package cn.tedu.mall.front.controller;


import cn.tedu.mall.common.annotation.CurrentUser;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
import cn.tedu.mall.service.pojo.bo.CartCacheBO;
import cn.tedu.mall.service.pojo.bo.CartTotalBO;
import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.vo.CartCacheVO;
import cn.tedu.mall.service.pojo.vo.CartTotalVO;
import cn.tedu.mall.service.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "购物车模块")
@Slf4j
@Validated
@RestController
@RequestMapping("mall/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    /**
     * 查询购物车
     *
     * @param currentPrincipal 用户id
     * @return JsonResult(List < CartVO >)
     */
    @ApiOperation("根据当前用户查询购物车")
    @GetMapping("/get")
    public JsonResult getCartByCurrentPrincipal(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal) {
        List<CartCacheBO> cartCacheBOS = cartService.getCartByUserId(currentPrincipal.getId());
        List<CartCacheVO> cartCacheVOS = PojoConvert.convertList(cartCacheBOS, CartCacheVO.class);
        return JsonResult.ok(cartCacheVOS);
    }

    /**
     * 增加商品到购物车
     *
     * @param currentPrincipal 当前用户,
     * @param cartAddDTO       增加SKU 商品DTO
     * @return JsonResult.ok()
     */
    @ApiOperation("添加商品到当前用户购物车")
    @PostMapping("/add")
    public JsonResult addCart(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, @Validated CartAddDTO cartAddDTO) {
        cartService.addCart(currentPrincipal.getId(), cartAddDTO);
        return JsonResult.ok();
    }

    /**
     * 根据productSpecId删除当前用户的购物车
     *
     * @param currentPrincipal 当前用户
     * @return JsonResult.ok()
     */
    @ApiOperation("删除当前用户的购物车选中的商品")
    @PostMapping("/delete")
    public JsonResult deleteCart(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal) {
        cartService.deleteCart(currentPrincipal.getId());
        return JsonResult.ok();
    }

    /**
     * 修改商品数量
     * 修改购物车里面的已经有的商品的数量 直接修改
     *
     * @param currentPrincipal 当前用户
     * @param productSpecId    SKU id
     * @param productNum       SKU 数量
     * @return JsonResult.ok()
     */
    @ApiOperation("修改商品数量")
    @PostMapping("/modify_amount/{productSpecId}/{productNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productSpecId", value = "商品SKUid", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "productNum", value = "商品SKU数量", required = true, dataType = "Integer"),
    })
    public JsonResult updateCartAmount(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal,
                                       @PathVariable @Range(min = 1, message = "请输入合法的商品id") Long productSpecId,
                                       @PathVariable @Range(min = 1, max = 100, message = "商品数量必须是1~100之间!") Integer productNum) {
        log.debug("修改商品数量-入参 用户id:{},商品SKUid:{},商品数量:{}", currentPrincipal.getId(), productSpecId, productNum);
        cartService.modifyAmount(currentPrincipal.getId(), productSpecId, productNum);
        return JsonResult.ok();
    }

    /**
     * 修改选中状态
     *
     * @param currentPrincipal 当前用户
     * @param productSpecId    SKU id
     * @param productChecked   SKU 选中状态
     * @return JsonResult.ok()
     */
    @ApiOperation("修改商品状态")
    @PostMapping("/modify_checked/{productSpecId}/{productChecked}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productSpecId", value = "商品SKUid", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "productChecked", value = "商品SKU选中状态", required = true, dataType = "Integer"),
    })
    public JsonResult updateCartChecked(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal,
                                        @PathVariable @Range(min = 1, message = "请输入合法的商品id") Long productSpecId,
                                        @PathVariable @Range(min = 0, max = 1, message = "商品选中状态必须是0~1之间!") Integer productChecked) {
        log.debug("修改商品状态-入参 用户id:{},商品SKUid:{},商品选中状态:{}", currentPrincipal.getId(), productSpecId, productChecked);
        cartService.modifyChecked(currentPrincipal.getId(), productSpecId, productChecked);
        return JsonResult.ok();
    }

    /**
     * 查询当前用户购物车价格总和以及选中商品总数量
     *
     * @param currentPrincipal 当前用户
     * @return JsonResult.ok(CartTotalVO)
     */
    @ApiOperation("查询当前用户购物车价格总和")
    @GetMapping("/total")
    public JsonResult updateCartChecked(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal) {
        log.debug("查询当前用户购物车价格总和-入参 用户id:{}", currentPrincipal.getId());
        CartTotalBO cartTotalBO = cartService.getTotal(currentPrincipal.getId());
        CartTotalVO cartTotalVO = PojoConvert.convert(cartTotalBO, CartTotalVO.class);
        return JsonResult.ok(cartTotalVO);
    }

    /**
     * 修改当前用户购物车所有商品的选中状态
     *
     * @param currentPrincipal  当前用户
     * @param currentAllChecked 当前选中状态
     * @return JsonResult.ok()
     */
    @ApiOperation("修改当前用户购物车所有商品的选中状态")
    @GetMapping("/allChecked/{currentAllChecked}")
    public JsonResult getCartAllChecked(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, @PathVariable boolean currentAllChecked) {
        log.debug("修改当前用户购物车所有商品的选中状态-入参 用户id:{} 当前选中状态{}", currentPrincipal.getId(), currentAllChecked);
        CartTotalBO cartTotalBO = cartService.getTotalByAllCheckedChanged(currentPrincipal.getId(), currentAllChecked);
        CartTotalVO cartTotalVO = PojoConvert.convert(cartTotalBO, CartTotalVO.class);
        return JsonResult.ok(cartTotalVO);
    }

    /**
     * 根据当前用户查询当前购物车已选中的所有商品
     *
     * @param currentPrincipal 用户id
     * @return JsonResult(List < CartVO >)
     */
    @ApiOperation("根据当前用户查询当前购物车已选中的所有商品")
    @GetMapping("/get/allChecked")
    public JsonResult getCheckedCartByCurrentPrincipal(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal) {
        List<CartCacheBO> cartCacheBOS = cartService.getCheckedCartByUserId(currentPrincipal.getId());
        List<CartCacheVO> cartCacheVOS = PojoConvert.convertList(cartCacheBOS, CartCacheVO.class);
        return JsonResult.ok(cartCacheVOS);
    }
}


