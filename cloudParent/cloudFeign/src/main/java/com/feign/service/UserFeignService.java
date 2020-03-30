package com.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cloud.Model.UserModel;

@FeignClient(value="userModule")
@Component
public interface UserFeignService {

	@RequestMapping(value="/queryuserinfo",method=RequestMethod.POST)
	public UserModel queryuserinfo(@RequestBody UserModel model);

}
