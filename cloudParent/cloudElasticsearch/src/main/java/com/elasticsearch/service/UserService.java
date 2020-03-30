package com.elasticsearch.service;


import java.util.List;

import com.elasticsearch.model.user;


public interface UserService {

	user queryById(Integer id);

	List<user> queryAll();

	List<user> selectFromDataBase();
	
	user queryUserById(Integer id);
	
	
}
