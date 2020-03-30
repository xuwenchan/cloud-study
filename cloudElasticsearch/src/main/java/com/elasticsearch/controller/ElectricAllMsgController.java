package com.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elasticsearch.service.ElectricAllMsgService;

@RestController
@RequestMapping("electric")
public class ElectricAllMsgController {

	@Autowired
	ElectricAllMsgService electricAllMsgService;
	
	@RequestMapping("today")
	public void queryTodayAlarm() {
		electricAllMsgService.queryTodayAlarmCouont();
	}
	
	@RequestMapping("alarmCount")
	public void alarmCount() {
		electricAllMsgService.searchQueryDemo();
	}
}
