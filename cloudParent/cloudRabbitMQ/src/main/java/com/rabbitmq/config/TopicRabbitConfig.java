package com.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class TopicRabbitConfig {

    final static String message="topic.message";
    final static String messages="topic.messags";

    //创建队列
    @Bean
    public Queue queueMessage(){
        return new Queue(TopicRabbitConfig.message);
    }

    @Bean
    public Queue queueMessages(){
        return new Queue(TopicRabbitConfig.messages);
    }

    //创建一个交换机
    @Bean
    TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    //将队列绑定到topic交换机上
    @Bean
    Binding bindignExchangeMessage(Queue queueMessage,TopicExchange exchange){
      return BindingBuilder.bind(queueMessage).to(exchange).with(TopicRabbitConfig.message);
    }

    //将队列绑定到Topic交换机上，采用#的方式
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages,TopicExchange topicExchange){
        return BindingBuilder.bind(queueMessages).to(topicExchange).with(TopicRabbitConfig.messages);
    }







}
