package com.rabbitmq._03_Publish_Subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Created by lihongwei1239 on 2017/7/29.
 */
public class ReceiveLogs {

    private static final String EXCHANGE_NAME="logs";

    public  static void main(String[] args) throws Exception {

        //新建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.210");
        factory.setPassword("hxb");
        factory.setUsername("hxb");

        //新建连接
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //设置交换区类型
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //获取随机的队列名称
        String queueName = channel.queueDeclare().getQueue();
        //交换区和队列绑定
        channel.queueBind(queueName,EXCHANGE_NAME,"");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        //消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName,true,consumer);

        //接收消息
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }

    }


}
