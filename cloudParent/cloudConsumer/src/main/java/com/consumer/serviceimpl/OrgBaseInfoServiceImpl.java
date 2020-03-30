package com.consumer.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cloud.Model.OrgBaseInfoModel;
import com.cloud.util.ServiceRsObjModel;
import com.consumer.service.OrgBaseInfoService;

@Service
public class OrgBaseInfoServiceImpl implements OrgBaseInfoService{
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public ServiceRsObjModel<OrgBaseInfoModel> queryOrgBaseInfo(OrgBaseInfoModel org) {
		ServiceRsObjModel<OrgBaseInfoModel> result=new ServiceRsObjModel<OrgBaseInfoModel>();
		try {
			OrgBaseInfoModel model=restTemplate.getForObject("http://userModule/getOrgInfo?orgId="+org.getOrgId(), OrgBaseInfoModel.class);
			result.setRsData(model);
			result.setSuccess(true);
		}catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
