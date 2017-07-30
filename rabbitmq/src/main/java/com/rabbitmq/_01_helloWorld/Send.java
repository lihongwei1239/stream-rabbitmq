package com.rabbitmq._01_helloWorld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

//提供者
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{

        //新建发送者渠道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.210");
        factory.setUsername("hxb");
        factory.setPassword("hxb");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //发送消息
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message = "Hello World";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        //关闭渠道
        channel.close();
        connection.close();
    }

}
