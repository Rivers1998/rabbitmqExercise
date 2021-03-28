package com.rabbitmq.provider.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @auther River
 * @date 2021/3/28 17:04
 */
@RestController
public class SendMessageController {


    @Autowired
    private RabbitTemplate rabbitTemplate;// 模板,封装了很多能直接使用的方法(发送/接收....)

    @GetMapping("/send/direct/message")
    public String sendDirectMessage() {

        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test send message, Hello World!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Map<String ,String> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);

        // 将消息携带绑定键值TestDirectRouting发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting",map);

        return "OK";
    }

    @GetMapping("/send/topic/message1")
    public String sendTopicMessage1() {

        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message : man";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Map<String ,String> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        // 该消息会进入firstQueue和secondQueue两个队列
        rabbitTemplate.convertAndSend("topicExchange","topic.man", manMap);

        return "OK";
    }

    @GetMapping("/send/topic/message2")
    public String sendTopicMessage2() {

        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message : WOMAN";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Map<String ,String> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
        // 该消息会进入secondQueue队列
        rabbitTemplate.convertAndSend("topicExchange","topic.a", womanMap);

        return "OK";
    }

    @GetMapping("/send/fanout/message")
    public String sendFanoutMessage() {

        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message : WOMAN";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Map<String ,String> fanoutMap = new HashMap<>();
        fanoutMap.put("messageId", messageId);
        fanoutMap.put("messageData", messageData);
        fanoutMap.put("createTime", createTime);
        // 无需绑定路由键
        rabbitTemplate.convertAndSend("fanoutExchange",null, fanoutMap);

        return "OK";
    }
}
