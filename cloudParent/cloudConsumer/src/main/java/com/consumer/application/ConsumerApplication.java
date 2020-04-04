package com.consumer.application;

import com.rabbion.rule.MyIRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.consumer"})
/*
*   在客户端主启动类上添加@RibbonClient注解；
    name属性：是客户端要访问的微服务名字
    configuration属性：是自定义负载均衡算法类。
*
* */
@RibbonClient(name = "userModule",configuration = MyIRule.class)//在MICROSERVICECLOUD-DEPT服务上使用MyIRule自定义负载均衡算法
public class ConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
	
	@Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
}
