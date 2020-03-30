package com.userModule.service;

import java.util.List;

import com.cloud.Model.Unpay;
import com.cloud.util.PageView;
import com.cloud.util.ServiceRsObjModel;

public interface UnpayService {

	ServiceRsObjModel addUnpay(Unpay unpay);
	
	ServiceRsObjModel delUnpay(Unpay unpay);
	
	ServiceRsObjModel updateUnpay(Unpay unpay);
	
	ServiceRsObjModel<List<Unpay>> queryUnpay(Unpay unpay);
	
	ServiceRsObjModel<PageView<Unpay>> queryUnpayPage(PageView<Unpay> pageView,Unpay unpay);
	
	
	
	
	
	
	
}
