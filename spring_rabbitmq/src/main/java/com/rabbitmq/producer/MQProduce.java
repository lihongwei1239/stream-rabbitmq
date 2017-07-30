package com.rabbitmq.producer;

/**
 * Created by lihongwei1239 on 2017/7/30.
 */
public interface MQProduce {

    /**
     * 发送消息到指定的队列
     * @param queueKey
     * @param object
     */
    public void sendDataToQueue(String queueKey,Object object);
}
