package com.rabbitmq._04_Routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lihongwei1239 on 2017/7/29.
 */
public class ReceiveLogsDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws  Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.210");
        factory.setPassword("hxb");
        factory.setUsername("hxb");

        //新建连接
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //设置交换区类型
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();

        String severity = getSeverity(args);
        channel.queueBind(queueName,EXCHANGE_NAME,"A");
        channel.queueBind(queueName,EXCHANGE_NAME,"B");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        while (true){

            Thread.sleep(2000);
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();

            System.out.println(" [x] Received '" + routingKey + "':'" + message + "'");
        }
    }

    private static String getSeverity(String[] strings){

        List<String> routingKey = new ArrayList<String>();
        routingKey.add("A");
        routingKey.add("B");

        return routingKey.get(new Random().nextInt(2));
    }
}
