package com.wxpt.action.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.PageBean;
import com.wxpt.site.entity.ProductType;
import com.wxpt.site.service.ProductTypeService;

@SuppressWarnings("serial")
public class ProductTypeAction extends ActionSupport {
	private int countPage = 0;// 用于在浏览装的 求总的页数
	private int rows = 10;// 每页显示的记录数
	private int total;
	private int listCount; // 总数据条数
	private int pageCount;// 总页数
	private int page=0;
	@Autowired
	ProductTypeService productTypeService;
	ProductType productType;
	JSONArray jsonArray;
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();
	public void addProductType() {
		ProductType productType = new ProductType();
		productType.setTypeName("电子产品类");
		productType.setParentId(0);
		int i = productTypeService.addProductType(productType);
	}
	
	private PageBean  pageBean=new PageBean();

	public void listProductType() throws IOException {
	
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		pageBean = productTypeService.productTypeList(enterId,page, rows);;
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		List<ProductType> list=new ArrayList<ProductType>();
		for (int i = 0; i < pageBean.getList().size(); i++) {
			list=pageBean.getList();
			productType = new ProductType();
			productType = list.get(i);
			map = new HashMap<String, Object>();
			map.put("productTypeId", productType.getProductTypeId());
			if (productType.getParentId()==0) {
				map.put("parentTypeName", "无");
			}else {
					String sql="select * from  wxpt"+enterId+".product_type where product_type_id="+productType.getParentId();
					map.put("parentTypeName", productTypeService.productTypeObject(sql).getTypeName());
					//大类别的id
					map.put("productParentTypeId", productTypeService.productTypeObject(sql).getProductTypeId());
				
			}
			map.put("typeName", productType.getTypeName());
			jsonList.add(map);
		}
		jsonArray=JSONArray.fromObject(jsonList);
		
		listCount = pageBean.getRecordCnt();
		out
		.print("{\"total\":" + listCount + ",\"rows\":" + jsonArray + "}");
		out.flush();
		out.close();
		
	}
	
//flag:标记添加和修改的
private String flag;
//标记大小类别的
private String flagType;
private String sql;
private String sql2;
private String typeName;
private JSONObject jsonObject;
private String id;
private String parentId="0";
private List<ProductType> productTypeList;


	public void productTypeObjectOperator() throws IOException{
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		Map<String, String> map=new HashMap<String, String>();
		 if (flag.equals("add")) {
				sql="insert into wxpt"+enterId+".product_type ( `parent_id`, `type_name`) values ('"+parentId+"','"+typeName+"')";
				sql2="select * from wxpt"+enterId+".product_type where type_name='"+typeName+"'";
				System.out.println(productTypeService.productTypeList(sql2).size());
				if (productTypeService.productTypeList(sql2).size()==0) {
					try { 	
						productTypeService.productTypeObjectOperator(sql);
						map.put("add", "1");
					} catch (Exception e) {
						map.put("add", "2");
					}
				}else {
					map.put("add", "3");
				}	
				
			
		}else {
			sql="update wxpt"+enterId+".product_type set type_name='"+typeName+"',parent_id='"+parentId+"' where product_type_id="+id;;
			try {
				productTypeService.productTypeObjectOperator(sql);
				map.put("add", "4");
			} catch (Exception e) {
				map.put("add", "5");
			}
		}
		jsonObject=JSONObject.fromObject(map);
		out.print(jsonObject);
		out.flush();
		out.close();
		
	}
	
	private String typeIds;
	public void DelProductType(){
	System.out.println(typeIds);
	}
	
	public String productTypeList(){
	 String sql="select * from wxpt"+enterId+".product_type where parent_id	=0";
	 productTypeList = productTypeService.productTypeList(sql);
	 return "typeajax2";
	}	
	
	public String productTypeSmall(){
		 String sql="select * from wxpt"+enterId+".product_type where parent_id	="+parentId;
		 productTypeList = productTypeService.productTypeList(sql);
		 return "typeajaxsmall";	
		
		
		
		
		
		
	}
	public List<ProductType> getProductTypeList() {
		return productTypeList;
	}

	public void setProductTypeList(List<ProductType> productTypeList) {
		this.productTypeList = productTypeList;
	}

	public String type() {
		return "manage";
	}
	
	public String getFlag() {
		return flag;
	}

	public String getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(String typeIds) {
		this.typeIds = typeIds;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
	return parentId;
}

public void setParentId(String parentId) {
	this.parentId = parentId;
}

public int getRows() {
	return rows;
}

public void setRows(int rows) {
	this.rows = rows;
}

public int getPage() {
	return page;
}

public void setPage(int page) {
	this.page = page;
}
	
	

}
