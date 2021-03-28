package com.rabbitmq.provider.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连交换机
 *
 * @auther River
 * @date 2021/3/28 16:43
 */
@Configuration
public class DirectRabbitConfig {

    // 队列 起名:TestDirectQueue
    @Bean
    public Queue testDirectQueue() {

        // durable 是否持久化,默认是false
        // exclusive 默认也是false,只在当前连接被创建时使用,而且当前连接关闭后即刻被删除;优先级高于durable
        // autoDelete 是否自动删除,当没有生产者或者消费者使用此队列时该队列会自动删除
        // 一般设置持久化就行,其余两个默认为false

        return new Queue("TestDirectQueue",true);
    }

    // Direct交换机 起名:TestDirectExchange
    @Bean
    public DirectExchange testDirectExchange() {

        return new DirectExchange("TestDirectExchange",true,false);
    }

    // 绑定 将队列和交换机绑定,并设置用于匹配件:TestDirectRouting
    @Bean
    public Binding bindingDirect() {

        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("TestDirectRouting");
    }

    @Bean
    public DirectExchange lonelyDirectExchange() {

        return new DirectExchange("lonelyDirectExchange");
    }
}
