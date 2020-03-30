package com.userModule.message;

import org.springframework.stereotype.Component;


@Component
public class MessageReceive {

    public void receiveMessage(String message){
        System.out.println("接收一条消息："+message);
    }



}
