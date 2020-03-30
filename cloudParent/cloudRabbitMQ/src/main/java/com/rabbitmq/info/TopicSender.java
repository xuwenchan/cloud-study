package com.rabbitmq.info;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling //开启定时器功能
@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    @Scheduled(fixedRate = 2000)//间隔为2s 通过StringRedisTemplate向对象redis消息队列chat发送消息
    public void send1(){
        String context="hi,慧儿，我给你唱歌好吗?";
        System.out.println("发送消息："+context);
        rabbitmqTemplate.convertAndSend("topicExchange","topic.message",context);
    }

    public void send2(){
        String context="hi,慧儿，你给我唱歌听好吗？";
        System.out.println("发送内容:"+context);
        rabbitmqTemplate.convertAndSend("topicExchange","topic.messages",context);
    }
}
