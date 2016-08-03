package com.wxpt.site.dao;

import java.util.List;



import com.wxpt.common.PageBean;
import com.wxpt.site.entity.Integrals;


import com.wxpt.common.PageBean;
import com.wxpt.site.entity.Activity;
import com.wxpt.site.entity.Coupons;
import com.wxpt.site.entity.Integrals;
import com.wxpt.site.entity.MamberGrade;
import com.wxpt.site.entity.Member;
import com.wxpt.site.entity.pojo.Integrals2;
import com.wxpt.site.entity.pojo.Message2;
import com.wxpt.site.entity.Storerecord;
import com.wxpt.site.entity.Message;


public interface MemberDao {
	public Member getOne(int id,int enterId);
	public int getJifen(int id,int enterId);
	public String chuzhi(int id,int enterId);
	public int getChangejifen(int id,int enterId,int jifen);
	public int getChangechuzhi(int id,int enterId,String chuzhi);
	public void getChangeGrade(int id,int enterId,int Grade);
	public Member getOneCardid(String card,int enterId);
	public void getChangePassword(int id,int enterId,String ps);
	public void getChange(int id,String xiangxidizhi,int nianling,String shoujihao,int xingbie,String xingming,int enterId);
	public List<Integrals> getListIntegrals(int id,int enterId);
	public Integrals2 getOneIntegrals(int id,int enterId);
	public List<Integrals> getFenye(int id,int enterId,int  start,int number);
	public Integrals getxiang(int id,int enterId);
	//会员注册
	public int addMember(int enterId,String cardId,String weixinId,String businessId,String addTime,String endTime,int memberGrade);
	public List<Member> checkMember(int enterId,String weixinId);
	public List<Member> checkMembers(int enterId,String businessId);
	public void delMember(String hql);
	public List<Member> findByWeiInId(int enterId,String weiXinId);
	//优惠活动
	public List<Activity> getActivity(int enterId,int id);
	public PageBean getByIdActivity(int enterId,int start,int pageSize);
	//优惠券
	public PageBean getByMemberId(int memberId,int enterId,int start,int pageSize);
	public List<Coupons> getCouponsById(int couponsId,int enterId);
	//促销活动添加
	public void addActivity(int enterId,Activity activity);
	public void updateActivity(int enterId,Activity activity,int activityId);
	//得回复信息
	public List<Message> getByMessageId(int enterId,int messageId,int type);
	public Message getByMessageIds(int enterId,int messageId,int type);
	//意见建议添加
	public void addMessage(int enterId,Message message);
	public PageBean showMessage(int enterId,int start,int number,int memberId);
	public List<Member> getmemberall(int enterId,int start, int number);


	public List<Member> findMember(int member_freeze, int member_grade,
			String add_time, String weixin_id, String memberName, int start,
			int number, int enterId);
	public int getmembercount(int enterId);
	public int MemberCount(int member_freeze, int member_grade,
			String add_time, String weixin_id, String memberName, int start,
			int number, int enterId);
	public Member jiedong(int enterId,int memberIdint);
	public void update(int enterId,Member member);

	public PageBean quliuyan(int enterId,int currentpage,int PAGE_SIZE,int memberIdint);
	public Message getmessagebyid(int enterId,int messageidint);

	public List<Storerecord> find_By_Storerecord_id(int memberId ,int  enterId );
	public Member find_by_member_memberid(int enterId,int member_id);
	public int add_storeRecod(String money ,String recordtime,int memberid,String businessName,Double present_money,int enterId);
	public PageBean spiltPageStorerecord(int enterpriseId,int curPage, int pageSize,int memberId);
	public List<Message> quhuifuliuyan(int enterId,Integer messageId);
	public void delactivity(int enterId,int activityidint);
	public Activity getactivitybyid(int enterId,int activityidint);
	public Storerecord findStorerecordByid(int id,int enterId);
	
	//更新 member
	public void updateMember(String sql,int enterId);
	
	//会员等级
	public List<MamberGrade> getAll(String sql,int page,int pageSize);
	
	//添加等级
	public void addGrade(String sql);
	
	//更新
	public void updateGrade(String sql,int enterId);
	
	//删除等级
	public void deleteGrade(String sql);
	
	//查询对象
	public MamberGrade getById(String sql);
	
	//查询集合
	public List<MamberGrade> getList(String sql);
}
