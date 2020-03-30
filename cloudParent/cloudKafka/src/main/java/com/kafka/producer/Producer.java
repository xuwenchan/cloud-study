package com.kafka.producer;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling //开启定时器功能
@Component
public class Producer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Scheduled(fixedRate = 2000)//间隔为2s 通过StringRedisTemplate向对象redis消息队列chat发送消息
    public void send(){
        Message message=new Message();
        message.setId("1");
        message.setName("慧儿");
        message.setDescription("I love you");
        kafkaTemplate.send("topic1", JSON.toJSONString(message));
    }

}
