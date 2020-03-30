package com.cloud.util;
public class Times{
	private int id;
	private Long len;
	Times(int id,Long len) {
		this.id=id;
		this.len=len;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getLen() {
		return len;
	}
	public void setLen(Long len) {
		this.len = len;
	}
}
