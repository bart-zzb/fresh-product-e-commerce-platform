package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.constant.OrderConstants;
import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.front.configuration.AliPayConfig;
import cn.tedu.mall.service.pojo.bo.OrderDetailBO;
import cn.tedu.mall.service.pojo.vo.OrderItemsVO;
import cn.tedu.mall.service.service.IOrderService;
import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(tags = "支付宝测试模块")
@Slf4j
@RestController
@RequestMapping("/alipay")
public class AliPayController {
    // 支付宝沙箱网关地址
    private static final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private static final String FORMAT = "JSON";
    private static final String CHARSET = "UTF-8";
    // 签名方式
    private static final String SIGN_TYPE = "RSA2";

    @Autowired
    private AliPayConfig aliPayConfig;

    @Autowired
    private IOrderService orderService;

    @ApiOperation("支付宝支付")
    @GetMapping("/pay")
    public JsonResult aliPay(String orderNo, HttpServletResponse httpServletResponse) throws IOException {
        //查询订单信息
        if (orderNo == null) {
            return JsonResult.fail(ServiceCode.ERR_ORDER_NOT_EXIST, ServiceConstant.ORDER_NOT_EXIST);
        }
        //判断订单是否存在, 且未支付
        OrderDetailBO orderDetailBO = orderService.getUnpaidOrderByOrderNo(orderNo);
        if (orderDetailBO == null) {
            return JsonResult.fail(ServiceCode.ERR_ORDER_NOT_EXIST, ServiceConstant.ORDER_NOT_EXIST);
        } else if (!orderDetailBO.getStatus().equals(OrderConstants.UNPAID.getValue())) {
            return JsonResult.fail(ServiceCode.ERR_ORDER_ALREADY_PAID, ServiceConstant.ORDER_ALREADY_PAID);
        }
        //创建Client,通用SDK提供的Client,负责调用支付宝的API
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(), aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);

        //创建Request并设置Request参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", orderDetailBO.getOrderNo());
        bizContent.put("total_amount", orderDetailBO.getOrderAmountTotal());
        StringBuilder subject = new StringBuilder();
        List<OrderItemsVO> orderItemsVOS = orderDetailBO.getOrderItemsVOS();
        for (OrderItemsVO orderItemsVO : orderItemsVOS) {
            subject.append(orderItemsVO.getTbProductName() + ",");
        }
        bizContent.put("subject", subject.substring(0, subject.length() - 1));//支付名称
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");//固定配置
        request.setBizContent(bizContent.toString());
        request.setReturnUrl("http://localhost:9090/order");//返回前端页面

        String form = "";

        try {
            form = alipayClient.pageExecute(request).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpServletResponse.setContentType("text/html;charset=" + CHARSET);// 直接将完整的表单html输出到页面
        httpServletResponse.getWriter().write(form);
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
        return JsonResult.ok();
    }
}
