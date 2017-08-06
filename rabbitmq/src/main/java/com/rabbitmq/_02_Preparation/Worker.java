package com.rabbitmq._02_Preparation;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Created by lihongwei1239 on 2017/7/28.
 */
public class Worker {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.210");
        factory.setUsername("hxb");
        factory.setPassword("hxb");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(TASK_QUEUE_NAME,false,consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            Thread.sleep(2000);
            System.out.println(" [x] Received '" + message + "'");
            doWork(message);
            System.out.println(" [x] Done" );

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }

    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
            if (ch == '.') Thread.sleep(2000);
        }
    }
}
