package com.wxpt.action.site;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.site.entity.QuestionTishi;
import com.wxpt.site.service.QuestionService;
@Results({
	@Result(name="success" ,location="/WEB-INF/content/site/successs.jsp")
	
})
public class ManageQuestionTishiAction extends ParentAction {
	@Autowired
	QuestionService questionService;
	JSONArray jsonArrayFromList;
	private String wei;
	private String dacuo;
	private String chaoshi;
	private String tishiId;
	private String choujiang;
	private String daguo;
	private int state;
	List<QuestionTishi> tishiList=new ArrayList<QuestionTishi>();
	//获取提示
	public void getTishi(){
		String sql="SELECT * FROM wxpt"+super.getCookieByEnterID()+".question_tishi where type=1";
		try {
			tishiList=questionService.getAllTishi(sql);
			JsonConfig jsonConfig = new JsonConfig();
			jsonArrayFromList = JSONArray.fromObject(tishiList);
			super.out.print("{\"total\":1,\"rows\":"
					+ jsonArrayFromList + "}");
			
			super.out.flush();
			super.out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//判断
	public String panduan(){
		String sql="SELECT * FROM wxpt"+super.getCookieByEnterID()+".question_tishi where type=1";
		tishiList=questionService.getAllTishi(sql);	
		state=tishiList.size();
		return "success";
	}
	//添加
	public void addTishi(){
		String sql="INSERT INTO wxpt"+super.getCookieByEnterID()+".question_tishi( `mei_jiang`, `da_cuo`, `chao_shi`, `choujiang`,`daguo`,`type`) VALUES ('"+wei+"','"+dacuo+"','"+chaoshi+"','"+choujiang+"','"+daguo+"',1)";
		try {
			questionService.addRule(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//修改
	public void updateTishi(){
		String sql="UPDATE wxpt"+super.getCookieByEnterID()+".question_tishi SET `mei_jiang`='"+wei+"',`da_cuo`='"+dacuo+"',`chao_shi`='"+chaoshi+"',`choujiang`='"+choujiang+"',`daguo`='"+daguo+"' WHERE `question_tishi_id`="+tishiId+"";
		try {
			questionService.updateRule(sql,super.getCookieByEnterID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getWei() {
		return wei;
	}
	public void setWei(String wei) {
		this.wei = wei;
	}
	public String getDacuo() {
		return dacuo;
	}
	public void setDacuo(String dacuo) {
		this.dacuo = dacuo;
	}
	public String getChaoshi() {
		return chaoshi;
	}
	public void setChaoshi(String chaoshi) {
		this.chaoshi = chaoshi;
	}
	public String getTishiId() {
		return tishiId;
	}
	public void setTishiId(String tishiId) {
		this.tishiId = tishiId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getChoujiang() {
		return choujiang;
	}
	public void setChoujiang(String choujiang) {
		this.choujiang = choujiang;
	}
	public String getDaguo() {
		return daguo;
	}
	public void setDaguo(String daguo) {
		this.daguo = daguo;
	}
}
