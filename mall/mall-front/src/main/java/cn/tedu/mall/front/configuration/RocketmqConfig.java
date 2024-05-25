package cn.tedu.mall.front.configuration;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//自动配置一个RocketMQTemplate
@Configuration
public class RocketmqConfig {
    @Value("${rocketmq.name-server}")
    private String nameSrvAddr;

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    @Bean
    public RocketMQTemplate rocketMQTemplate() {
        DefaultMQProducer producer = new DefaultMQProducer();
        //设置name server地址
        producer.setNamesrvAddr(nameSrvAddr);
        //设置生产者分组
        producer.setProducerGroup(producerGroup);
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        rocketMQTemplate.setProducer(producer);
        return rocketMQTemplate;
    }
}
