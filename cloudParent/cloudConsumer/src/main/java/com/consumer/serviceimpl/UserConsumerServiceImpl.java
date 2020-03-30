package com.consumer.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.consumer.service.UserConsumerService;

public class UserConsumerServiceImpl implements UserConsumerService {

	 @Autowired
	 RestTemplate restTemplate;
	
}
