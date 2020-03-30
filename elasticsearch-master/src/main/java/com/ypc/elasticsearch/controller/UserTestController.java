package com.ypc.elasticsearch.controller;

import com.ypc.elasticsearch.entity.user;
import com.ypc.elasticsearch.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserTestController {

	@Resource
	private UserService userService;

	@RequestMapping("/test/{id}")
	public user queryById(@PathVariable(name = "id") Integer id){
		return userService.queryById(id);
	}
	
	/**
	 * 调用原生的语句查询
	 */
	@RequestMapping("queryUserById")
	public user queryUserById(Integer id) {
		return userService.queryUserById(id);
	}
}
