package com.rabbitmq.producer;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by lihongwei1239 on 2017/7/30.
 */
@Service
public class MQProducerImpl implements MQProduce {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final Logger logger = LoggerFactory.getLogger(MQProducerImpl.class);

    @Override
    public void sendDataToQueue(String queueKey, Object object) {

            try{
                logger.info("发送消息 开始");
                logger.info("发送消息  请求报文：queueKey:"+queueKey+",object:"+ JSON.toJSONString(object));
                amqpTemplate.convertAndSend(queueKey,object);
                logger.info("发送消息 结束");
            }catch (Exception e){
                logger.error(e.getMessage(),e);
            }


    }
}
