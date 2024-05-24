package cn.tedu.mall.service.rocketmq.producers;

import cn.tedu.mall.common.constant.TimeConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;


@Slf4j
@Component
public class ProductSpecsProducer {
    private static final DefaultMQProducer producer = new DefaultMQProducer();

    static {
        producer.setNamesrvAddr("localhost:9876");
        producer.setProducerGroup("product-specs-group01");
        try {
            producer.start();
        } catch (MQClientException e) {
            throw new RuntimeException(e);
        }
    }
//    @Value(value = "${rocketmq.name-server}")
//    public void setNameserverAddr(String nameServerAddr) {
//        //提供rocketmq连接 localhost:9876(nameserver)
//        producer.setNamesrvAddr(nameServerAddr);
//    }
//
//    @Value(value = "${rocketmq.producer.group}")
//    public void setProducerGroup(String producerGroup) {
//        //配置生成者组
//        producer.setProducerGroup(producerGroup);
//
//    }

    public static void sendMsg(String orderNo, int delayLevel) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        Message msg = new Message("product-specs-topic",orderNo.getBytes(StandardCharsets.UTF_8));
        //1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
        msg.setDelayTimeLevel(delayLevel);
        producer.send(msg);
        //producer.shutdown();
    }
}
