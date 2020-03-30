package com.userModule.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.Model.UserModel;
import com.cloud.util.ServiceRsObjModel;
import com.userModule.mapper.UserMapper;
import com.userModule.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;
	
	@Override
	public ServiceRsObjModel<UserModel> queryUserInfo(UserModel user) {
		ServiceRsObjModel<UserModel> result=new ServiceRsObjModel<UserModel>();
		try {
			UserModel userResult=  userMapper.queryUserInfo(user);
			if(userResult!=null) {
				result.setRsData(userResult);
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
				result.setMessage("没有符合条件的数据");
			}
		}catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServiceRsObjModel addUser(UserModel user) {
		ServiceRsObjModel result=new ServiceRsObjModel();
		try {
			Integer addResult=userMapper.addUser(user);
			if(addResult>0) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		}catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServiceRsObjModel updateUser(UserModel user) {
		ServiceRsObjModel result=new ServiceRsObjModel();
		try {
			Integer updateResult=userMapper.updateUser(user);
			if(updateResult>0) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		}catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServiceRsObjModel delUser(UserModel user) {
		ServiceRsObjModel result=new ServiceRsObjModel();
		try {
			Integer delResult=userMapper.delUser(user);
			if(delResult>0) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		}catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
