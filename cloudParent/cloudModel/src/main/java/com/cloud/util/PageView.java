package com.cloud.util;

import java.util.List;

public class PageView<T> {
	
	public List<T> result;
	
	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int currentPage=1;//当前页
	
	public int pageSize=10;//页面显示数据条数
	
	public Long totalCount;//总个数
	
	public int totalPage;//总页数

	public int getTotalPage() {
		return (int) (this.totalCount%this.pageSize==0? this.totalCount/this.pageSize:(this.totalCount/this.pageSize)+1);
	}

	public int start;
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
	
}
