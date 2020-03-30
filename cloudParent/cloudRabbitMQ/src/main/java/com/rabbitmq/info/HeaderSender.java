package com.rabbitmq.info;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HeaderSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void creditBank(Map<String,Object> header,String msg){
        System.out.println("credit.bank send message:"+msg);
        rabbitTemplate.convertAndSend("creditBankExchange","credit.bank",getMessage(header,msg));

    }

    public void creditFinance(Map<String, Object> head, String msg){
        System.out.println("credit.finance send message: "+msg);
        rabbitTemplate.convertAndSend("creditFinanceExchange", "credit.finance", getMessage(head, msg));
    }

    private Message getMessage(Map<String, Object> head, Object msg){
        MessageProperties messageProperties = new MessageProperties();
        for (Map.Entry<String, Object> entry : head.entrySet()) {
            messageProperties.setHeader(entry.getKey(), entry.getValue());
        }
        MessageConverter messageConverter = new SimpleMessageConverter();
        return messageConverter.toMessage(msg, messageProperties);
    }




}
