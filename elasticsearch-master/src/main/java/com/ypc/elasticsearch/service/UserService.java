package com.ypc.elasticsearch.service;


import java.util.List;

import com.ypc.elasticsearch.entity.user;

public interface UserService {

	user queryById(Integer id);

	List<user> queryAll();

	List<user> selectFromDataBase();
	
	user queryUserById(Integer id);
}
