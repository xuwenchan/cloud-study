package com.userModule.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.userModule"})
//mapper的扫描路径
@MapperScan("com.userModule.mapper")
/*@ImportResource(locations = {"classpath:"})*/
public class UserModuleApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserModuleApplication.class, args);
	}
}
