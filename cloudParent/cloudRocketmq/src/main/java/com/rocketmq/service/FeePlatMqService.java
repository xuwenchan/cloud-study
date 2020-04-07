package com.rocketmq.service;

import org.apache.rocketmq.client.producer.SendResult;

public interface FeePlatMqService {
    public SendResult openAccountMsg(String msgInfo);
}
