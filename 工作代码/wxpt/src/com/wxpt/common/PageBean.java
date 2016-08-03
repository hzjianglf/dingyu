package com.wxpt.common;

import java.util.List;

public class PageBean
{
	// 从数据库查询得来的数据
	private List list;    // 当前页面的数据
	private int recordCnt;// 总的记录数

	// 自己计算的数据
	private int maxSize;  // 每页显示的最大记录数
	// 总页数
	private int pageCnt;
	// 首页
	private int firstPage;
	// 上一页
	private int prePage;
	// 下一页
	private int nextPage;
	// 尾页
	private int lastPage;
	// 当前页
	private int curPage;
	
	
	public PageBean() {
		super();
	}

	/**
	 * 初始化页面数据
	 */
	public PageBean(List list, int recordCnt, int maxSize, int curPage)
	{
		this.list = list;
		this.recordCnt = recordCnt;
		this.maxSize = maxSize;
		this.curPage = curPage;
		
		// 计算总页数
		
		this.pageCnt = (this.recordCnt / this.maxSize)
				
				+ (this.recordCnt % this.maxSize == 0 ? 0 : 1);
		
		this.firstPage = 1;

		this.lastPage = this.pageCnt;
		
		update(curPage);
	}

	public void update(int curPage)
	{

		if (curPage <= 1)
		{// 当前页是首页的时候，上一页为当前页
			this.prePage = curPage;
		} else
		{
			this.prePage = curPage - 1;
		}
		
		if (curPage >= this.pageCnt)
		{
			//当前页是尾页的时候，下一页为尾页
			
			this.nextPage = this.curPage;
		}else	
		{
			this.nextPage = curPage + 1;
		}

	}

	public List getList()
	{
		return list;
	}

	public void setList(List list)
	{
		this.list = list;
	}

	public int getRecordCnt()
	{
		return recordCnt;
	}

	public void setRecordCnt(int recordCnt)
	{
		this.recordCnt = recordCnt;
	}

	public int getMaxSize()
	{
		return maxSize;
	}

	public void setMaxSize(int maxSize)
	{
		this.maxSize = maxSize;
	}

	public int getPageCnt()
	{
		return pageCnt;
	}

	public void setPageCnt(int pageCnt)
	{
		this.pageCnt = pageCnt;
	}

	public int getFirstPage()
	{
		return firstPage;
	}

	public void setFirstPage(int firstPage)
	{
		this.firstPage = firstPage;
	}

	public int getPrePage()
	{
		return prePage;
	}

	public void setPrePage(int prePage)
	{
		this.prePage = prePage;
	}

	public int getNextPage()
	{
		return nextPage;
	}

	public void setNextPage(int nextPage)
	{
		this.nextPage = nextPage;
	}

	public int getLastPage()
	{
		return lastPage;
	}

	public void setLastPage(int lastPage)
	{
		this.lastPage = lastPage;
	}

	public int getCurPage()
	{
		return curPage;
	}

	public void setCurPage(int curPage)
	{
		this.curPage = curPage;
	}

}
