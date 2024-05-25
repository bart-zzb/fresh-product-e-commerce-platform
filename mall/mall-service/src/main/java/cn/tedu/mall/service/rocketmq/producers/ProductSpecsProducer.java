package cn.tedu.mall.service.rocketmq.producers;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductSpecsProducer {
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.delayLevel}")
    private int delayLevel;

    public void sendMsg(String orderNo) {
        Message<String> msg = MessageBuilder
                .withPayload(orderNo).build();

        //delay level:1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
        rocketMQTemplate.asyncSend("product-specs-topic", msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.debug("发送成功");
            }

            @Override
            public void onException(Throwable e) {
                log.debug("发送失败");
            }
        },2000, delayLevel);
        //2.4 异步延迟消息
        log.debug("发送完毕");
    }
}
