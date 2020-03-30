package com.userModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.Model.UserModel;
import com.cloud.util.ServiceRsObjModel;
import com.userModule.service.UserService;

@RestController
public class userController {

	@Autowired
	UserService userService;
	
	@RequestMapping("hello")
	public void test(String name) {
		System.out.println("hello,"+name);
	}
	
	@RequestMapping(value="queryuserinfo",method=RequestMethod.POST)
	public UserModel queryUserInfo(UserModel user) {
		ServiceRsObjModel<UserModel> result=  userService.queryUserInfo(user);
		if(result.isSuccess()) {
			System.out.println(result.getRsData().getUname()+","+result.getRsData().getUserAddress());
			return result.getRsData();
		}
		return user;
	}
}
