package com.consumer.service;

import com.cloud.Model.OrgBaseInfoModel;
import com.cloud.util.ServiceRsObjModel;

public interface OrgBaseInfoService {
	/**
	 * 	查询单位的详情信息
	 * @param org
	 * @return
	 */
	ServiceRsObjModel<OrgBaseInfoModel> queryOrgBaseInfo(OrgBaseInfoModel org);

}
