package com.userModule.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class userController {

	@RequestMapping("hello")
	public void test(String name) {
		System.out.println("hello,"+name);
	}
}
