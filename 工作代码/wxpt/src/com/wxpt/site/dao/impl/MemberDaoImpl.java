package com.wxpt.site.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.org.apache.regexp.internal.recompile;
import com.wxpt.common.JDBC_test;
import com.wxpt.common.PageBean;
import com.wxpt.site.dao.MemberDao;
import com.wxpt.site.entity.Activity;
import com.wxpt.site.entity.Coupons;
import com.wxpt.site.entity.Integrals;
import com.wxpt.site.entity.Keywordexplicit;
import com.wxpt.site.entity.MamberGrade;
import com.wxpt.site.entity.Member;
import com.wxpt.site.entity.pojo.Integrals2;
import com.wxpt.site.entity.pojo.Message2;

import com.wxpt.site.entity.Storerecord;
import com.wxpt.site.entity.Message;

public class MemberDaoImpl implements MemberDao {
	private Member member;
	private List<Integrals> inte;
	private Integrals inter;
	private int jifen;
	private String chuzhi;

	public String getChuzhi() {
		return chuzhi;
	}

	public void setChuzhi(String chuzhi) {
		this.chuzhi = chuzhi;
	}

	public int getJifen() {
		return jifen;
	}

	public void setJifen(int jifen) {
		this.jifen = jifen;
	}

	public Integrals getInter() {
		return inter;
	}

	public void setInter(Integrals inter) {
		this.inter = inter;
	}

	private Integrals2 integ;

	public Integrals2 getInteg() {
		return integ;
	}

	public void setInteg(Integrals2 integ) {
		this.integ = integ;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<Integrals> getInte() {
		return inte;
	}

	public void setInte(List<Integrals> inte) {
		this.inte = inte;
	}

	@Override
	public Member getOne(int id, int enterId) {
		// TODO Auto-generated method stub
		member = new Member();
		try {
			String sql = "select * from wxpt" + enterId
					+ ".member where member_id=" + id;
			// String hql="from Member where memberId="+id;
			// member=(Member)
			// this.sessionFactory.getCurrentSession().createQuery(hql).list().get(0);
			member = (Member) this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Member.class).list().get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

	@Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc = new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;

	// 会员注册
	@SuppressWarnings("deprecation")
	@Override
	public int addMember(int enterId, String cardId, String weixinId,
			String businessId, String addTime, String endTime, int memberGrade) {

		int i = 0;
		String sql = "INSERT INTO wxpt"
				+ enterId
				+ ".member ( `card_id`, `weixin_id`, `business_id`,`save_money`,`add_time`, `end_time`, `member_grade`,`password`,`member_freeze`) "
				+ "VALUES ('" + cardId + "','" + weixinId + "','" + businessId
				+ "','" + 0 + "','" + addTime + "'," + "'" + endTime + "',"
				+ memberGrade + ",'" + 888888 + "'," + 1 + ")";
		System.out.println(sql);
		try {
			session = (Session) this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			i = session.connection().createStatement().executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getmemberall(int enterId, int start, int number) {
		List<Member> list;
		try {
			String sql = "select * from wxpt" + enterId + ".member";
			System.out.println(sql);
			Query query = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Member.class);

			query.setFirstResult(start);
			query.setMaxResults(number);

			list = query.list();
		} catch (Exception e) {
			// TODO: handle exception

			return null;
		}
		return list;
	}

	// 查询信息
	@SuppressWarnings("unchecked")
	public List<Member> findMember(int member_freeze, int member_grade,
			String add_time, String weixin_id, String memberName, int start,
			int number, int enterId) {
		List<Member> list;
		try {
			String sql = "select * from wxpt" + enterId + ".member where 1=1";
			System.out.println(sql);

			// 姓名
			if (memberName != null && !memberName.equals("")) {
				sql += " and username like '%" + memberName + "%'";
			}
			// 会员卡号
			if (weixin_id != null && !weixin_id.equals("")) {
				sql += " and card_id like '%" + weixin_id + "%'";
			}
			// 添加时间
			if (add_time != null && !add_time.equals("")) {
				/*
				 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 * Date weixin_date = sdf.parse(add_time);
				 */
				sql += " and add_time='" + add_time + "'";
			}
			// 会员级别
			if (member_grade != -1) {
				sql += " and member_grade='" + member_grade + "'";
			}
			if (member_freeze != -1) {
				sql += " and member_freeze='" + member_freeze + "'";
			}
			Query query = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Member.class);
			System.out.println(sql);
			query.setFirstResult(start);
			query.setMaxResults(number);
			list = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return list;
	}

	// 查询数据总条数

	@SuppressWarnings("unused")
	public int MemberCount(int member_freeze, int member_grade,
			String add_time, String weixin_id, String memberName, int start,
			int number, int enterId) {
		List<Member> list;
		int acount = 0;

		String sql = "select * from wxpt" + enterId + ".member where 1=1";
		System.out.println(sql);

		// 姓名
		if (memberName != null && !memberName.equals("")) {
			sql += " and username like '%" + memberName + "%'";
		}
		// 微信号
		if (weixin_id != null && !weixin_id.equals("")) {
			sql += " and weixin_id like '%" + weixin_id + "%'";
		}
		// 添加时间
		if (add_time != null && !add_time.equals("")) {
			/*
			 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); Date
			 * weixin_date = sdf.parse(add_time);
			 */
			sql += " and add_time='" + add_time + "'";
		}
		// 会员级别
		if (member_grade != -1) {
			sql += " and member_grade='" + member_grade + "'";
		}
		if (member_freeze != -1) {
			sql += " and member_freeze='" + member_freeze + "'";
		}
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Member.class);
		System.out.println(sql);
		/*
		 * query.setFirstResult(start); query.setMaxResults(number);
		 */
		acount = query.list().size();
		return acount;
	}

	@SuppressWarnings("static-access")
	@Override
	public void getChange(int id, String xiangxidizhi, int nianling,
			String shoujihao, int xingbie, String xingming, int enterId) {
		// TODO Auto-generated method stub
		try {
			// this.sessionFactory.getCurrentSession().update(member);
			String sql = "update member set address='" + xiangxidizhi
					+ "',age=" + nianling + ",phone='" + shoujihao + "',sex="
					+ xingbie + ",username='" + xingming + "' where member_id="
					+ id;
			con = jdbc.getConnection(enterId);
			pstmt = con.prepareStatement(sql);
			// 执行
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getmembercount(int enterId) {
		int listcount;
		try {
			String sql = "select * from wxpt" + enterId + ".member";
			System.out.println(sql);
			Query query = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Member.class);

			listcount = query.list().size();
		} catch (Exception e) {
			// TODO: handle exception

			return 0;
		}
		return listcount;
	}

	@Override
	public Member jiedong(int enterId, int memberIdint) {
		Member member;
		try {
			String sql = "select * from wxpt" + enterId
					+ ".member where `member_id`=" + memberIdint;
			Query query = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Member.class);
			member = (Member) query.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception

			return null;
		}
		return member;
	}

	@SuppressWarnings("static-access")
	@Override
	public void update(int enterId, Member member) {
		// TODO Auto-generated method stub
		String sql = "UPDATE wxpt" + enterId + ".member set member_freeze="
				+ member.getMemberFreeze() + " where member_id="
				+ member.getMemberId();

		System.out.println(sql);
		con = jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement(sql);
			// 执行
			pstmt.executeUpdate();
			con.close(); // 关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 查询记录表
	@SuppressWarnings("unchecked")
	public List<Storerecord> find_By_Storerecord_id(int memberId, int enterId) {

		String sql = "select * from wxpt" + enterId
				+ ".storerecord where member_id=" + memberId;
		List<Storerecord> list = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Storerecord.class).list();
		return list;
	}

	@Override
	public Member getOneCardid(String card, int enterId) {
		// TODO Auto-generated method stub
		member = new Member();
		try {
			String sql = "select * from wxpt" + enterId
					+ ".member where card_id='" + card + "'";
			/*
			 * String hql="from Member where cardId= '"+card+"'";
			 * member=(Member)
			 * this.sessionFactory.getCurrentSession().createQuery
			 * (hql).list().get(0);
			 */
			member = (Member) this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Member.class).list().get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> checkMember(int enterId, String weixinId) {
		// TODO Auto-generated method stub
		List<Member> list = new ArrayList<Member>();
		String sql = "select * from wxpt" + enterId
				+ ".member where `weixin_id`= '" + weixinId + "'";
		list = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Member.class).list();
		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> checkMembers(int enterId, String businessId) {
		// TODO Auto-generated method stub
		String sql = "select * from wxpt" + enterId
				+ ".member where `business_id`= '" + businessId + "'";
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Member.class).list();
	}

	@Override
	public void delMember(String hql) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().createSQLQuery(hql)
				.executeUpdate();
	}

	/*
	 * @Override public List<Message> quliuyan(int memberIdint) { List<Message>
	 * listmessage; try { String sql =
	 * "select * from wxpt62.message where message_parentid=0 and member_id="
	 * +memberIdint; System.out.println(sql); Query query =
	 * this.sessionFactory.getCurrentSession
	 * ().createSQLQuery(sql).addEntity(Message.class);
	 * 
	 * listmessage = query.list(); } catch (Exception e) { // TODO: handle
	 * exception
	 * 
	 * return null; } return listmessage; }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public PageBean quliuyan(int enterId, int currentpage, int PAGE_SIZE,
			int memberIdint) {
		List<Message> listmessage;

		String sql = "select * from wxpt" + enterId
				+ ".message where message_parentid=0 and member_id="
				+ memberIdint;
		System.out.println(sql);
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Message.class);

		int total = query.list().size();
		int firstPage = (currentpage - 1) * PAGE_SIZE;
		query.setFirstResult(firstPage);
		query.setMaxResults(PAGE_SIZE);
		listmessage = query.list();

		return new PageBean(listmessage, total, PAGE_SIZE, currentpage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integrals> getListIntegrals(int id, int enterId) {
		// TODO Auto-generated method stub
		inte = new ArrayList<Integrals>();
		String sql = "SELECT * FROM wxpt" + enterId
				+ ".integrals WHERE member_id =" + id;
		try {
			inte = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
					.addEntity(Integrals.class).list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inte;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void addMessage(int enterId, Message message) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO wxpt"
				+ enterId
				+ ".message ( `member_id`, `message_title`, `message_content`,`message_type`,`time`, `message_parentid`) "
				+ "VALUES ('" + message.getMember().getMemberId() + "','"
				+ message.getMessageTitle() + "','"
				+ message.getMessageContent() + "','"
				+ message.getMessageType() + "','" + message.getTime() + "',"
				+ "'" + message.getMessageParentid() + "')";
		System.out.println(sql);
		try {
			session = (Session) this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.connection().createStatement().executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Message getmessagebyid(int enterId, int messageidint) {
		Message message;
		try {
			String sql = "select * from wxpt" + enterId
					+ ".message where `message_id`=" + messageidint;
			System.out.println(sql);
			Query query = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Message.class);

			message = (Message) query.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception

			return null;
		}

		return message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integrals2 getOneIntegrals(int id, int enterId) {
		// TODO Auto-generated method stub
		/* List<Object[]> aList = new ArrayList<Object[]>(); */
		integ = new Integrals2();
		String sql = "SELECT *, SUM(integrals_one) as he FROM wxpt" + enterId
				+ ".integrals WHERE member_id =" + id;
		try {
			List<Object[]> aList = (List<Object[]>) this.sessionFactory
					.getCurrentSession().createSQLQuery(sql).list();
			integ.setIntegralsId((Integer) aList.get(0)[0]);
			integ.setMemberId((Integer) aList.get(0)[1]);
			integ.setIntegralsOne((Integer) aList.get(0)[2]);
			Date dd = (Date) aList.get(0)[3];
			String time = new SimpleDateFormat("yyyy-MM-dd").format(dd);
			integ.setIntegralsTime(time);
			integ.setIntegralsBusiness(aList.get(0)[4].toString());
			integ.setIntegralsRemark(aList.get(0)[5].toString());
			integ.setHe(Integer.parseInt(((Object) aList.get(0)[6]).toString()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return integ;
	}

	public Member find_by_member_memberid(int enterId, int member_id) {
		String sql = "select * from wxpt" + enterId
				+ ".member where member_id=" + member_id;
		System.out.println(sql);
		Member member = (Member) this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Member.class).list().get(0);
		return member;
	}

	@SuppressWarnings("deprecation")
	public int add_storeRecod(String money, String recordtime, int memberid,
			String businessName, Double present_money, int enterId) {
		int i = 0;
		String sql = "INSERT INTO wxpt"
				+ enterId
				+ ".storerecord ( `money`, `recordtime`, `member_id`,`businessName`,`present_money`) "
				+ "VALUES ('" + money + "','" + recordtime + "','" + memberid
				+ "','" + businessName + "','" + present_money + "')";
		try {
			/*
			 * session=(Session)this.sessionFactory.getCurrentSession();
			 * session.beginTransaction();
			 */
			/* i = session.connection().createStatement().executeUpdate(sql); */
			this.sessionFactory.getCurrentSession().connection()
					.createStatement().executeUpdate(sql);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	public PageBean spiltPageStorerecord(int enterpriseId, int curPage,
			int pageSize, int memberId) {
		String sql = "select * from wxpt" + enterpriseId
				+ ".storerecord where member_id=" + memberId;
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Storerecord.class);
		int total = query.list().size();
		int firstPage = (curPage - 1) * 5;
		query.setFirstResult(firstPage);
		query.setMaxResults(pageSize);
		List<Storerecord> list = query.list();
		return new PageBean(list, total, pageSize, curPage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageBean showMessage(int enterId, int start, int pageSize,
			int memberId) {
		List<Message> list;
		String sql = "select * from wxpt"
				+ enterId
				+ ".message where message_parentid != 0 and message_type =1 and member_id="
				+ memberId + "";
		System.out.println(sql);
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Message.class);
		int total = query.list().size();
		int firstPage = (start - 1) * pageSize;
		query.setFirstResult(firstPage);
		query.setMaxResults(pageSize);
		list = query.list();
		return new PageBean(list, total, pageSize, start);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getByMessageId(int enterId, int messageId, int type) {
		// TODO Auto-generated method stub
		List<Message> list;
		try {
			String sql = "select * from wxpt" + enterId
					+ ".message where message_type =" + type
					+ " and message_id=" + messageId + "";
			System.out.println(sql);
			Query query = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Message.class);

			query.setFirstResult(0);
			query.setMaxResults(10);

			list = query.list();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return list;
	}

	@Override
	public Message getByMessageIds(int enterId, int messageId, int type) {
		// TODO Auto-generated method stub
		String sql = "select * from wxpt" + enterId
				+ ".message where message_type =" + type + " and message_id="
				+ messageId + "";

		return (Message) this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Message.class).list().get(0);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Activity> getActivity(int enterId, int id) {
		String sql = "select * from wxpt" + enterId
				+ ".activity  where activity_id=" + id;
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Activity.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageBean getByIdActivity(int enterId, int start, int pageSize) {
		List<Activity> list;
		String sql = "select * from wxpt" + enterId + ".activity";
		System.out.println(sql);
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Activity.class);
		int total = query.list().size();
		int firstPage = (start - 1) * pageSize;
		query.setFirstResult(firstPage);
		query.setMaxResults(pageSize);
		list = query.list();
		return new PageBean(list, total, pageSize, start);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integrals> getFenye(int id, int enterId, int start, int number) {
		// TODO Auto-generated method stub
		inte = new ArrayList<Integrals>();
		String sql = "SELECT * FROM wxpt" + enterId
				+ ".integrals WHERE member_id =" + id;
		try {
			Query query = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Integrals.class);
			query.setFirstResult(start);
			query.setMaxResults(number);
			inte = query.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inte;
	}

	@Override
	public Integrals getxiang(int id, int enterId) {
		// TODO Auto-generated method stub
		inter = new Integrals();
		String sql = "select * from wxpt" + enterId
				+ ".integrals WHERE integrals_id=" + id;
		try {
			inter = (Integrals) this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Integrals.class).list()
					.get(0);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inter;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> quhuifuliuyan(int enterId, Integer messageId) {
		List<Message> listhui;
		try {
			String sql = "select * from wxpt" + enterId
					+ ".message where message_parentid=" + messageId;
			System.out.println(sql);
			Query query = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Message.class);

			listhui = query.list();

		} catch (Exception e) {

			return null;
		}
		return listhui;
	}

	@SuppressWarnings("static-access")
	@Override
	public void getChangePassword(int id, int enterId, String ps) {
		// TODO Auto-generated method stub
		String sql = "update wxpt" + enterId + ".member set password='" + ps
				+ "' where member_id=" + id;

		try {
			con = jdbc.getConnection(enterId);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 执行

	}

	@SuppressWarnings("unchecked")
	@Override
	public int getJifen(int id, int enterId) {
		// TODO Auto-generated method stub
		jifen = 0;
		String sql = "SELECT *, SUM(integrals_one) as he FROM wxpt" + enterId
				+ ".integrals WHERE member_id =" + id;
		try {
			List<Object[]> aList = (List<Object[]>) this.sessionFactory
					.getCurrentSession().createSQLQuery(sql).list();
			System.out.println(((Object) aList.get(0)[6]).toString());
			jifen = Integer.parseInt(((Object) aList.get(0)[6]).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jifen = 0;
		}
		return jifen;
	}

	@SuppressWarnings("static-access")
	@Override
	public int getChangejifen(int id, int enterId, int jifen) {
		// TODO Auto-generated method stub
		int i = 0;
		String sql = "update wxpt"+enterId+".member set member_points=" + jifen
				+ " where member_id=" + id;
		try {
			con = jdbc.getConnection(enterId);
			pstmt = con.prepareStatement(sql);
			i = pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@SuppressWarnings("static-access")
	@Override
	public void getChangeGrade(int id, int enterId, int Grade) {
		// TODO Auto-generated method stub
		String sql = "update member set member_grade=" + Grade
				+ " where member_id=" + id;
		try {
			con = jdbc.getConnection(enterId);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 促销活动添加
	@SuppressWarnings("deprecation")
	@Override
	public void addActivity(int enterId, Activity activity) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO wxpt"
				+ enterId
				+ ".activity ( `activity_title`, `activity_content`, `activity_starttime`,`activity_endtime`,`image_url`) "
				+ "VALUES ('" + activity.getActivityTitle() + "','"
				+ activity.getActivityContent() + "'," + "'"
				+ activity.getActivityStarttime() + "','"
				+ activity.getActivityEndtime() + "','"
				+ activity.getImageUrl() + "')";
		System.out.println(sql);
		try {
			this.sessionFactory.getCurrentSession().connection()
					.createStatement().executeUpdate(sql);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 促销活动update
	@SuppressWarnings("static-access")
	@Override
	public void updateActivity(int enterId, Activity activity, int id) {
		// TODO Auto-generated method stub
		String sql = "update activity set activity_title='"
				+ activity.getActivityTitle() + "',activity_content='"
				+ activity.getActivityContent() + "'," + "activity_starttime='"
				+ activity.getActivityStarttime() + "',activity_endtime='"
				+ activity.getActivityEndtime() + "',image_url='"
				+ activity.getImageUrl() + "' where activity_id=" + id;
		try {
			con = jdbc.getConnection(enterId);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delactivity(int enterId, int activityidint) {
		// TODO Auto-generated method stub
		try {
			String sql = "delete from wxpt" + enterId
					+ ".activity where activity_id =" + activityidint;
			// Query query =
			// this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Activity.class);
			SQLQuery query = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			query.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Activity getactivitybyid(int enterId, int activityidint) {
		Activity ac;
		try {
			String sql = "select * from wxpt" + enterId
					+ ".activity where activity_id=" + activityidint;
			System.out.println(sql);
			Query query = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Activity.class);

			ac = (Activity) query.list().get(0);

		} catch (Exception e) {

			return null;
		}
		return ac;
	}

	// 优惠券
	@SuppressWarnings("unchecked")
	@Override
	public PageBean getByMemberId(int memberId, int enterId, int start,
			int pageSize) {
		List<Coupons> list;
		String sql = "select * from wxpt" + enterId
				+ ".coupons where member_id =" + memberId;
		System.out.println(sql);
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Coupons.class);
		int total = query.list().size();
		int firstPage = (start - 1) * pageSize;
		query.setFirstResult(firstPage);
		query.setMaxResults(pageSize);
		list = query.list();
		return new PageBean(list, total, pageSize, start);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Coupons> getCouponsById(int couponsId, int enterId) {
		// TODO Auto-generated method stub
		String sql = "select * from wxpt" + enterId
				+ ".coupons where coupons_id =" + couponsId + "";

		return this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Coupons.class).list();

	}

	@Override
	public String chuzhi(int id, int enterId) {
		// TODO Auto-generated method stub
		Storerecord sr = new Storerecord();
		chuzhi = null;
		String sql = "SELECT * FROM wxpt" + enterId
				+ ".storerecord WHERE `member_id`=" + id
				+ " and `id`=(select max(`id`) from wxpt" + enterId
				+ ".storerecord where  `member_id`=" + id + ")";
		// String
		// sql="SELECT * FROM wxpt"+enterId+".storerecord WHERE `member_id`="+id+" order by `id` desc limit 1";
		sr = (Storerecord) this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Storerecord.class).list().get(0);
		chuzhi = sr.getPresentMoney().toString();
		return chuzhi;
	}

	@SuppressWarnings("static-access")
	@Override
	public int getChangechuzhi(int id, int enterId, String chuzhi) {
		// TODO Auto-generated method stub
		int i = 0;
		String sql = "update member set save_money=" + chuzhi
				+ " where member_id=" + id;
		try {
			con = jdbc.getConnection(enterId);
			pstmt = con.prepareStatement(sql);
			i = pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public Storerecord findStorerecordByid(int id, int enterId) {
		String sql = "select * from wxpt" + enterId + ".storerecord where id ="
				+ id + "";
		return (Storerecord) this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Storerecord.class).list().get(0);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> findByWeiInId(int enterId, String weiXinId) {
		// TODO Auto-generated method stub
		List<Member> list = new ArrayList<Member>();
		try {
			String sql = "SELECT * FROM wxpt" + enterId
					+ ".member WHERE `weixin_id`='" + weiXinId + "'";
			list = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
					.addEntity(Member.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@SuppressWarnings("static-access")
	@Override
	public void updateMember(String sql, int enterId) {
		// TODO Auto-generated method stub
		try {
			con = jdbc.getConnection(enterId);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MamberGrade> getAll(String sql,int page,int pageSize) {
		// TODO Auto-generated method stub
		List<MamberGrade> list = new ArrayList<MamberGrade>();
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(MamberGrade.class);
		query.setFirstResult((page-1)*pageSize);
		query.setMaxResults(pageSize);
		list=query.list();
		return list;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void addGrade(String sql) {
		// TODO Auto-generated method stub
		try {
			session = (Session) this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.connection().createStatement().executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void updateGrade(String sql, int enterId) {
		// TODO Auto-generated method stub
		try {
			con = jdbc.getConnection(enterId);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteGrade(String sql) {
		// TODO Auto-generated method stub
		try {
			SQLQuery query = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			query.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public MamberGrade getById(String sql) {
		// TODO Auto-generated method stub
		return (MamberGrade) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
		.addEntity(MamberGrade.class).list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MamberGrade> getList(String sql) {
		// TODO Auto-generated method stub
		return  this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(MamberGrade.class).list();
	}

}
