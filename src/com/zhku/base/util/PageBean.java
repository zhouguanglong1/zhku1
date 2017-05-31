package com.zhku.base.util;

import java.util.List;
/**
 * 封装分页的工具Bean
 * @author HuMeng
 */
public class PageBean {
	
	private List recordList; // 本页的数据列表
	private int totalCount; // 总记录数
	/**
	 * 当前页
	 */
	private String currentPage;
	/**
	 * 单页数
	 */
	private String pageSize;
	public PageBean(){}
	/**
	 * @param recordList 查询对象记录列表
	 * @param totalCount 本次查询记录总数
	 */
	public PageBean(List recordList, int totalCount) {
		super();
		this.recordList = recordList;
		this.totalCount = totalCount;
	}

	

	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public List getRecordList() {
		return recordList;
	}
	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	
	
}
