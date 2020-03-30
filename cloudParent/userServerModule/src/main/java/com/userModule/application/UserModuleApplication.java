package com.userModule.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @EnableTransactionManagement
 * 开启事务支持
 * @author 徐文产
 */

@EnableTransactionManagement
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.userModule"})
//mapper的扫描路径
@MapperScan("com.userModule.mapper")
public class UserModuleApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserModuleApplication.class, args);
	}
}
