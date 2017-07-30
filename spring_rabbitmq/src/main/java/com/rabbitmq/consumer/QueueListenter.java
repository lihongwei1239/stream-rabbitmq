package com.rabbitmq.consumer;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Created by lihongwei1239 on 2017/7/30.
 */
@Component
public class QueueListenter implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(QueueListenter.class);

    @Override
    public void onMessage(Message message) {

        try{
            logger.info("接收消息 开始");
            logger.info("接收消息  响应报文：message："+message.toString());
            logger.info("接收消息 结束");

        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

    }
}
