package com.wxpt.action.site;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.FileUploadBean;
import com.wxpt.site.entity.Keywordexplicit;
import com.wxpt.site.entity.Keywords;
import com.wxpt.site.entity.pojo.KeyListPojo;
import com.wxpt.site.service.QuestionService;
import com.wxpt.site.service.UserService;
@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
		@Result(name = "keyword", type = "json", params = { "root", "result" }),
		@Result(name = "success", location = "success.jsp") })
public class ManageKeyAction extends ParentAction{
	public List<KeyListPojo> keyListPojo ;
	@Autowired
	UserService userService;
	JSONArray jsonls;
	int count;// 总记录数
	int rows;
	int page;
	private String idsKeys;
	private List<Keywordexplicit> keywordexplicits;
	@Autowired
	QuestionService questionService;
	GetCookie cookies=new GetCookie();
	int enterId=cookies.getCookie();
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println();
		keywordexplicits = userService.getKeyList(enterId,page,rows);
		keyListPojo = new ArrayList<KeyListPojo>();
		for (Keywordexplicit k : keywordexplicits) {
			int explicitId=k.getExplicitId();
			/*userService.getkExplicitBy(enterId,explicitId);*/
			/*System.out.println(k.getTitle());*/
			int keyWordId=userService.getKeyWordId(enterId,explicitId);
			KeyListPojo p = new KeyListPojo();
			Keywords keyWord=userService.getByID(enterId,keyWordId);
			p.setKeyName(keyWord.getKeywordcontent());
			if(k.getEkey().equals("images")){
				
				p.setEcontent("<img  src=\"../web/images/"+enterId+"/"
						+ k.getEcontent()
						+ "\" style=\"width:100%;heigh:100%;\"/>");
				p.setEkey("图文");
			}
			if(k.getEkey().equals("vidio")){
				p.setEcontent("<embed src=\"../web/images/"+enterId+"/"
								+ k.getEcontent()
								+ "\" style=\"width:100px; height:60px;\" autostart=false loop=\"false\" />");
				p.setEkey("音频");
			}
			if(k.getEkey().equals("word")){
				p.setEcontent(k.getEcontent());
				p.setEkey("文本");
			}
			p.setKeyId(k.getKeywords().getKeywordId());
			p.setAddTime(k.getAddTime());
			p.setEinstruction(k.getEinstruction());
			p.setTitle(k.getTitle());
			if(k.getUrl()!=null &&k.getUrl().equals("")==false){
				p.setUrl("<a href=\""+k.getUrl()+"\"  target=\"_blank\">点击查看"+k.getUrl()+"</a>");
			}
			
			p.setExplicitId(k.getExplicitId());
			keyListPojo.add(p);
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "user" });
			jsonls = JSONArray.fromObject(keyListPojo);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count = questionService.getCount("select * from wxpt"+enterId+".keywordexplicit");
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "keyword";
	}
	private int explicitId;
	private String explicitIds;
	private FileUploadBean bean = new FileUploadBean();
	//删除关键字内容
	public String deletKeywordexplicit(){
		try {
			String ids[] = explicitIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				Keywordexplicit keywordexplicit = userService
						.getkExplicitBy(enterId,Integer.parseInt(ids[i]));
				if (keywordexplicit != null) {
					if (!keywordexplicit.getEcontent().equals("word")) {
						bean.deletefile(enterId+"/"
								+ keywordexplicit.getEcontent());
					}
				}
				userService.deleteSql(enterId,ids[i]);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "keyword";
	}
	
	//删除关键字
	public void deletKeywords(){
		idsKeys=idsKeys.substring(0, idsKeys.lastIndexOf(","));
			String value="";
			 //执行删除操作
			 try {
		       String[] ids2 = idsKeys.split(",");
		       
		       for(int i=0;i<ids2.length;i++){	    	   
		    	 keywordexplicits = userService.getkExplicitByKeyID(Integer.parseInt(ids2[i]), enterId);
		    	 if(keywordexplicits.size()>0){
		    		 value="2";
		    		 return ;
		    	 }else{
		    		 //  执行删除
		    		 userService.deleteKeywords(enterId, Integer.parseInt(ids2[i]));
		    		 value="1";
		    	 }
		       }
		      
				 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					//return ERROR;
					value="0";
				}finally{
					try{
						ServletActionContext.getResponse().setCharacterEncoding("utf-8");
						PrintWriter out = ServletActionContext.getResponse().getWriter();
						out.print(value);
					}catch(Exception e2){	
					}	
				}
				
		
	}
	
	
	
	public String getIdsKeys() {
		return idsKeys;
	}

	public void setIdsKeys(String idsKeys) {
		this.idsKeys = idsKeys;
	}

	public String getExplicitIds() {
		return explicitIds;
	}

	public void setExplicitIds(String explicitIds) {
		this.explicitIds = explicitIds;
	}
	private List<Keywords> keywordsies;
	public String key(){
		
		try {
			keywordsies = userService.getBykeyList(enterId,page,rows);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "keywordexplicits","replies" });
			jsonls = JSONArray.fromObject(keywordsies,jsonConfig);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*count = questionService.getCount("select * from wxpt"+enterId+".keywords");*/
		count = questionService.getKeyWordsCount("select * from wxpt"+enterId+".keywords");
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "keyword";
	}
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getExplicitId() {
		return explicitId;
	}

	public void setExplicitId(int explicitId) {
		this.explicitId = explicitId;
	}

	public List<KeyListPojo> getKeyListPojo() {
		return keyListPojo;
	}
	public void setKeyListPojo(List<KeyListPojo> keyListPojo) {
		this.keyListPojo = keyListPojo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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
