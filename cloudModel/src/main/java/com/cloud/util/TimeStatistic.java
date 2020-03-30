package com.cloud.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class TimeStatistic {
	public TimeStatistic(Date start,Date end,String time,int step) {
		this.start=start;
		this.end=end;
		this.time=time;
		this.step=step;
	}
	public static Map<String,Object> timeMap;
	static {
		timeMap=new HashMap<String,Object>();
		timeMap.put("day", new Times(Calendar.DATE,new Long(1000*3600*24)));
		timeMap.put("hour", new Times(Calendar.HOUR_OF_DAY,new Long(1000*60*60)));
		timeMap.put("month", new Times(Calendar.MONTH,null));
	}
	public Date start;
	public Date end;
	private String time;//以多长时间间隔进行一次统计
	private int step;//步长
	private String description;
	private Float count;
	
	public TimeStatistic() {
		
	}
	
	public TimeStatistic(String time,int step) {
		this.time=time;
		this.step=step;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static Map<String, Object> getTimeMap() {
		return timeMap;
	}

	public static void setTimeMap(Map<String, Object> timeMap) {
		TimeStatistic.timeMap = timeMap;
	}

	public Float getCount() {
		return count;
	}

	public void setCount(Float count) {
		this.count = count;
	}

	
	
	
	

}
