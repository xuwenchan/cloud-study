package com.rabbitmq.customer;

import com.rabbitmq.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;

@Component
public class OrderConsumer {

    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(name = "order-exchange", durable = "true", type = "topic"),
                    key = "order.#",
                    value = @Queue(value = "order-queue", durable = "true")
            )
    )
    public void onOrderMessage(@Payload Order order, @Headers Map<String, Object> headers, Channel channel){
        // 消费者操作
        System.err.println("--收到消息，开始消费--");
        System.err.println("the data what " + order.getId()+":"+order.getName()+":"+order.getMessageId());

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        // 手工签收
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
