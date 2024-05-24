package cn.tedu.mall.service.service;

import org.redisson.api.RBlockingDeque;

import java.util.concurrent.TimeUnit;

public interface IRedissonDelayedQueueService {
    //Redisson有一个RDelayedQueue是一个专门用于处理延迟任务的队列，RDelayedQueue不会阻止生产者向已满的队列中添加元素，而是会将它们放入一个等待队列中，直到有空间可用。允许元素在一定时间后被重新入队，或者在指定的时间间隔内被消费。下面我们来实现延迟队列：
    //核心思想是通过线程执行redission的延时队列（DelayedQueue），通过take()方法尝试从队列中取出元素。
    //add为添加一个元素到等待队列，封装到延时队列中
    <E> void addQueue(E e, long delay, TimeUnit timeUnit, String queueName);

    //get从延时队列中获取一个等待队列
    <E> RBlockingDeque<E> getQueue(String queueName);

    //为删除队列中的元素，适用于某些想直接删除已经添加的键值场景，支付成功后需从队列中删除
    <E> void removeQueueElement(E e, String queueName);
}
