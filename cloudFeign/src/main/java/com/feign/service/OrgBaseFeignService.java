package com.feign.service;

import com.cloud.Model.OrgBaseInfoModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value="userModule")
@Component
public interface OrgBaseFeignService {

	@RequestMapping(value="getOrgInfo",method=RequestMethod.GET)
	public OrgBaseInfoModel getOrgInfo(OrgBaseInfoModel model);
}
