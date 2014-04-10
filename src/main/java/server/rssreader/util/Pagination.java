package server.rssreader.util;

import java.util.List;

@SuppressWarnings("rawtypes")
public class Pagination {
	private int pageSize = 30;
	private int pageIndex;
	private int totalRecords;
	private List datas;
	
	public static Pagination newInstance() {
		return new Pagination();
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	public int getTotalPages() {
		int num = this.totalRecords/this.pageSize;
		return this.totalRecords % this.pageSize == 0? num : num+1;
	}
	public int getPrevIndex() {
		return this.pageIndex > 1? (this.pageIndex-1) : 1;
	}
	public int getNextIndex() {
		int totalPages = this.getTotalPages();
		return this.pageIndex < totalPages? (this.pageIndex+1) : totalPages;
	}
	
	
}
