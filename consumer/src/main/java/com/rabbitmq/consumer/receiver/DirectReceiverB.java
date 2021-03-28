package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @auther River
 * @date 2021/3/28 22:58
 */
@Component
@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiverB {

    @RabbitHandler
    public void process(Map message) {

        System.out.println("第二个DirectReceiver消费到的消息:" + message.toString());
    }
}
