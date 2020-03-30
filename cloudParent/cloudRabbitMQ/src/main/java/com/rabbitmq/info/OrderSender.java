package com.rabbitmq.info;


import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendOrder(){
        //交换机
        Order order=new Order();
        String exchange ="order-exchange";
        //路由规则
        String routingkey="order.test";
        CorrelationData correlationData=new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());
        order.setMessageId(UUID.randomUUID().toString());
        order.setName("xuyi:"+System.currentTimeMillis());
        order.setId(System.currentTimeMillis()+"");
        //第一个参数：生产者要发送的交换机
        //第二个参数：消息路由规则
        //第三个参数：消息体
        //第四个参数：消息体唯一id
        rabbitTemplate.convertAndSend(exchange,routingkey,order,correlationData);

    }

}
