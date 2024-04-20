package cn.tedu.mall.front.controller;


import cn.tedu.mall.common.annotation.CurrentUser;
import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.dto.CartUpdateDTO;
import cn.tedu.mall.service.pojo.vo.CartCacheVO;
import cn.tedu.mall.service.pojo.vo.CartVO;
import cn.tedu.mall.service.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
     * @param currentPrincipal 用户id
     * @return JsonResult(List<CartVO>)
     */
    @ApiOperation("根据当前用户查询购物车")
    @GetMapping("/get")
    public JsonResult getCartByCurrentPrincipal(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal){
        List<CartCacheVO> list =  cartService.getCartByUserId(currentPrincipal.getId());
        return JsonResult.ok(list);
    }

    /**
     * 增加商品到购物车
     * @param currentPrincipal 当前用户, cartAddDTO
     * @return JsonResult(List<CartVO>)
     */
    @ApiOperation("添加商品到当前用户购物车")
    @PostMapping("/add")
    public JsonResult addCart(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, @Validated CartAddDTO cartAddDTO){
        cartService.addCart(currentPrincipal.getId(), cartAddDTO);
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


