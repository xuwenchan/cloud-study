package com.userModule.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cloud.Model.Unpay;

@Repository
public interface UnpayMapper {

	Integer addUnpay(Unpay unpay);

	Integer delUnpay(Unpay unpay);

	Integer updateUnpay(Unpay unpay);

	List<Unpay> queryUnpay(Unpay unpay);

	List<Unpay> queryUnpayPage(Unpay unpay);

}
