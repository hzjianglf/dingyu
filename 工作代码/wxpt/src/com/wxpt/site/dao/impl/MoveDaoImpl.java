package com.wxpt.site.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.MoveDao;
import com.wxpt.site.entity.Move;



public class MoveDaoImpl implements MoveDao{
@Autowired
SessionFactory sessionFactory;
PreparedStatement ps=null;
Connection con=null;
JDBC_test jdbc =new JDBC_test();
protected Session session = null;
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Move> findByMoveName(String moveName,int enterId) {
		Move move=null;
		//String hql="from Move where moveName='"+moveName+"'";
		String sql="select * from wxpt"+enterId+".move where move_name='"+moveName+"'";
		List<Move> list = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Move.class).list();
		if(list.size()!=0){
			 move = list.get(0);
			// System.out.println(move.getMoveState());
		}
		
		return list;
		
		
	}
	@SuppressWarnings("deprecation")
	@Override
	public void save(Move move,int enterId) {
		String sql="INSERT INTO wxpt"+enterId+".move( `move_state_desc`, `move_name`, `move_content`, `move_start_time`, `move_end_time`, `move_state`,`awardTime`,probability)" +
				" VALUES ('"+move.getMoveStateDesc()+"','"+move.getMoveName()+"','"+move.getMoveContent()
				+"','"+move.getMoveStartTime()+"','"+move.getMoveEndTime()+"','"+move.getMoveState()+"','"+move.getAwardTime()+"','"+move.getProbability()+"')";
		session=(Session)this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		try {
			session.connection().createStatement().executeUpdate(sql);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Move> getMoveList(int page, int rows, String sql,int enterId) {
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Move.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}
	@Override
	public int getMoveById(String hql) {
		try {
			// TODO Auto-generated method stub
			int count = (Integer) this.sessionFactory.getCurrentSession()
					.createSQLQuery(hql).addEntity(Move.class).list().size();

			return count;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Move> findAllMove(int enterId) {
		// TODO Auto-generated method stub
		List<Move> moveList=null;
		moveList=this.sessionFactory.getCurrentSession().createSQLQuery(" select * from wxpt"+enterId+".move").addEntity(Move.class).list();
		if(moveList.size()==0){
			return moveList;
		}else{
			return moveList;
		}
		
	}
	@Override
	public void delete(Move move) {
		this.sessionFactory.getCurrentSession().delete(move);
		
	}
	@SuppressWarnings("static-access")
	@Override
	public void update(Move move,int enterId) {
		String sql ="UPDATE move SET `move_state_desc`='"+move.getMoveStateDesc()+"',`move_name`='"+move.getMoveName()+"',`move_content`='"+move.getMoveContent()+"',`move_start_time`='"+move.getMoveStartTime()+"',`move_end_time`='"+move.getMoveEndTime()+"',`move_state`="+move.getMoveState()+",`awardTime`='"+move.getAwardTime()+"',`gailv`='"+move.getGailv()+"',`probability`='"+move.getProbability()+"' WHERE `move_id`="+move.getMoveId()+"";
		System.out.println(sql);
		System.out.println(move.getProbability());
		con=jdbc.getConnection(enterId);
		try {
			ps = con.prepareStatement(sql);
			//执行
			ps.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}
	@Override
	public void deleteByMoveId(int moveId,int enterId) {
		String sql="delete from wxpt"+enterId+".move where move_id="+moveId;
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	@Override
	public Move getMoveByMoveId(int moveId,int enterId) {
		String hql="select * from wxpt"+enterId+".move where move_id="+moveId;
		return (Move) this.sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Move.class).list().get(0);
	}
	

}
