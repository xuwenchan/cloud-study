package com.elasticsearch.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.elasticsearch"})
@EnableElasticsearchRepositories(basePackages = "com.elasticsearch.repository")
public class ElasticSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchApplication.class, args);
		System.out.println("elasticsearch开启成功。。。。。。");
	}
}
