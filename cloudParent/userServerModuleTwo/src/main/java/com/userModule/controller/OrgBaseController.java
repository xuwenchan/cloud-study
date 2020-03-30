package com.userModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.Model.OrgBaseInfoModel;
import com.cloud.util.ServiceRsObjModel;
import com.userModule.service.OrgBaseService;

@RestController
public class OrgBaseController {

	@Autowired
	OrgBaseService orgBaseService;
	
	@RequestMapping("getOrgInfo")
	public OrgBaseInfoModel getOrgInfo(OrgBaseInfoModel model) {
		System.out.println("userServerModuleTwo..............................");
		ServiceRsObjModel<OrgBaseInfoModel> orgResult=orgBaseService.getOrgInfo(model);
		if(orgResult.isSuccess()) {
			return orgResult.getRsData();
		}else {
			return model;
		}
		
	}
}
