package com.rabbitmq.test;

import com.rabbitmq.producer.MessageProducer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihongwei1239 on 2017/7/30.
 */
public class RabbitMQProducer extends AbstractConfigTest {

    @Autowired
    private MessageProducer messageProducer;

    private String queueId ="test_mq";

    @Test
    public void sendDataToQueue() throws Exception{

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", "hello rabbitmq");
        messageProducer.sendQueue(queueId + "_exchange", queueId + "_patt", map);
    }

}
