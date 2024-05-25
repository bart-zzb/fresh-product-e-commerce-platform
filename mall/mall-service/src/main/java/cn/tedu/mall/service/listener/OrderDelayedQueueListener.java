package cn.tedu.mall.service.listener;

import cn.tedu.mall.common.constant.RedisConstants;
import cn.tedu.mall.service.service.IOrderService;
import cn.tedu.mall.service.service.IRedissonDelayedQueueService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingDeque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
//测试Redisson实现延时队列消费时才开启
//@Component
public class OrderDelayedQueueListener {
    @Autowired
    private IRedissonDelayedQueueService redissonDelayedQueueService;

    @Autowired
    private IOrderService orderService;

    private final ExecutorService singlePoolExecutor
            = Executors.newSingleThreadExecutor();
    /**
     * 每次
     */
    @PostConstruct
    public void listener() {
        singlePoolExecutor.execute(()->{
            while (true){
                RBlockingDeque<String> blockingDeque = redissonDelayedQueueService.getQueue(RedisConstants.REDIS_KEY_ORDER);
                String orderNo  = null;
                try {
                    orderNo = blockingDeque.take();
                } catch (InterruptedException e) {
                    log.error("消费异常",e);
                }
                log.info("获取到订单队列:{}",orderNo);
                orderService.updateOrder2SysCancel(orderNo);
            }
        });
    }
}

