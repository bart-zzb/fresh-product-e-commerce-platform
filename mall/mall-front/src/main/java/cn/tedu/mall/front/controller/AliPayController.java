package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.constant.OrderConstants;
import cn.tedu.mall.common.constant.ServiceCode;
import cn.tedu.mall.common.constant.ServiceConstant;
import cn.tedu.mall.common.util.PojoConvert;
import cn.tedu.mall.common.web.JsonResult;
import cn.tedu.mall.front.configuration.AliPayConfig;
import cn.tedu.mall.service.pojo.bo.OrderDetailBO;
import cn.tedu.mall.service.pojo.dto.ProductSpecDeleteDTO;
import cn.tedu.mall.service.pojo.vo.OrderItemsVO;
import cn.tedu.mall.service.service.IOrderService;
import cn.tedu.mall.service.service.IProductSpecsService;
import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private IProductSpecsService productSpecsService;

    @ApiOperation("支付宝支付")
    @GetMapping("/pay")
    public void aliPay(String orderNo, HttpServletResponse httpServletResponse) throws IOException {
        //查询订单信息
        if (orderNo == null) {
            return;
            //return JsonResult.fail(ServiceCode.ERR_ORDER_NOT_EXIST, ServiceConstant.ORDER_NOT_EXIST);
        }
        //判断订单是否存在, 且未支付
        OrderDetailBO orderDetailBO = orderService.getUnpaidOrderByOrderNo(orderNo);
        if (orderDetailBO == null) {
            return;
            //return JsonResult.fail(ServiceCode.ERR_ORDER_NOT_EXIST, ServiceConstant.ORDER_NOT_EXIST);
        } else if (!orderDetailBO.getStatus().equals(OrderConstants.UNPAID.getValue())) {
            return;
            //return JsonResult.fail(ServiceCode.ERR_ORDER_ALREADY_PAID, ServiceConstant.ORDER_ALREADY_PAID);
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
    }

    @PostMapping("/notify")
    public JsonResult payNotify(HttpServletRequest request) throws Exception {
        log.debug("======支付宝异步回调======");
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }
            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, aliPayConfig.getAlipayPublicKey(), "UTF-8");
            //支付宝验签
            if (checkSignature) {
                log.debug("交易名称:" + params.get("subject"));
                log.debug("交易状态:" + params.get("trade_status"));
                log.debug("支付宝交易凭证号:" + params.get("trade_no"));
                log.debug("商户订单号:" + params.get("out_trade_no"));
                log.debug("交易金额:" + params.get("total_amount"));
                log.debug("买家在支付宝唯一id:" + params.get("buyer_id"));
                log.debug("买家付款时间:" + params.get("gmt_payment"));
                log.debug("买家付款金额:" + params.get("buyer_pay_amount"));

                String tradeNo = params.get("out_trade_no");
                String gmtPayment = params.get("gmt_payment");
                String alipayTradeNo = params.get("trade_no");
                //更新订单状态为已支付, 设置支付信息
                OrderDetailBO orderDetailBO = orderService.getUnpaidOrderByOrderNo(tradeNo);
                orderDetailBO.setStatus(OrderConstants.PAID.getValue());
                orderService.updateOrder(orderDetailBO);
                //更新库存, 更新销量
                List<OrderItemsVO> orderItemsVOS = orderDetailBO.getOrderItemsVOS();
                List<ProductSpecDeleteDTO> productSpecDeleteDTOS = PojoConvert.convertList(orderItemsVOS, ProductSpecDeleteDTO.class);
                productSpecsService.deleteProductSpecsAmount(orderDetailBO.getId(), productSpecDeleteDTOS);
            }
            return JsonResult.ok();
        }
        return JsonResult.fail(ServiceCode.ERROR_PAY_FAILED, ServiceConstant.PAY_FAILED);
    }
}
