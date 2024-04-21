package cn.tedu.mall.front.controller;


import cn.tedu.mall.common.annotation.CurrentUser;
import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.vo.CartCacheVO;
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

import javax.validation.constraints.NotNull;
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
        List<CartCacheVO> list = cartService.getCartByUserId(currentPrincipal.getId());
        return JsonResult.ok(list);
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
     * @param productSpecId    SKU id
     * @return JsonResult.ok()
     */
    @ApiOperation("删除当前用户的购物车商品")
    @PostMapping("/delete/{productSpecId}")
    public JsonResult deleteCart(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, @PathVariable @NotNull @Range(min = 1) Long productSpecId) {
        cartService.deleteCart(currentPrincipal.getId(), productSpecId);
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
    @ApiOperation("修改商品数量-替换")
    @PostMapping("/modify_amount")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productSpecId", value = "商品SKUid", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "productNum", value = "商品SKU数量", required = true, dataType = "Integer"),
    })
    public JsonResult updateCartAmount(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal,
                                 @Range(min = 1, message = "请输入合法的商品id") Long productSpecId,
                                 @Range(min = 1, max = 100, message = "商品数量必须是1~100之间!") Integer productNum) {
        log.debug("增加商品-入参 用户id:{},商品id:{},商品数量:{}", currentPrincipal.getId(), productSpecId, productNum);
        cartService.modifyAmount(currentPrincipal.getId(), productSpecId, productNum);
        return JsonResult.ok();
    }

    /**
     * 修改选中状态
     * @param currentPrincipal 当前用户
     * @param productSpecId    SKU id
     * @param productChecked   SKU 选中状态
     * @return JsonResult.ok()
     */
    @ApiOperation("修改商品状态-替换")
    @PostMapping("/modify_checked")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productSpecId", value = "商品SKUid", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "productChecked", value = "商品SKU选中状态", required = true, dataType = "Integer"),
    })
    public JsonResult updateCartChecked(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal,
                                 @Range(min = 1, message = "请输入合法的商品id") Long productSpecId,
                                 @Range(min = 0, max = 1, message = "商品选中状态必须是0~1之间!") Integer productChecked) {
        log.debug("增加商品-入参 用户id:{},商品id:{},商品数量:{}", currentPrincipal.getId(), productSpecId, productChecked);
        cartService.modifyChecked(currentPrincipal.getId(), productSpecId, productChecked);
        return JsonResult.ok();
    }
//使用数据库存储方案
//    /**
//     * 增加购物车
//     * @param cartAddDTO 商品
//     * @return JsonResult
//     */
//    @ApiOperation("增加购物车")
//    @PostMapping("/add")
//    public JsonResult addCart(@Validated CartAddDTO cartAddDTO){
//        cartService.addCart(cartAddDTO);
//        return JsonResult.ok();
//    }
//
//    /**
//     * 删除购物车
//     * @param id 购物车id
//     * @return JsonResult
//     */
//    @ApiOperation("删除购物车")
//    @PostMapping("/delete/{id}")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "购物车id",required = true,dataType = "Long"),
//    })
//    public JsonResult deleteCartById(@PathVariable @NotNull(message = "购物车id不能为空") Long id){
//        cartService.deleteCartById(id);
//        return JsonResult.ok();
//    }
//
//    /**
//     * 更新购物车
//     * @param cartUpdateDTO 购物车更新DTO
//     * @return JsonResult
//     */
//    @ApiOperation("更新购物车")
//    @PostMapping("/update")
//    public JsonResult updateCartByCartUpdateDTO(@Validated CartUpdateDTO cartUpdateDTO){
//        cartService.updateCartByCartUpdateDTO(cartUpdateDTO);
//        return JsonResult.ok();
//    }
//
//    /**
//     * 查询购物车
//     * @param currentPrincipal 当前用户, userId 用户id
//     * @return JsonResult(List<CartVO>)
//     */
//    @ApiOperation("查询购物车")
//    @GetMapping("/{userId}")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "用户id",required = true,dataType = "Long"),
//    })
//    public JsonResult getCartByUserId(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, @PathVariable @NotNull(message = "用户id不能为空") Long userId){
//        List<CartVO> list =  cartService.getCartByUserId(userId);
//        return JsonResult.ok(list);
//    }
//
//    /**
//     * 查询购物车
//     * @param currentPrincipal 用户id
//     * @return JsonResult(List<CartVO>)
//     */
//    @ApiOperation("根据当前用户查询购物车")
//    @GetMapping("/get")
//    public JsonResult getCartByCurrentPrincipal(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal){
//        List<CartVO> list =  cartService.getCartByUserId(currentPrincipal.getId());
//        return JsonResult.ok(list);
//    }
}


