package com.userModule.mapper;

import org.springframework.stereotype.Repository;

import com.cloud.Model.OrgBaseInfoModel;

@Repository
public interface OrgBaseMapper {
	/**
	 * 查询单位信息
	 * @param model
	 * @return
	 */
	OrgBaseInfoModel getOrgInfo(OrgBaseInfoModel model);
	
}
