package com.cloud.util;

public class ServiceRsObjModel<T> extends ServiceResult  {

	public T RsData;

	public T getRsData() {
		return RsData;
	}

	public void setRsData(T rsData) {
		RsData = rsData;
	}
	
}
