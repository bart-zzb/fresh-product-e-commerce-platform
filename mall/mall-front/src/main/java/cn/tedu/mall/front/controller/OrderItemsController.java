package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "订单详情模块")
@Slf4j
@Validated
@RestController
@RequestMapping("mall/order_items")
public class OrderItemsController {
//    /**
//     * 查询用户所有订单
//     * @param currentPrincipal 查询用户所有订单
//     * @return JsonResult(List < OrderPO >)
//     */
//    @ApiOperation("通过订单id查询当前用户的订单详情")
//    @GetMapping("/select/orderId")
//    public JsonResult getOrder
}
