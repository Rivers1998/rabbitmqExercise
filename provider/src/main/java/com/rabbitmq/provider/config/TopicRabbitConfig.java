package com.rabbitmq.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * topic交换机
 *
 * @auther River
 * @date 2021/3/28 23:02
 */
@Configuration
public class TopicRabbitConfig {

    // 绑定键
    private final static String man = "topic.man";
    private final static String woman = "topic.woman";

    @Bean
    public Queue firstQueue() {

        return new Queue(TopicRabbitConfig.man);
    }

    @Bean
    public Queue secondQueue() {

        return new Queue(TopicRabbitConfig.woman);
    }

    @Bean
    public TopicExchange exchange() {

        return new TopicExchange("topicExchange");
    }

    /**
     * 将firstQueue队列和topicExchange交换机绑定,而且绑定的键值为man
     * 这样只有消息携带的路由键是topic.man,才会分发到该队列
     */
    @Bean
    public Binding bindingExchangeMessage() {

        return BindingBuilder.bind(firstQueue()).to(exchange()).with(man);
    }

    /**
     * 将secondQueue队列和topicExchange交换机绑定,而且绑定的时候用上通配路由键规则topic.#
     * 这样只要消息携带建的路由键是topic开头,都会分发到该队列
     */
    @Bean
    public Binding bindingExchangeMessage2() {

        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }
}
