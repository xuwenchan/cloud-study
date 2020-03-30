package com.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.Model.OrgBaseInfoModel;
import com.cloud.util.ServiceRsObjModel;
import com.consumer.service.OrgBaseInfoService;

@RestController
public class OrgBaseInfoController {

	@Autowired
	OrgBaseInfoService orgBaseInfoService;
	
	@RequestMapping("queryOrgBaseInfo")
	public void queryOrgBaseInfo(OrgBaseInfoModel org) {
		ServiceRsObjModel<OrgBaseInfoModel> result= orgBaseInfoService.queryOrgBaseInfo(org);
	}
}
