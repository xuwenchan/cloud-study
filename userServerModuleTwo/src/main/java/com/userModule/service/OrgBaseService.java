package com.userModule.service;

import com.cloud.Model.OrgBaseInfoModel;
import com.cloud.util.ServiceRsObjModel;

public interface OrgBaseService {
	/**
	 * 查询单位的信息
	 * @param model
	 * @return
	 */
	ServiceRsObjModel<OrgBaseInfoModel> getOrgInfo(OrgBaseInfoModel model);

}
