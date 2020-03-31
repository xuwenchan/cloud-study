package com.kafka.producer;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling //开启定时器功能
@Component
public class Producer {

    @Autowired
    private KafkaTemplate<Object,Object> kafkaTemplate;

    /*
    * kafka有三种发送消息的方式
    * 1，立即发送：只需要把消息发送到服务器，而不关心消息发送的结果，
    * 2，同步发送：调用send方法发送消息后，获取该方法返回的Future对象，
    * 根据对象的结果查看send方法调用的是否成功
    * 3，异步发送：先注册一个回调函数，通过调用另一个send方法发送消息时把回调函数
    * 当做参数传入，这样当生产者收到kafka服务器的响应就会执行回调函数
    *
    *
    * */

    //发送方式：立即发送
    //通过定时器将信息发送到指定的topic
    @Scheduled(fixedRate = 2000)//间隔为2s 通过StringRedisTemplate向对象redis消息队列chat发送消息
    public void send(){
        Message message=new Message();
        message.setId("1");
        message.setName("慧儿");
        message.setDescription("I love you");
        kafkaTemplate.send("topic1", JSON.toJSONString(message));
    }

    //发送方式:同步发送
    @Scheduled(fixedRate = 3000)
    public void send_sync(){
        Message message=new Message();
        message.setId("2");
        message.setName("慧儿");
        message.setDescription("测试同步发送");
        try{
            kafkaTemplate.send(new ProducerRecord<>("topic1","test-key",JSON.toJSONString(message))).get();
        }catch(Exception e){
            System.out.println("发送失败");
            e.printStackTrace();
        }
    }

    /*异步发送测试有问题*/
    @Scheduled(fixedRate = 4000)
    public void send_async(){
        Message message=new Message();
        message.setId("3");
        message.setName("慧儿");
        message.setDescription("测试异步发送");

        kafkaTemplate.send(String.valueOf(new ProducerRecord<Object, Object>("topic1",1,"test-key", JSON.toJSONString(message))),new Callback(){
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception!=null){
                    System.out.println("消息发送失败");
                    exception.printStackTrace();
                }
            }
        });
    }

}
