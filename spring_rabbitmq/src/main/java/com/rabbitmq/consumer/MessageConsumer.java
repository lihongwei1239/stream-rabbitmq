package com.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by lihongwei1239 on 2017/7/30.
 */
public class MessageConsumer implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    public void onMessage(Message message) {
        logger.info("消息消费者 = " + message.toString());
    }
}
