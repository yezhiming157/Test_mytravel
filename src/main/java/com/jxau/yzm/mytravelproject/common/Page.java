package com.jxau.yzm.mytravelproject.common;

import java.util.List;


/**
 * 分页
 * @author yezhiming
 *2019年8月18日
 *@version
 */
public class Page<T> {

	// 当前页面
	private int currentPage;
	// 每页显示记录数
	private int pageSize;
	// 总记录数
	private int totalSize;
	// 总页面数
	private int totalPage;
	// 页面数据
	private List<T> datas;

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

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalPage() {
		totalPage = (totalSize % pageSize) == 0 ? (totalSize / pageSize)
				: (totalSize / pageSize + 1);
		if(totalPage == 0){
			totalPage = 1;
		}
		return totalPage;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "Page{" +
				"currentPage=" + currentPage +
				", pageSize=" + pageSize +
				", totalSize=" + totalSize +
				", totalPage=" + totalPage +
				", datas=" + datas +
				'}';
	}
}
