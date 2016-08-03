package com.wxpt.action.site;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Suroption;
import com.wxpt.site.entity.Surquestion;
import com.wxpt.site.entity.Surrecord;
import com.wxpt.site.entity.Survey;
import com.wxpt.site.service.SuroptionService;
import com.wxpt.site.service.SurquestionService;
import com.wxpt.site.service.SurrecordService;
import com.wxpt.site.service.SurveyService;
@Results({
	@Result(name="success" ,location="/WEB-INF/content/site/successs.jsp"),
 @Result(name = "success1", type = "json", params = { "root", "result" })
})
@SuppressWarnings("serial")
public class ManageSurveyAction extends ParentAction {
	@Autowired
	SurveyService surveyService;
	@Autowired
	SurquestionService surquestionService;
	@Autowired
	SurrecordService surrecordService;
	@Autowired
	SuroptionService suroptionService;
	JSONArray jsonArrayFromList;
	
	private int page=1;
	private int pageSize=10;
	private int count;
	int state=0;
	int questionTypeid=0;
	List<Survey> surveyList=new ArrayList<Survey>();
	List<Surquestion> surquestionList=new ArrayList<Surquestion>();
	List<Suroption> suroptionList=new ArrayList<Suroption>();
	List<Surrecord> surrecordList=new ArrayList<Surrecord>();
	List<Suroption>optionList;
	//关于题目操作
	private String questionNum;
	private String system;
	private String surquestionId;
	private String questionType;
	private String surveyId;
	//添加题目
	public void addSurquestion() {
		if(questionType.equals("单选")){
			 questionTypeid=1;
		}else if(questionType.equals("多选")){
			 questionTypeid=0;
		}
		String sql = "INSERT INTO wxpt"+super.getCookieByEnterID()+".surquestion(surquestion_number, surquestion_content, surquestion_type,surquestion_addtime) VALUES ('"+questionNum+"','"+system+"',"+questionTypeid+",'"+TimeUtil.getTime()+"')";
		try {
			surquestionService.addSurquestion(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//查询用户
	public void querySurvey(){
		String sql="SELECT * FROM wxpt"+super.getCookieByEnterID()+".survey";
		try {
			surveyList=surveyService.getAllSurvey(sql, page, pageSize);
			String sql2="SELECT * FROM wxpt"+super.getCookieByEnterID()+".survey";
			count=surveyService.getAllSurveyList(sql2).size();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "surrecords" });
			JSONArray	jsonArrayFromList = JSONArray.fromObject(surveyList,jsonConfig);
			String str =jsonArrayFromList.toString().replaceAll("\"surveyUerSex\":1,", "\"surveyUerSex\":\"男\",");	
			str = str.replaceAll("\"surveyUerSex\":0,", "\"surveyUerSex\":\"女\",");
			str = str.replaceAll("\"surveyUserAge\":0,", "\"surveyUserAge\":\"18岁以下\",");
			str = str.replaceAll("\"surveyUserAge\":1,", "\"surveyUserAge\":\"18到25岁\",");
			str = str.replaceAll("\"surveyUserAge\":2,", "\"surveyUserAge\":\"26-35岁\",");
			str = str.replaceAll("\"surveyUserAge\":3,", "\"surveyUserAge\":\"36到45岁\",");
			str = str.replaceAll("\"surveyUserAge\":4,", "\"surveyUserAge\":\"45岁以上\",");
			
			//System.out.println(str.indexOf(",\"productState\":2"));
			super.out.print("{\"total\":" + count + ",\"rows\":"
					+ str.toString() + "}");
			System.out.println(str);
			String sql3="SELECT * FROM wxpt"+super.getCookieByEnterID()+".suroption ";
			optionList=suroptionService.getAllSuroptionList(sql3);
			System.out.println(optionList.size());
			
			super.out.flush();
			super.out.close();
			
			/*//为生成答卷做准备
			QuestionPojo  question = new QuestionPojo();
			Surquestion surquestion=new Surquestion();		
			surquestionList=surquestionService.findAll(super.getCookieByEnterID());
			System.out.println(surquestionList);
			String sql3="SELECT * FROM wxpt"+super.getCookieByEnterID()+".suroption ";
			optionList=suroptionService.getAllSuroptionList(sql3);
			System.out.println(optionList.size());*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//查询记录表
	public   String 	query(){
		 String quetype="";
		String sql="SELECT * FROM wxpt"+super.getCookieByEnterID()+".surquestion   order by surquestion_id";
		//根据用户id查询所有问卷试题
		surquestionList=surquestionService.getAllSurquestion(sql);
		StringBuffer sb = new StringBuffer();
		try {
			JsonConfig jsonConfig = new JsonConfig();		
		   jsonConfig.setExcludes(new String[] { "suroptions", "surrecords" });
			jsonArrayFromList = JSONArray.fromObject(surquestionList,jsonConfig);
				String str =jsonArrayFromList.toString();
			if(surquestionList.size()>0){
				for(int i=0;i<surquestionList.size();i++){
				/*	Suroption suroption=new Suroption();
					Surquestion surquestion= new Surquestion();
					String sql2="SELECT * FROM wxpt"+super.getCookieByEnterID()+".suroption WHERE option_id="+surrecordList.get(i).getSuroption().getOptionId();
					suroption=suroptionService.getSuroption(sql2);
					String sql3="SELECT * FROM wxpt"+super.getCookieByEnterID()+".surquestion WHERE surquestion_id="+surrecordList.get(i).getSurquestion().getSurquestionId();
					surquestion=surquestionService.getAllSurquestion(sql3).get(0);*/
					Surrecord surrecord =new Surrecord();
					String sql2="SELECT * FROM wxpt"+super.getCookieByEnterID()+".surrecord  WHERE survey_id="+surveyId +"  and surquestion_id="+surquestionList.get(i).getSurquestionId();
					System.out.println(sql2);
					surrecordList=surrecordService.getAllSurrecord(sql2);
					String suroptionContent ="";//
				if(surrecordList.size()>0){
					for(int j=0;j<surrecordList.size();j++){
						String sql1="SELECT * FROM wxpt"+super.getCookieByEnterID()+".suroption WHERE option_id="+surrecordList.get(j).getSuroption().getOptionId();
						System.out.println(sql1);
						Suroption suroption=new Suroption();//选项对象
						suroption=suroptionService.getAllSuroptionList(sql1).get(0);
						suroptionContent+=" "+suroption.getOptionNumber()+":"+suroption.getOptionContent();
						System.out.println(suroptionContent);
						
					}
				}else{
					suroptionContent="  该题目未选择选项答案";
				}
					
					String temp = "";
					temp =str.substring(0,str.indexOf("}"));
					sb.append(temp);	
					// sb.append(",\"suroptionNum\":\""+suroption.getOptionNumber()+"\"");
					 sb.append(",\"suroptionContent\":\""+suroptionContent+"\"");
					 sb.append(",\"surquestionNum\":\""+surquestionList.get(i).getSurquestionNumber()+"\"");
					 sb.append(",\"surquestionContent\":\""+surquestionList.get(i).getSurquestionContent()+"\"");
					 if(surquestionList.get(i).getSurquestionType()==1){
						 quetype="单选";
					 }else{
						 quetype="多选";
					 }
					 sb.append(",\"questionsate\":\""+quetype+"\"");
					 sb.append("}");
					str =str.substring(str.indexOf("}")+1);
				}
				 sb.append("]");
				  System.out.println(sb.toString());
					super.out.print(sb.toString());
					super.out.flush();
					super.out.close();
			}else{
				  System.out.println(jsonArrayFromList);
					super.out.print(jsonArrayFromList);
					super.out.flush();
					super.out.close();
			}
		
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return "success1";
	}
private String surveyIds;
	
	////批量删除 调研用户
	public void deleteSurvey(){
		 try {
			 surveyIds=surveyIds.substring(0, surveyIds.lastIndexOf(","));
		 //执行删除操作
		 System.out.println(surveyIds); 
		 String sql="delete from wxpt"+super.getCookieByEnterID()+".survey  where survey_id in (" + surveyIds+")";
		String sql2="delete from wxpt"+super.getCookieByEnterID()+".surrecord  where survey_id in (" + surveyIds+")";
		 //执行删除方法
		 surveyService.deletedeleteSurvey(sql, super.getCookieByEnterID());
		surrecordService.deleteSurrecord(sql2,super.getCookieByEnterID());
				HttpServletResponse hs=ServletActionContext.getResponse();
	    		  hs.setContentType(
	 				     "text/html;charset=utf-8");
	    		 PrintWriter out = hs.getWriter();
	    		result=1;
				out.print(result);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				out.print(result);
			}
		   out.print(result);
		  
	}
	
	
	//查询题目设置
	public void querySurquestion(){
		String sql="SELECT * FROM wxpt"+super.getCookieByEnterID()+".surquestion ";
		String sql2="SELECT * FROM wxpt"+super.getCookieByEnterID()+".surquestion ";
				
		try {
			surquestionList=surquestionService.getAllSurquestion(sql, page, pageSize);
			count=surquestionService.getAllSurquestion(sql2).size();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] {"suroptions","surrecords"});
			jsonArrayFromList = JSONArray.fromObject(surquestionList,jsonConfig);
			String str =jsonArrayFromList.toString().replaceAll("\"surquestionType\":1,", "\"surquestionType\":\"单选\",");	
			str = str.replaceAll("\"surquestionType\":0,", "\"surquestionType\":\"多选\",");
			//System.out.println(str.indexOf(",\"productState\":2"));
			super.out.print("{\"total\":" + count + ",\"rows\":"
					+ str.toString() + "}");
			System.out.println(str.toString());
			
			super.out.flush();
			super.out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//修改题目
	public void updatateSurquestion(){
		String sql="UPDATE wxpt"+super.getCookieByEnterID()+".surquestion SET surquestion_number='"+questionNum+"',`surquestion_content`='"+system+"',`surquestion_type`="+questionTypeid+",surquestion_updatetime='"+TimeUtil.getTime()+"' WHERE surquestion_id="+surquestionId;
		try {
			surquestionService.updateSurquestion(sql, super.getCookieByEnterID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//判断
	public String  panduan(){
	//	surveyList=surveyService.getRuleList(super.getCookieByEnterID());
		if(surveyList.size()==3){
			state=1;
		}
		return "success";
	}
	
	private String ids;
	int result=0; 
//批量删除
public void deleteSurquestion(){
	 try {
	 ids=ids.substring(0, ids.lastIndexOf(","));
	 //执行删除操作
	 System.out.println(ids); 
	 String sql="delete from wxpt"+super.getCookieByEnterID()+".surquestion  where surquestion_id in (" + ids+")";
	 //执行删除方法
	   surquestionService.deleteSurquestion(sql, super.getCookieByEnterID());
			HttpServletResponse hs=ServletActionContext.getResponse();
    		  hs.setContentType(
 				     "text/html;charset=utf-8");
    		 PrintWriter out = hs.getWriter();
    		result=1;
			out.print(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.print(result);
		}
	   out.print(result);
	  
}
	
private String idOptions;
//批量删除
public void deleteSuroption(){
	 try {
		 idOptions=idOptions.substring(0, idOptions.lastIndexOf(","));
	 //执行删除操作
	 System.out.println(ids); 
	 String sql="delete from wxpt"+super.getCookieByEnterID()+".suroption  where option_id in (" + idOptions +")";
	 //执行删除方法
	 suroptionService.deleteSurquestion(sql, super.getCookieByEnterID());
			HttpServletResponse hs=ServletActionContext.getResponse();
  		  hs.setContentType(
				     "text/html;charset=utf-8");
  		 PrintWriter out = hs.getWriter();
  		result=1;
			out.print(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.print(result);
		}
	   out.print(result);
	  
}
	
	
	public String getIdOptions() {
	return idOptions;
}
public void setIdOptions(String idOptions) {
	this.idOptions = idOptions;
}
		
		//关于选项操作
		private String questionSurNumber;
		private String questionSurContent;
		private String optionNumber;
		private String optionContent;
		private String optionCode;
		private String optionId;
		private String surQuestion;

		//查询选项设置
		public void querySuroption(){
			String sql="SELECT * FROM wxpt"+super.getCookieByEnterID()+".suroption order by surquestion_id";
			System.out.println(sql);
			try {
				suroptionList=suroptionService.getAllSuroption(sql, page, pageSize);
				String sql2="SELECT * FROM wxpt"+super.getCookieByEnterID()+".suroption ";
				count=suroptionService.getAllSuroptionList(sql2).size();
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setExcludes(new String[] { "surrecords" , "surquestion" });
				jsonArrayFromList = JSONArray.fromObject(suroptionList,jsonConfig);
				String str=jsonArrayFromList.toString();
				StringBuffer sb = new StringBuffer();
				//循环遍历获取角色和部门
				for(int i=0;i<suroptionList.size();i++){
					String temp = "";		
					int questionId =suroptionList.get(i).getSurquestion().getSurquestionId();	
					System.out.println(questionId);
				   sql="SELECT * FROM wxpt"+super.getCookieByEnterID()+".surquestion where surquestion_id="+questionId;
					Surquestion S=(Surquestion) surquestionService.getAllSurquestion(sql).get(0);
					temp =str.substring(0,str.indexOf("}"));				
					sb.append(temp);			
					sb.append(",\"questionNumber\":\""+S.getSurquestionNumber()+"\"");
					sb.append(",\"questionContent\":\""+S.getSurquestionContent()+"\"");
					sb.append("}");
					str =str.substring(str.indexOf("}")+1);
				}
				sb.append("]");
				super.out.print("{\"total\":" + count + ",\"rows\":"
						+ sb.toString() + "}");
				System.out.println(sb);		
				super.out.flush();
				super.out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
		
		
		
			//添加选项
			public void addSuroption() {
			
				String sql = "INSERT INTO wxpt"+super.getCookieByEnterID()+".suroption( option_number, option_content, surquestion_id, option_code,option_addtime)" +
						" VALUES ('"+optionNumber+"','"+optionContent+"',"+surQuestion+",'"+optionCode+"','"+TimeUtil.getTime()+"')";
				try {
					surquestionService.addSurquestion(sql);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		//修改选项
			
			public void updateSuroption() {
				String sql1="SELECT * FROM wxpt"+super.getCookieByEnterID()+".suroption WHERE option_id="+optionId;
			//根据选项id查询查询原选项
				System.out.println(sql1);
				Suroption suroption=suroptionService.getSuroption(sql1);
				String sql = "UPDATE wxpt"+super.getCookieByEnterID()+".suroption SET option_number='"+optionNumber+"',`option_content`='"+optionContent+"',`surquestion_id`="+suroption.getSurquestion().getSurquestionId()+",`option_code`='"+optionCode+"',option_updatetime='"+TimeUtil.getTime()+"' WHERE option_id="+optionId;
				try {
					suroptionService.updateSurSuroption(sql, super.getCookieByEnterID());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			
		

public List<Surrecord> getSurrecordList() {
				return surrecordList;
			}
			public void setSurrecordList(List<Surrecord> surrecordList) {
				this.surrecordList = surrecordList;
			}
public String getSurveyId() {
				return surveyId;
			}
			public void setSurveyId(String surveyId) {
				this.surveyId = surveyId;
			}
public List<Suroption> getOptionList() {
				return optionList;
			}
			public void setOptionList(List<Suroption> optionList) {
				this.optionList = optionList;
			}
public String getSurveyIds() {
		return surveyIds;
	}
	public void setSurveyIds(String surveyIds) {
		this.surveyIds = surveyIds;
	}
public String getSurQuestion() {
		return surQuestion;
	}
	public void setSurQuestion(String surQuestion) {
		this.surQuestion = surQuestion;
	}
public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
 public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
 public String getQuestionSurNumber() {
		return questionSurNumber;
	}
	public void setQuestionSurNumber(String questionSurNumber) {
		this.questionSurNumber = questionSurNumber;
	}
	public String getQuestionSurContent() {
		return questionSurContent;
	}
	public void setQuestionSurContent(String questionSurContent) {
		this.questionSurContent = questionSurContent;
	}
	public String getOptionNumber() {
		return optionNumber;
	}
	public void setOptionNumber(String optionNumber) {
		this.optionNumber = optionNumber;
	}
	public String getOptionContent() {
		return optionContent;
	}
	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}
	public String getOptionCode() {
		return optionCode;
	}
	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}
 public List<Surquestion> getSurquestionList() {
		return surquestionList;
	}
	public void setSurquestionList(List<Surquestion> surquestionList) {
		this.surquestionList = surquestionList;
	}
	public List<Suroption> getSuroptionList() {
		return suroptionList;
	}
	public void setSuroptionList(List<Suroption> suroptionList) {
		this.suroptionList = suroptionList;
	}
	public List<Survey> getSurveyList() {
		return surveyList;
	}
	public void setSurveyList(List<Survey> surveyList) {
		this.surveyList = surveyList;
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

	
	public String getSurquestionId() {
		return surquestionId;
	}
	public void setSurquestionId(String surquestionId) {
		this.surquestionId = surquestionId;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
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
	

}
