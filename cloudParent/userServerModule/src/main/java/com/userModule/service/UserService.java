package com.userModule.service;

import com.cloud.Model.UserModel;
import com.cloud.util.ServiceRsObjModel;

public interface UserService {

	ServiceRsObjModel<UserModel> queryUserInfo(UserModel user);
	
	ServiceRsObjModel addUser(UserModel user);
	
	ServiceRsObjModel updateUser(UserModel user);
	
	ServiceRsObjModel delUser(UserModel user);

}
