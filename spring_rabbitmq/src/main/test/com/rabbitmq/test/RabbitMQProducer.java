package com.rabbitmq.test;

import com.rabbitmq.producer.MQProduce;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lihongwei1239 on 2017/7/30.
 */
public class RabbitMQProducer extends AbstractConfigTest {

    @Autowired
    private MQProduce MQProduce;

    final String queue_key = "test_queue";

    @Test
    public void sendDataToQueue() throws Exception{

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Thread.sleep(10000);
            MQProduce.sendDataToQueue(queue_key,"hello world");
        }
    }

}
