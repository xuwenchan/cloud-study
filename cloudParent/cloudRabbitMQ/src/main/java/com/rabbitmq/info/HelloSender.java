package com.rabbitmq.info;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@EnableScheduling //开启定时器功能
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    //发送简单的字符串消息
    /*@Scheduled(fixedRate = 2000)//间隔为2s 通过StringRedisTemplate向对象redis消息队列chat发送消息
    public void send(){
        String context="hello"+ UUID.randomUUID().toString()+",huier";
        System.out.println("send:"+context);
        rabbitmqTemplate.convertAndSend("hello",context);
    }*/

    //发送对象
    @Scheduled(fixedRate = 2000)//间隔为2s 通过StringRedisTemplate向对象redis消息队列chat发送消息
    public void send(){
        Messagee messagee =new Messagee();
        messagee.setId(UUID.randomUUID().toString());
        System.out.println("发送了一条消息");
        messagee.setMessageBody("慧儿,我很想你,接收到我的思念了吗?");
        rabbitmqTemplate.convertAndSend("hello",messagee);
    }

}
