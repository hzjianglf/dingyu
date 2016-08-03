package com.wxpt.site.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.jdbc.Statement;
import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.QuestionDao;
import com.wxpt.site.entity.Answer;
import com.wxpt.site.entity.Idiom;
import com.wxpt.site.entity.Keywordexplicit;
import com.wxpt.site.entity.Keywords;
import com.wxpt.site.entity.Question;
import com.wxpt.site.entity.QuestionRule;
import com.wxpt.site.entity.QuestionTishi;
import com.wxpt.site.entity.QuestionType;
import com.wxpt.site.entity.TrainNumber;

public class QuestionDaoImpl implements QuestionDao {

	@Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	@Override
	public void addQuestion(int enterId,Question question) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".question( `qustion_content`, `answer_a`, `answer_b`, `answer_c`, `answer_d`, `right_answer`, `question_title`, `type`, `typeId`) " +
				"VALUES ('"+question.getQustionContent()+"','"+question.getAnswerA()+"','"+question.getAnswerB()+"'," +
						"'"+question.getAnswerC()+"','"+question.getAnswerD()+"','"+question.getRightAnswer()+"','"+question.getQuestionTitle()+"',"+question.getType()+","+question.getQuestionType().getTypeId()+")";
		try {
			session=(Session)this.sessionFactory.getCurrentSession();
			session.beginTransaction();
		session.connection().createStatement().executeUpdate(sql);
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		/*this.sessionFactory.getCurrentSession().save(question);*/
	}

	@Override
	public List<Question> getQuestionList() {
		// TODO Auto-generated method stub
		String hql = "from Question";
		List<Question> questionList = this.sessionFactory.getCurrentSession()
				.createQuery(hql).list();
		return questionList;
	}

	@Override
	public void deleQuestion(int enterId,Question question) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM wxpt"+enterId+".question WHERE questionid="+question.getQuestionid();
		System.out.println(sql);
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public Question getQuestionByID(int enterId,int questionId) {
		// TODO Auto-generated method stub
		String hql="select * from wxpt"+enterId+".question where questionid =" +questionId;
		Question question = (Question) this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Question.class).list().get(0);
		return question;
	}

	@SuppressWarnings("static-access")
	@Override
	public void addAnswer(int MaxId,int enterId,int state,String foruser,String time, int id) {
		// TODO Auto-generated method stub
		
		con = jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement("insert into wxpt"+enterId+".answer values(?,?,?,?,?,?)");
			pstmt.setInt(1, MaxId*1+1);
			pstmt.setString(2, foruser);
			pstmt.setString(3, time);
			pstmt.setInt(4, id);
			pstmt.setString(5, "null");
			pstmt.setInt(6, state);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Answer getMaxAnswer(int enterId,String sendUser) {
		// TODO Auto-generated method stub
		List<Answer> answerlist=null;
		Answer answer=null;
		String hql = "select * from wxpt"+enterId+".answer where  answerid = (select max(answerid) from wxpt"+enterId+".answer where sendUser = '"+sendUser+"') and (UNIX_TIMESTAMP(now()) - UNIX_TIMESTAMP(answertime) ) < 60 and state =0";
		 System.out.println(hql);
		try {
			answerlist =  this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).addEntity(Answer.class).list();
			if(answerlist.size()>0){
				answer=answerlist.get(0);
			}
			return answer;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return answer;
		}
		
	}

	@Override
	public int updateAnswer(int enterId,Answer answer,int questionId) {
		// TODO Auto-generated method stub
		//String sql="UPDATE answer SET `sendUser`='"+answer.getSendUser()+"',`state`="+answer.getState()+" WHERE `answerid`="+answer.getAnswerid()+"";
		int i = 0;
		String sql="UPDATE wxpt"+enterId+".answer SET `sendUser`='"+answer.getSendUser()+"',`answertime`='"+answer.getAnswertime()+"',`questionid`="+questionId+",`send_answer`='"+answer.getSendAnswer()+"',`state`="+answer.getState()+" WHERE `answerid`="+answer.getAnswerid()+"";
		
		con=jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement(sql);
			//执行
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*this.sessionFactory.getCurrentSession().update(answer);*/
		return i;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> getDayAnswer(int enterId,String sendUser) {
		// TODO Auto-generated method stub
		List<Answer> answerList=null;
		try {
			String hql = "select * from wxpt"+enterId+".answer where sendUser = '"+sendUser+"' and datediff(now(),str_to_date(answertime,'%Y/%m/%d %H:%i:%s') )=0";
			 answerList = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Answer.class).list();
			 return answerList;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answerList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> getQtByIds(int enterId,String ids) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".question where  questionid not in ("+ids+")";
		List<Question> questionList = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Question.class).list();
		return questionList;
	}

	@Override
	public Answer getMaxAnswerById(String sendUser) {
		// TODO Auto-generated method stub
		String hql = "from Answer where sendUser = '"+sendUser+"' and answerid = (select max(answerid) from Answerwhere sendUser = '"+sendUser+"')";
		try {
			Answer answer = (Answer) this.sessionFactory.getCurrentSession()
					.createQuery(hql).list().get(0);
			return answer;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void updateBySql(int enterId,String sendUser) {
		// TODO Auto-generated method stub
		String sql="UPDATE wxpt"+enterId+".answer SET `state`=1 WHERE `sendUser`='"+sendUser+"'";
		con=jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement(sql);
			//执行
			pstmt.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();*/
	}

	@Override
	public Answer getMaxAnswer(int enterId,String sendUser, int state) {
		// TODO Auto-generated method stub
		List<Answer> answerlist=null;
		Answer answer=null;
		String hql = "select * FROM wxpt"+enterId+".answer where  answerid = (select max(answerid) from wxpt"+enterId+".answer where sendUser = '"+sendUser+"') and state = "+state;
		System.out.println(hql);
		try {
			answerlist = this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).addEntity(Answer.class).list();
			if(answerlist.size()>0){
				answer=answerlist.get(0);
			}
			return answer;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<Answer> getAnAnswerList(int enterId,String sendUser) {
		List<Answer> answerList=null;
		// TODO Auto-generated method stub
		try {
			String hql = "select * from wxpt"+enterId+".answer where sendUser = '"+sendUser+"' and datediff(now(),str_to_date(answertime,'%Y/%m/%d %H:%i:%s') )=0 and send_answer !='null'";
			System.out.println(hql);
			answerList = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Answer.class).list();
			return answerList;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answerList;
	}

	@Override
	public void updateBySql(String sendUser, int state) {
		// TODO Auto-generated method stub
		String sql =" update answer set state = "+state+"  where sendUser = '"+sendUser+"'";
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public List<QuestionType> getQsTypeList(int enterId) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".question_type WHERE `typeId`!=-1";
		List<QuestionType> qt = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(QuestionType.class).list();
		return qt;
	}

	
	
	@Override
	public QuestionType getTypeById(int enterId,int typeId) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".question_type where typeId ="+typeId;
		con=jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement(hql);
			 ResultSet  rs = pstmt.executeQuery();
			 if(rs.next()){
				 QuestionType qu=new QuestionType();
				 qu.setTypeId(rs.getInt(1));
				 qu.setTypeName(rs.getString(2));
				 qu.setTypeNum(rs.getInt(3));
				 return qu;
			 }
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public List<Question> getQuestionList(int enterId,int typeNum) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".question where typeId =" +typeNum;
		List<Question> questionList = this.sessionFactory.getCurrentSession()
				.createSQLQuery(hql).addEntity(Question.class).list();
		return questionList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Question> getQtByIds(int enterId,String ids,int typeNum) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".question where  questionid not in ("+ids+") and typeId =" +typeNum;
		System.out.println(hql);
		List<Question> questionList = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Question.class).list();
		return questionList;
	}
	public QuestionType getTypeByNum(int enterId,int typeNum) {
		// TODO Auto-generated method stub
		String hql = "select * from  wxpt"+enterId+".question_type where typeNum ="+typeNum;
		System.out.println(hql);
		QuestionType q = (QuestionType) this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(QuestionType.class).list().get(0);
		System.out.println(q.getTypeName());
		return q;
	}

	@Override
	public List<Question> getQuestionList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".question  WHERE questionid!=-1";
		System.out.println(hql);
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(hql).addEntity(Question.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}

	@Override
	public int getCount(String hql) {
		// TODO Auto-generated method stub
		System.out.println(hql);
		List<Keywordexplicit>list=this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Keywordexplicit.class).list();
		int i=list.size();
		return i;
	}

	@Override
	public void updateBy(int enterId,Question question,int typeId) {
		// TODO Auto-generated method stub
		
		String sql="UPDATE question SET qustion_content='"+question.getQustionContent()+"',answer_a='"+question.getAnswerA()+"'," +
				"answer_b='"+question.getAnswerB()+"',answer_c='"+question.getAnswerC()+"',answer_d='"+question.getAnswerD()+"'," +
				"right_answer='"+question.getRightAnswer()+"',question_title='"+question.getQuestionTitle()+"'," +
				"type="+question.getType()+",typeId="+typeId+" WHERE questionid="+question.getQuestionid()+"";
		con=jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement(sql);
			//执行
			pstmt.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public int  questionIdById(int enterId, int questionId) {
		// TODO Auto-generated method stub
		String sql="select typeId from wxpt"+enterId+".question where questionid="+questionId;
		int questionId1=(Integer) this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().get(0);
		return questionId1;
	}

	@Override
	public String typeName(int enterId, int typeId) {
		// TODO Auto-generated method stub
		String sql="select typeName from wxpt"+enterId+".question_type where typeId="+typeId;
		String typeName=(String) this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().get(0);
		return typeName;
	}

	@Override
	public int getKeyWordsCount(String sql) {
		// TODO Auto-generated method stub
		System.out.println(sql);
		List<Keywords>list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Keywords.class).list();
		int i=list.size();
		return i;
	}

	@Override
	public int getquestionCount(String sql) {
		// TODO Auto-generated method stub
		List<Question>list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Question.class).list();
		int i=list.size();
		return i;
	}

	@Override
	public int getQuestionId(int enterId,int answerId) {
		// TODO Auto-generated method stub
		String sql="SELECT questionid  FROM wxpt"+enterId+".answer WHERE `answerid`="+answerId;
		int questionId= (Integer) this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().get(0);
		return questionId;
	}

	public int updateBySql2(int enterId, String fromUsername) {
		// TODO Auto-generated method stub
		int i = 0;
		String sql = "SELECT  max(answerid) FROM wxpt"+enterId+".answer WHERE `sendUser`='"+fromUsername+"'";
		int count= (Integer) this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().get(0);
		String sql2="UPDATE wxpt"+enterId+".answer` SET `state`=1 WHERE  `answerid` = "+count+"";
		con=jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement(sql2);
			//执行
			i = pstmt.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return i;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void addRule(String sql) {
		// TODO Auto-generated method stub
		try {
			session=(Session)this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.connection().createStatement().executeUpdate(sql);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionRule> getAllRule(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		List<QuestionRule> list = new ArrayList<QuestionRule>();
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(QuestionRule.class);
		query.setFirstResult((page-1)*pageSize);
		query.setMaxResults(pageSize);
		list=query.list();
		return list;
	}

	@SuppressWarnings("static-access")
	@Override
	public void updateRule(String sql, int enterId) {
		// TODO Auto-generated method stub
		con=jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement(sql);
			//执行
			pstmt.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionTishi> getAllTishi(String sql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(QuestionTishi.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionRule> getRuleList(int enterId) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM wxpt"+enterId+".question_rule order by `question_num` asc";
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(QuestionRule.class).list();
	}

	@Override
	public Answer getMaxId(String sql) {
		// TODO Auto-generated method stub
		return (Answer) this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Answer.class).list().get(0);
		 
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean getidiom(String sql) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		List<Idiom>idList=new ArrayList<Idiom>();
		try {
			idList=(List<Idiom>) this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Idiom.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			return false;
		}
		if(idList.size()==0){
			return false;
		}else{
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Idiom> getIdiom(String sql) {
		// TODO Auto-generated method stub
		List<Idiom> list=new ArrayList<Idiom>();
		list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Idiom.class).list();
		return list;
	}

	@Override
	public TrainNumber getTrain(String sql) {
		// TODO Auto-generated method stub
		TrainNumber trainNumber=null;
		try {
			trainNumber=(TrainNumber) this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(TrainNumber.class).list().get(0);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if(trainNumber==null){
			return null;
		}else{
			return trainNumber;
		}
	}

}
