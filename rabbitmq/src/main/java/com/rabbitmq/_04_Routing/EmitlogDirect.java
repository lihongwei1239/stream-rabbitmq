package com.rabbitmq._04_Routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lihongwei1239 on 2017/7/29.
 */
public class EmitlogDirect {


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
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        String severity = getSeverity(args);
        String message = getMessage(args);

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Thread.sleep(1000);
            //发送消息 绑定交换区 绑定路由
            channel.basicPublish(EXCHANGE_NAME,severity,null,message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

        channel.close();
        connection.close();

    }

    private static String getMessage(String[] strings){
        if(strings.length < 1){
            return "hello world";
        }
        return joinStrings(strings," ");
    }

    private static String joinStrings(String[] strings,String delimiter){
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }

    private static String getSeverity(String[] strings){

        List<String> routingKey = new ArrayList<String>();
        routingKey.add("A");
        routingKey.add("B");

        return routingKey.get(new Random().nextInt(2));
    }
}
