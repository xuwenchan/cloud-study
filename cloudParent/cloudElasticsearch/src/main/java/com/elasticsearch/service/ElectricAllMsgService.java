package com.elasticsearch.service;

import java.util.List;

import com.elasticsearch.model.electricAllMsg;

public interface ElectricAllMsgService {

	public List<electricAllMsg> queryTodayAlarmCouont();
	
	public void searchQueryDemo();
}
