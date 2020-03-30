package com.userModule.mapper;

import org.springframework.stereotype.Repository;

import com.cloud.Model.UserModel;

@Repository
public interface UserMapper {

	UserModel queryUserInfo(UserModel user);

}
