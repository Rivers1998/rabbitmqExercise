package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @auther River
 * @date 2021/3/28 23:49
 */
@Component
@RabbitListener(queues = "topic.woman") // 监听名称为topic.woman的队列消息
public class TopicTotalReceiver {

    @RabbitHandler
    public void process(Map message) {

        System.out.println("TopicTotalReceiver消费到的消息:" + message.toString());
    }
}
