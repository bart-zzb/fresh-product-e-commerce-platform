package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.annotation.CurrentUser;
import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.service.pojo.authentication.CurrentPrincipal;
import cn.tedu.mall.service.pojo.bo.OrderDetailBO;
import cn.tedu.mall.service.pojo.dto.OrderItemsAddDTO;
import cn.tedu.mall.service.pojo.dto.OrderUpdateConsigneeInfoDTO;
import cn.tedu.mall.service.pojo.dto.OrderUpdatePaidDTO;
import cn.tedu.mall.service.pojo.vo.OrderDetailVO;
import cn.tedu.mall.service.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
     * @param orderItemsAddDTOS 订单详情
     * @return JsonResult
     */
    @ApiOperation("增加订单")
    @PostMapping("/add")
    public JsonResult addOrder(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, @Validated @RequestBody List<OrderItemsAddDTO> orderItemsAddDTOS) throws InterruptedException {
        log.debug("currentPrincipal:{}",currentPrincipal);
        OrderDetailBO orderDetailBO = orderService.addOrder(currentPrincipal.getId(), orderItemsAddDTOS);
        OrderDetailVO orderDetailVO = PojoConvert.convert(orderDetailBO, OrderDetailVO.class);
        return JsonResult.ok(orderDetailVO);
    }

    /**
     * 更新订单从未支付到支付状态
     *
     * @param orderUpdatePaidDTO 更新订单从未支付到支付状态
     * @return JsonResult
     */
    @ApiOperation("更新订单从未支付到支付状态")
    @PostMapping("/update/pay")
    public JsonResult updateOrder2Paid(@CurrentUser CurrentPrincipal currentPrincipal, @Validated OrderUpdatePaidDTO orderUpdatePaidDTO) {
        if (orderUpdatePaidDTO != null) {
            orderUpdatePaidDTO.setTbUserId(currentPrincipal.getId());
            orderService.updateOrder2Paid(orderUpdatePaidDTO);
        }
        return JsonResult.ok();
    }

    /**
     * 更新订单收货信息
     *
     * @param orderUpdateConsigneeInfoDTO 更新订单收货信息
     * @return JsonResult
     */
    @ApiOperation("更新订单收货信息")
    @PostMapping("/update/consignee_info")
    public JsonResult updateOrder(@CurrentUser CurrentPrincipal currentPrincipal, @Validated OrderUpdateConsigneeInfoDTO orderUpdateConsigneeInfoDTO) {
        if (orderUpdateConsigneeInfoDTO != null) {
            orderUpdateConsigneeInfoDTO.setTbUserId(currentPrincipal.getId());
            orderService.updateOrder(orderUpdateConsigneeInfoDTO);
        }
        return JsonResult.ok();
    }


    /**
     * 查询用户所有订单
     *
     * @param currentPrincipal 查询用户所有订单
     * @return JsonResult(List < OrderPO >)
     */
    @ApiOperation("查询当前用户所有订单")
    @GetMapping("/select/all")
    public JsonResult getOrderByUserId(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal) {
        log.debug("currentPrincipal:{}",currentPrincipal);
        List<OrderDetailBO> orderDetailBOS = orderService.getOrderByUserId(currentPrincipal.getId());
        List<OrderDetailVO> orderDetailVOS = PojoConvert.convertList(orderDetailBOS, OrderDetailVO.class);
        return JsonResult.ok(orderDetailVOS);
    }

    /**
     * @param currentPrincipal 当前用户
     * @param status 状态
     * @return JsonResult.ok(orderDetailVOS)
     */
    @ApiOperation("查询当前用户所有相对应的状态的订单")
    @GetMapping("/select/order_status")
    public JsonResult getOrdersByStatus(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, @RequestParam Integer status) {
        List<OrderDetailBO> orderDetailBOS = orderService.getOrdersByStatus(currentPrincipal.getId(), status);
        List<OrderDetailVO> orderDetailVOS = PojoConvert.convertList(orderDetailBOS, OrderDetailVO.class);
        return JsonResult.ok(orderDetailVOS);
    }

    /**
     * 通过订单编号查询当前用户订单
     *
     * @param currentPrincipal 当前用户
     * @param orderNo          订单编号
     * @return JsonResult.ok(orderDetailVO);
     */
    @ApiOperation("通过订单编号查询当前用户订单")
    @GetMapping("/select")
    public JsonResult getOrderByOrderNo(@CurrentUser @ApiIgnore CurrentPrincipal currentPrincipal, String orderNo) {
        if (orderNo.equals("null")) {
            return JsonResult.fail(ServiceCode.ERROR_BAD_REQUEST, ServiceConstant.ORDER_NOT_EXIST);
        }
        OrderDetailBO orderDetailBO = orderService.getOrderByUserIdAndOrderNo(currentPrincipal.getId(), orderNo);
        OrderDetailVO orderDetailVO = PojoConvert.convert(orderDetailBO, OrderDetailVO.class);
        return JsonResult.ok(orderDetailVO);
    }
}
