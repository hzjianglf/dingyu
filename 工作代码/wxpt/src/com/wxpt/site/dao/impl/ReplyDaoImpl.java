package com.wxpt.site.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.site.dao.ReplyDao;
import com.wxpt.site.entity.Reply;



public class ReplyDaoImpl implements ReplyDao {
	@Autowired
	SessionFactory sessionFactory;
	Reply reply;
	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	@Override
	public List<Reply> getReplyList() {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery("from Reply")
				.list();
	}

	@Override
	public List<Reply> getReplyList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt"+enterId+".reply";
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Reply.class);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		return query.list();
	}

	@Override
	public int getReplyCount(int enterId) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery("select * from wxpt"+enterId+".reply").addEntity(Reply.class)
				.list().size();
	}

	@Override
	public Reply getReplyByID(int enterId,int replyId) {
		// TODO Auto-generated method stub
		String sql ="select * from wxpt"+enterId+".reply where reply_id = "+ replyId;
		 reply =   (Reply) this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Reply.class).list().get(0);
		System.out.println(reply.getReplyId());
		return reply;
	}

	@Override
	public void deleteById(int replyId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM `reply` WHERE `reply_id` ="+replyId;
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public int getReplyByType(int enterId,int type) {
		// TODO Auto-generated method stub
		
		String sql="select keywordID from wxpt"+enterId+".reply where reply_type = "+type;
		int id = (Integer) this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().get(0);
		return id;
	}

	@Override
	public int getReplyKwordId(String sql) {
		// TODO Auto-generated method stub
		int id = (Integer) this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().get(0);
		return id;
	}

	@Override
	public String getKeywordcontent(String sql) {
		// TODO Auto-generated method stub
		String  count = (String) this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().get(0);
		return count;
	}


}
