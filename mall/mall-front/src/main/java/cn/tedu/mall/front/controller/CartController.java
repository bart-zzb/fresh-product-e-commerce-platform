package cn.tedu.mall.front.controller;


import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.dto.CartAddDTO;
import cn.tedu.mall.service.pojo.dto.CartUpdateDTO;
import cn.tedu.mall.service.pojo.po.CartPO;
import cn.tedu.mall.service.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "购物车类别")
@Slf4j
@Validated
@RestController
@RequestMapping("mall/Cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    /**
     * 增加购物车
     * @param cartAddDTO 商品
     * @return JsonResult
     */
    @ApiOperation("增加购物车")
    @PostMapping("/add")
    public JsonResult addCart(@Validated CartAddDTO cartAddDTO){
        cartService.addCart(cartAddDTO);
        return JsonResult.ok();
    }

    /**
     * 删除购物车
     * @param id 购物车id
     * @return JsonResult
     */
    @ApiOperation("删除购物车")
    @PostMapping("/delete/{id}")
    public JsonResult deleteCartById(@PathVariable @NotNull Long id){
        cartService.deleteCartById(id);
        return JsonResult.ok();
    }

    /**
     * 更新购物车
     * @param cartUpdateDTO 购物车更新DTO
     * @return JsonResult
     */
    @ApiOperation("更新购物车")
    @PostMapping("/update")
    public JsonResult updateCartByCartUpdateDTO(@Validated CartUpdateDTO cartUpdateDTO){
        cartService.updateCartByCartUpdateDTO(cartUpdateDTO);
        return JsonResult.ok();
    }

    @ApiOperation("更新购物车")
    @GetMapping("/{userId}")
    public JsonResult getCartByUserId(@PathVariable @NotNull Long userId){
        List<CartPO> list =  cartService.getCartByUserId(userId);
        return JsonResult.ok(list);
    }
}


