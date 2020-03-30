package com.elasticsearch.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.elasticsearch.model.user;
import com.elasticsearch.service.UserService;
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
