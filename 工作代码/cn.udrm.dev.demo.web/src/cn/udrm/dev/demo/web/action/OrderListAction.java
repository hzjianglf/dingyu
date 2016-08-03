package cn.udrm.dev.demo.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.udrm.dev.demo.manager.OrderManager;

public class OrderListAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(OrderListAction.class);
	
	@Autowired
	private OrderManager orderManager;
	
	//查询条件
	private String queryString;
	
	//上一次的搜索条件，以便在结果中搜。不包含where字符串，不包含order by。
	protected boolean searchInResult = false;
	protected String lastWhereClause = null;
	
	//翻页条件
	protected int limit = 20; //这里是pagesize的初始值，实际上是在客户端中定义并提交上来的
	protected int start = 0;
	
	//排序条件
	private String sort;
	private String dir;
	
	@Override
	public String execute() {
		int pageSize = limit;
		int pageNo = start/limit + 1;
		
		boolean isAsc = true;
		if (dir != null && dir.equalsIgnoreCase("DESC")){
			isAsc = false;
		}
		
		queryString = "from Order";
		try {
			String json = orderManager.searchOrders(queryString, pageNo, pageSize, sort, isAsc);
			System.out.println(json);
			return this.setJson(json);
		} catch (Exception e) {
			log.error("列表订单是发生错误！", e);
			return this.setJson(false, "列表订单是发生错误！" + e.getMessage());
		}
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
	
}
