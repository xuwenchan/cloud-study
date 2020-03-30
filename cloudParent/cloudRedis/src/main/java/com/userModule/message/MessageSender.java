package com.userModule.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling //开启定时器功能
@Component
public class MessageSender {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Scheduled(fixedRate = 2000)//间隔为2s 通过StringRedisTemplate向对象redis消息队列chat发送消息
    public void sendMessage(){
        stringRedisTemplate.convertAndSend("chat","我爱你，你知道吗？收到请回复");
    }




}
