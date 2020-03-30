package com.rabbitmq.consumer;

import com.rabbitmq.info.Messagee;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="hello")
public class HelloReceiver {

    //接收字符串
    /*@RabbitHandler
    public void process(String message){
        System.out.println("receiver:"+message);
    }*/

    @RabbitHandler
    public void process(Messagee messagee){
        System.out.println("接收到消息");
        System.out.println("消息内容为:"+messagee.getMessageBody());
    }


}
