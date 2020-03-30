package com.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.Model.OrgBaseInfoModel;
import com.feign.service.OrgBaseFeignService;

@RestController
public class OrgBaseController {

	@Autowired
	OrgBaseFeignService orgBaseFeignService;
	
	@RequestMapping("queryOrgInfo")
	public void queryOrgInfo(OrgBaseInfoModel model) {
		OrgBaseInfoModel orgModel=orgBaseFeignService.getOrgInfo(model);
		if(orgModel!=null) {
			System.out.println("调用成功");
		}else {
			System.out.println("调用失败");
		}
	}
}
