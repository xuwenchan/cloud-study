package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*
* 分布式锁的应用场景
* 我们希望和这个单子关联的所有的表同一时间只能被一个线程来处理更新，
* 多个线程按照不同的顺序去更新同一个单子关联的不同数据，出现死锁的概率比较大。
* 对于非敏感的数据，我们也没有必要去都加乐观锁处理，我们的服务都是多机器部署的，
* 要保证多进程多线程同时只能有一个进程的一个线程去处理，这个时候我们就需要用到分布式锁。
*
* */



@SpringBootApplication
@EnableEurekaClient
public class application {

    public static void main(String[] args){
        SpringApplication.run(application.class,args);
    }
}
