package com.userModule.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.Model.Unpay;
import com.cloud.util.PageView;
import com.cloud.util.ServiceRsObjModel;
import com.userModule.mapper.UnpayMapper;
import com.userModule.service.UnpayService;

public class UnpaySeviceImpl implements UnpayService{

	@Autowired
	UnpayMapper unpayMapper;
	
	@Override
	/**
	 *	事物传播行为介绍: 
		@Transactional(propagation=Propagation.REQUIRED) 
		如果有事务, 那么加入事务, 没有的话新建一个事务(默认情况下)
		@Transactional(propagation=Propagation.NOT_SUPPORTED) 
		容器不为这个方法开启事务
		@Transactional(propagation=Propagation.REQUIRES_NEW) 
		不管是否存在事务,都创建一个新的事务,原来的挂起,新的执行完毕,继续执行老的事务
		@Transactional(propagation=Propagation.MANDATORY) 
		必须在一个已有的事务中执行,否则抛出异常
		@Transactional(propagation=Propagation.NEVER) 
		必须在一个没有的事务中执行,否则抛出异常(与Propagation.MANDATORY相反)
		@Transactional(propagation=Propagation.SUPPORTS) 
		如果其他bean调用这个方法,在其他bean中声明事务,那就用事务.如果其他bean没有声明事务,那就不用事务.
		
		事物超时设置:
		@Transactional(timeout=30) //默认是30秒
		
		事务隔离级别:
		@Transactional(isolation = Isolation.READ_UNCOMMITTED)
		读取未提交数据(会出现脏读, 不可重复读) 基本不使用
		@Transactional(isolation = Isolation.READ_COMMITTED)
		读取已提交数据(会出现不可重复读和幻读)
		@Transactional(isolation = Isolation.REPEATABLE_READ)
		可重复读(会出现幻读)
		@Transactional(isolation = Isolation.SERIALIZABLE)
		串行化
	 */
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class,isolation=Isolation.READ_COMMITTED)
	public ServiceRsObjModel addUnpay(Unpay unpay) {
		ServiceRsObjModel result=new ServiceRsObjModel();
		try {
			Integer add=unpayMapper.addUnpay(unpay);
			if(add>0) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		}catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServiceRsObjModel delUnpay(Unpay unpay) {
		ServiceRsObjModel result=new ServiceRsObjModel();
		try {
			Integer del=unpayMapper.delUnpay(unpay);
			if(del>0) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		}catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServiceRsObjModel updateUnpay(Unpay unpay) {
		ServiceRsObjModel result=new ServiceRsObjModel();
		try {
			Integer update=unpayMapper.updateUnpay(unpay);
			if(update>0) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		}catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServiceRsObjModel<List<Unpay>> queryUnpay(Unpay unpay) {
		ServiceRsObjModel result=new ServiceRsObjModel();
		try {
			List<Unpay> listUnpay=unpayMapper.queryUnpay(unpay);
			result.setRsData(listUnpay);
		}catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	@Override
	public ServiceRsObjModel<PageView<Unpay>> queryUnpayPage(PageView<Unpay> pageView,Unpay unpay) {
		ServiceRsObjModel<PageView<Unpay>> result=new ServiceRsObjModel<PageView<Unpay>>();
		try {
			List<Unpay> listUnpayPage=unpayMapper.queryUnpayPage(unpay);
			pageView.setResult(listUnpayPage);
			result.setRsData(pageView);
		}catch(Exception e) {
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}
		return result;
	}

}
