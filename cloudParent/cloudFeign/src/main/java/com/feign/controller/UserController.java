package com.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.Model.UserModel;
import com.feign.service.UserFeignService;

@RestController
public class UserController {
	
	@Autowired
	UserFeignService userFeignService;

	@RequestMapping(value="queryUserInfo",method=RequestMethod.POST)
	public void queryUserInfo(UserModel model) {
		UserModel user=userFeignService.queryuserinfo(model);
		if(user!=null) {
			System.out.println(user.getUid()+","+user.getPhone()+","+user.getUserAddress());
		}else {
			System.out.println("数据为空");
		}
	}
	
}
