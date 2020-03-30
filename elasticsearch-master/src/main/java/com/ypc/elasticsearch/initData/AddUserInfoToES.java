package com.ypc.elasticsearch.initData;

import com.ypc.elasticsearch.entity.user;
import com.ypc.elasticsearch.repository.UserRepository;
import com.ypc.elasticsearch.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Component
@Order(value=2)
public class AddUserInfoToES implements ApplicationRunner {

	@Resource
	private UserService userService;

	@Resource
	private UserRepository userRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//添加用书数据到ES
		System.out.println(">>>> add user information ro ES <<<<");
		List<user> userList = userService.selectFromDataBase();
		if (!CollectionUtils.isEmpty(userList)){
			for (user user:userList)
				userRepository.save(user);
		}

	}
}
