package com.userModule.service;

import com.cloud.Model.UserModel;
import com.cloud.util.ServiceRsObjModel;

public interface UserService {

	ServiceRsObjModel<UserModel> queryUserInfo(UserModel user);

}
