package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听队列消费消息
 *
 * @auther River
 * @date 2021/3/28 17:41
 */
@Component
@RabbitListener(queues = "TestDirectQueue") // 监听队列的名称TestDirectQueue
public class DirectReceiver {

    @RabbitHandler
    public void process(Map message) {

        System.out.println("DirectReceiver消费到的消息:" + message.toString());
    }
}
