package cn.tedu.mall.service.rocketmq.consumers;

import cn.tedu.mall.service.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(
        topic = "product-specs-topic",
        consumerGroup = "productSpecs-consumer-group"
)
public class ProductSpecsConsumer implements RocketMQListener<String> {
    @Autowired
    private IOrderService orderService;

    @Override
    public void onMessage(String message) {
        log.debug("开始处理超时未支付的订单:{}", message);
        orderService.updateOrder2SysCancel(message);
        log.debug("库存销量回退完成");
    }
}
