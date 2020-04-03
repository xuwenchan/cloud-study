package com.order.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.order"})
@MapperScan("com.order.mapper")
@EnableEurekaClient
public class OrderApplication {

    public static void main(String[] args){
        SpringApplication.run(OrderApplication.class,args);
    }
}
