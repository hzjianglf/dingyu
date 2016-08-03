package com.wxpt.action.site;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.FileUploadBean;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.impl.QuestionDaoImpl;
import com.wxpt.site.entity.Question;
import com.wxpt.site.entity.QuestionType;
import com.wxpt.site.entity.pojo.QuestionPoro;
import com.wxpt.site.service.QuestionService;
@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
		@Result(name = "question", type = "json", params = { "root", "result" }),
		@Result(name = "success", location = "success.jsp") })
public class ManageQuestionAction extends ParentAction {
	private List<QuestionType> qsType;
	QuestionDaoImpl questionDao =new QuestionDaoImpl();
	GetCookie cookies=new GetCookie();
	int enterId=cookies.getCookie();
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		qsType = questionService.getQsTypeList(enterId);
		String message = "[";
		for (int i = 0; i < qsType.size(); i++) {
			QuestionType questionType = qsType.get(i);
			if ((i + 1) == qsType.size()) {
				message += "{\"id\":\"" + questionType.getTypeId()
						+ "\",\"text\":\"" + questionType.getTypeName() + "\"}";
			} else {
				message += "{\"id\":\"" + questionType.getTypeId()
						+ "\",\"text\":\"" + questionType.getTypeName()
						+ "\"},";
			}
		}

		message += "]";
		System.out.println(message);
		super.out.print(message);
		super.out.flush();
		super.out.close();
		return "question";
	}

	private String qustionTitle;
	private File qustionContent;
	private String answera;
	private String answerb;
	private String answerc;
	private String answerd;
	private String rightAnswer;
	private FileUploadBean bean = new FileUploadBean();
	private String message;
	private int type;
	@Autowired
	QuestionService questionService;
	private String fileName;
	private String content;
	private int typeId;
	private int questionType;
	public String addQuestion() {
		QuestionType q  = questionDao.getTypeById(enterId,questionType);
		Question question = new Question();
		if(type == 0||type==2){
			if (qustionContent != null) {
				fileName = TimeUtil.getImagesTime()
						+ fileName.substring(fileName.lastIndexOf("."),
								fileName.length());
				bean.upLoad(fileName, qustionContent,enterId+"");
				question.setQustionContent(fileName);
				question.setQuestionTitle(qustionTitle);
			}
			question.setType(type);
		}
		if(type == 1){
			question.setType(1);
			question.setQuestionTitle(" ");
			question.setQustionContent(content);
		}
		question.setQuestionType(q);
		question.setQuestionTitle(qustionTitle);
		question.setAnswerA(answera);
		question.setAnswerB(answerb);
		question.setAnswerC(answerc);
		question.setAnswerD(answerd);
		question.setRightAnswer(rightAnswer);
		try {
			questionService.addQuestion(enterId,question);
			message = "添加成功!";
		} catch (Exception e) {
			// TODO: handle exception
			message = "添加失败!";
		}
		return "success";
	}

	public String updateQuestion() {
		System.out.println(type);
		try {
			Question question = questionService.getQuestionByID(enterId,questionId);
			if (type == 0) {
				if (qustionContent != null) {
					if (question.getType() == 0) {
						bean.deletefile(enterId+"/"
								+ question.getQustionContent());
					}
					fileName = TimeUtil.getImagesTime()
							+ fileName.substring(fileName.lastIndexOf("."),
									fileName.length());
					bean.upLoad(fileName, qustionContent,enterId+"");
					question.setQustionContent(fileName);
					question.setQuestionTitle(qustionTitle);
				}
				question.setQuestionTitle(qustionTitle);
				question.setType(0);
			}
			if (type == 1 && content != null && content.equals("") == false) {
				question.setType(1);
				question.setQuestionTitle("");
				question.setQustionContent(content);
			}
			QuestionType ques = questionDao.getTypeById(enterId,questionType);
			System.out.println(ques.getTypeName());
			question.setQuestionType(ques);
			
				question.setAnswerA(answera);
			
			
				question.setAnswerB(answerb);
			
			
				question.setAnswerC(answerc);
			
			
				question.setAnswerD(answerd);
			
			question.setRightAnswer(rightAnswer);
			/*questionService.updateBy(enterId,question,q.getTypeId());*/
			questionDao.updateBy(enterId,question,ques.getTypeId());
			message="修改成功!";
		} catch (Exception e) {
			// TODO: handle exception
			message="修改失败!";
		}
		return "success";
	}

	public int getQuestionType() {
		return questionType;
	}



	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}



	public int getTypeId() {
		return typeId;
	}



	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}



	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	private List<Question> qtList;
	JSONArray jsonls;
	int count;// 总记录数
	int rows;
	int page;
	private List<QuestionPoro> questionPoroList;
	
	public String getQuestionList() {
		qtList = questionService.getQuestionList(enterId,page, rows);

		questionPoroList = new ArrayList<QuestionPoro>();
		for (Question question : qtList) {
			QuestionPoro questionPoro = new QuestionPoro();
			questionPoro.setQuestionid(question.getQuestionid());
			questionPoro.setAnswerA(question.getAnswerA());
			questionPoro.setAnswerB(question.getAnswerB());
			questionPoro.setAnswerC(question.getAnswerC());
			questionPoro.setAnswerD(question.getAnswerD());
			questionPoro.setRightAnswer(question.getRightAnswer());
			questionPoro.setQuestionTitle(question.getQuestionTitle());
			if (question.getType() == 0) {
				questionPoro
						.setQustionContent("<embed src=\"../web/images/"+enterId+"/"
								+ question.getQustionContent()
								+ "\" style=\"width:100px; height:60px;\" autostart=false loop=\"false\" />");
				questionPoro.setQuestionTypeStr("音乐");
			} else {
				questionPoro.setQustionContent(question.getQustionContent());
				questionPoro.setQuestionTypeStr("文本");
			}
			int typeId=questionService.questionIdById(enterId,question.getQuestionid());
			String typeName=questionService.typeName(enterId,typeId);
			questionPoro.setQuestionTypeStr(typeName);
			
			questionPoro
					.setQuestionType(typeId);
			questionPoroList.add(questionPoro);
		}

		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "user" });
			jsonls = JSONArray.fromObject(questionPoroList);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql="select * from wxpt"+enterId+".question";
		System.out.println(sql);
		/*count = questionService.getCount(sql);*/
		count = questionService.getquestionCount(sql);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		System.out.println(jsonls+"wwwwwwww");
		return "question";
	}
	private int questionId;
	private String questionIds;
	public String deleQuestion(){
		try {
			String quetionIDs[] = questionIds.split(",");
			for (int i = 0; i < quetionIDs.length; i++) {
				Question question = questionService.getQuestionByID(enterId,Integer
						.parseInt(quetionIDs[i]));
				questionService.deleQuestion(enterId,question);
				bean.deletefile(enterId+"/" + question.getQustionContent());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "question";
	}
	
	public String getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(String questionIds) {
		this.questionIds = questionIds;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public List<Question> getQtList() {
		return qtList;
	}

	public void setQtList(List<Question> qtList) {
		this.qtList = qtList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getQustionTitle() {
		return qustionTitle;
	}

	public void setQustionTitle(String qustionTitle) {
		this.qustionTitle = qustionTitle;
	}

	public File getQustionContent() {
		return qustionContent;
	}

	public void setQustionContent(File qustionContent) {
		this.qustionContent = qustionContent;
	}

	public String getAnswera() {
		return answera;
	}

	public void setAnswera(String answera) {
		this.answera = answera;
	}

	public String getAnswerb() {
		return answerb;
	}

	public void setAnswerb(String answerb) {
		this.answerb = answerb;
	}

	public String getAnswerc() {
		return answerc;
	}

	public void setAnswerc(String answerc) {
		this.answerc = answerc;
	}

	public String getAnswerd() {
		return answerd;
	}

	public void setAnswerd(String answerd) {
		this.answerd = answerd;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<QuestionType> getQsType() {
		return qsType;
	}

	public void setQsType(List<QuestionType> qsType) {
		this.qsType = qsType;
	}

	
	

}
