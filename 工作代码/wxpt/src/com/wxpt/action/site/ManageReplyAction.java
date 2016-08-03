package com.wxpt.action.site;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.site.dao.ParentDao;
import com.wxpt.site.dao.ReplyDao;
import com.wxpt.site.entity.Keywords;
import com.wxpt.site.entity.Reply;
import com.wxpt.site.entity.pojo.ReplyPojo;
import com.wxpt.site.service.ReplyService;
import com.wxpt.site.service.UserService;



@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({ @Result(name = "reply", type = "json", params = { "root", "result" }) })
public class ManageReplyAction extends ParentAction {
	private String replyContent;
	@Autowired
	UserService userService;
	@Autowired
	ReplyService replyService;
	ParentDao parentDao;

	private int keywordID;
	GetCookie cookies= new GetCookie();
	int enterId=cookies.getCookie();
	
	public String add() {
		/*Reply reply = new Reply();
		
		
		reply.setReplyContent(replyContent);
		reply.setReplyType(0);

		replyService.add(reply);*/
		return "reply";
	}

	JSONArray jsonls;
	int count;// 总记录数
	int page;
	int rows;

	public int getKeywordID() {
		return keywordID;
	}

	public void setKeywordID(int keywordID) {
		this.keywordID = keywordID;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	List<Reply> indList;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		indList = replyService.getReplyList(enterId,page, rows);
		List<ReplyPojo> pojoList = new ArrayList<ReplyPojo>();
		for (Reply reply : indList) {
			String replyType = "";
			if (reply.getReplyType() == 0) {
				replyType = "关注回复";
			} else {
				replyType = "默认回复";
			}
			
			
			try {
				String sql="select keywordID from wxpt"+enterId+".reply where reply_id = "+reply.getReplyId();
				int id=replyService.getReplyKwordId(sql);
				String sql2="SELECT keywordcontent FROM wxpt"+enterId+".keywords WHERE `keywordID`="+ id;
				String keywordcontend=replyService.getKeywordcontent(sql2);
				ReplyPojo replyPojo = new ReplyPojo(reply.getReplyId(),keywordcontend,
						replyType);
				System.out.println(reply.getKeywords().getKeywordId());
				replyPojo.setKeyId(reply.getKeywords().getKeywordId());
				pojoList.add(replyPojo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ReplyPojo replyPojo = new ReplyPojo(reply.getReplyId(),"暂未添加，点击右上角添加修改" ,
						replyType);
				
				replyPojo.setKeyId(0);
				pojoList.add(replyPojo);
			}
			
//			replyPojo.setKeyId(reply.getKeywords().getKeywordId());
//			pojoList.add(replyPojo);
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "user" });
			jsonls = JSONArray.fromObject(pojoList);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count = replyService.getReplyCount(enterId);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "reply";
	}

	private int replyId;
	private String keyName;
	
	
	
	public String update() {
		Keywords keywords = userService.getKeyBysendContent(enterId,keyName);
		Reply reply = replyService.getReplyByID(enterId,replyId);
		reply.setKeywords(keywords);
		parentDao.update(enterId,reply);
		return "reply";
	}
	public void clearReply(){
		System.out.println(replyId);
		Reply reply = replyService.getReplyByID(enterId,replyId);
		String sql="UPDATE wxpt"+enterId+".reply SET `reply_type`="+reply.getReplyType()+",`keywordID`=null WHERE `reply_id`="+replyId;
		try {
			parentDao.clearReply(sql,enterId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	

	public String delete() {
		String[] ids = replyContent.split(",");
		for (int i = 0; i < ids.length; i++) {

			replyService.deleteById(Integer.parseInt(ids[i]));

		}
		return "reply";
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public ParentDao getParentDao() {
		return parentDao;
	}

	public void setParentDao(ParentDao parentDao) {
		this.parentDao = parentDao;
	}

}
