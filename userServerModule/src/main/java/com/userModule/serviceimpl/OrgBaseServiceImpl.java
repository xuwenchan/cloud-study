package com.userModule.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.Model.OrgBaseInfoModel;
import com.cloud.util.ServiceRsObjModel;
import com.userModule.mapper.OrgBaseMapper;
import com.userModule.service.OrgBaseService;

@Service
public class OrgBaseServiceImpl implements OrgBaseService{

	@Autowired
	OrgBaseMapper orgBaseMapper;
	
	@Override
	public ServiceRsObjModel<OrgBaseInfoModel> getOrgInfo(OrgBaseInfoModel model) {
		ServiceRsObjModel<OrgBaseInfoModel> result=new ServiceRsObjModel<OrgBaseInfoModel>();
		try {
			OrgBaseInfoModel orgResult=orgBaseMapper.getOrgInfo(model);
			result.setSuccess(true);
			result.setRsData(orgResult);
		}catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
