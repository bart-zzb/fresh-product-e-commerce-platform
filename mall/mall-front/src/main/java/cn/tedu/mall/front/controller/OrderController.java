package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.dto.OrderAddDTO;
import cn.tedu.mall.service.pojo.dto.OrderUpdateDTO;
import cn.tedu.mall.service.pojo.po.OrderPO;
import cn.tedu.mall.service.pojo.vo.OrderVO;
import cn.tedu.mall.service.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Api(tags = "订单模块")
@Slf4j
@Validated
@RestController
@RequestMapping("mall/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    /**
     * 增加订单
     *
     * @param orderAddDTO 订单
     * @return JsonResult
     */
    @ApiOperation("增加订单")
    @PostMapping("/add")
    public JsonResult addOrder(@Validated OrderAddDTO orderAddDTO) {
        orderService.addOrder(orderAddDTO);
        return JsonResult.ok();
    }

    /**
     * 更新订单状态
     *
     * @param orderUpdateDTO 更新订单状态
     * @return JsonResult
     */
    @ApiOperation("更新订单状态")
    @PostMapping("/update")
    public JsonResult updateOrder(@Validated OrderUpdateDTO orderUpdateDTO) {
        orderService.updateOrder(orderUpdateDTO);
        return JsonResult.ok();
    }

    /**
     * 查询用户所有订单
     *
     * @param userId 查询用户所有订单
     * @return JsonResult(List < OrderPO >)
     */
    @ApiOperation("查询用户所有订单")
    @GetMapping("/select/{userId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Long"),
    })
    public JsonResult getOrderByUserId(@PathVariable @NotNull(message = "用户id不能为空") Long userId) {
        List<OrderVO> orderPOList = orderService.getOrderByUserId(userId);
        return JsonResult.ok(orderPOList);
    }
}
