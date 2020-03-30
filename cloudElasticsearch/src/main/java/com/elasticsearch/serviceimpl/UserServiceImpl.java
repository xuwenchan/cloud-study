package com.elasticsearch.serviceimpl;

import org.springframework.stereotype.Service;

import com.elasticsearch.model.user;
import com.elasticsearch.repository.UserRepository;
import com.elasticsearch.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;

	
	@Override
	public user queryById(Integer id) {
		System.out.println(">>>> query user from ES <<<<");
		Optional<user> result = userRepository.findById(id);
		if (result.isPresent())
			return result.get();

		return null;
	}

	@Override
	public List<user> queryAll() {
		return null;
	}

	@Override
	public List<user> selectFromDataBase() {
		return null;
	}

	@Override
	public user queryUserById(Integer id) {
		System.out.println("原生查询");
		Optional<user> result=userRepository.queryUserById(id);
		if(result!=null) {
			return result.get();
		}
		return null;
	}
}
