package com.wxpt.action.site;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.site.entity.QuestionRule;
import com.wxpt.site.service.QuestionService;
@Results({
	@Result(name="success" ,location="/WEB-INF/content/site/successs.jsp")
	
})
@SuppressWarnings("serial")
public class ManageQuestionRuleAction extends ParentAction {
	@Autowired
	QuestionService questionService;
	JSONArray jsonArrayFromList;
	private String questionNum;
	private String system;
	private String prize;
	private String ruleId;
	private String fangqi;
	private int page=1;
	private int pageSize=10;
	private int count;
	int state=0;
	List<QuestionRule> ruleList=new ArrayList<QuestionRule>();
	public void addRule() {
		String sql = "INSERT INTO wxpt"
				+ super.getCookieByEnterID()
				+ ".question_rule( `question_num`, `question_tishi`, `awards`,`fangqi`) "
				+ "VALUES (" + questionNum + ",'" + system + "','" + prize +"','"+ fangqi +"')";
		try {
			questionService.addRule(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//查询规则
	@SuppressWarnings("unused")
	public void selectRule(){
		String sql="SELECT * FROM wxpt"+super.getCookieByEnterID()+".question_rule";
		try {
			ruleList=questionService.getAllRule(sql, page, pageSize);
			count=ruleList.size();
			JsonConfig jsonConfig = new JsonConfig();
			jsonArrayFromList = JSONArray.fromObject(ruleList);
			super.out.print("{\"total\":" + count + ",\"rows\":"
					+ jsonArrayFromList + "}");
			
			super.out.flush();
			super.out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//修改规则
	public void updateRule(){
		String sql="UPDATE wxpt"+super.getCookieByEnterID()+".question_rule SET `question_num`="+questionNum+",`question_tishi`='"+system+"',`awards`='"+prize+"',`fangqi`='"+fangqi+"' WHERE `question_rule_id`="+ruleId+"";
		try {
			questionService.updateRule(sql,super.getCookieByEnterID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//判断
	public String  panduan(){
		ruleList=questionService.getRuleList(super.getCookieByEnterID());
		if(ruleList.size()==3){
			state=1;
		}
		return "success";
	}
	public String getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(String questionNum) {
		this.questionNum = questionNum;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getFangqi() {
		return fangqi;
	}
	public void setFangqi(String fangqi) {
		this.fangqi = fangqi;
	}


}
