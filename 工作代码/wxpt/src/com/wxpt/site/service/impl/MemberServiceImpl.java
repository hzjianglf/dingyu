package com.wxpt.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.PageBean;
import com.wxpt.common.PageBean;
import com.wxpt.site.dao.MemberDao;
import com.wxpt.site.entity.Activity;
import com.wxpt.site.entity.Coupons;
import com.wxpt.site.entity.Integrals;
import com.wxpt.site.entity.MamberGrade;
import com.wxpt.site.entity.Member;
import com.wxpt.site.entity.pojo.Integrals2;
import com.wxpt.site.entity.pojo.Message2;
import com.wxpt.site.entity.Storerecord;
import com.wxpt.site.entity.Message;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.service.MemberService;

public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao memberDao;

	@Override
	@Transactional
	public int addMember(int enterId, String cardId, String weixinId,
			String businessId, String addTime, String endTime, int memberGrade) {
		// TODO Auto-generated method stub
		return memberDao.addMember(enterId, cardId, weixinId, businessId,
				addTime, endTime, memberGrade);

	}

	@Transactional
	public List<Member> getmemberall(int enterId, int start, int number) {
		// TODO Auto-generated method stub
		return memberDao.getmemberall(enterId, start, number);
	}

	@Transactional
	public int getmembercount(int enterId) {
		// TODO Auto-generated method stub
		return memberDao.getmembercount(enterId);
	}

	@Transactional
	public List<Member> findMember(int member_freeze, int member_grade,
			String add_time, String weixin_id, String memberName, int start,
			int number, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.findMember(member_freeze, member_grade, add_time,
				weixin_id, memberName, start, number, enterId);
	}

	// @Override
	// @Transactional
	// public void getChange(Member member) {
	// // TODO Auto-generated method stub
	// memberDao.getChange(member);
	// }

	@Override
	public Member getOne(int id, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.getOne(id, enterId);
	}

	@Transactional
	public int MemberCount(int member_freeze, int member_grade,
			String add_time, String weixin_id, String memberName, int start,
			int number, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.MemberCount(member_freeze, member_grade, add_time,
				weixin_id, memberName, start, number, enterId);
	}

	@Transactional
	public Member jiedong(int enterId, int memberIdint) {
		// TODO Auto-generated method stub
		return memberDao.jiedong(enterId, memberIdint);
	}

	@Transactional
	public void update(int enterId, Member member) {
		// TODO Auto-generated method stub
		memberDao.update(enterId, member);
	}

	@Transactional
	public List<Storerecord> find_By_Storerecord_id(int memberId, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.find_By_Storerecord_id(memberId, enterId);
	}

	@Override
	public Member getOneCardid(String card, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.getOneCardid(card, enterId);
	}

	@Transactional
	public List<Member> checkMember(int enterId, String weixinId) {
		// TODO Auto-generated method stub
		return memberDao.checkMember(enterId, weixinId);
	}

	@Transactional
	public List<Member> checkMembers(int enterId, String businessId) {
		// TODO Auto-generated method stub
		return memberDao.checkMember(enterId, businessId);
	}

	@Transactional
	public List<Member> findByWeiInId(int enterId, String weiXinId) {
		return memberDao.findByWeiInId(enterId, weiXinId);

	}

	@Transactional
	public void delMember(String hql) {
		// TODO Auto-generated method stub
		memberDao.delMember(hql);
	}

	@Transactional
	public PageBean quliuyan(int enterId, int currentpage, int PAGE_SIZE,
			int memberIdint) {
		// TODO Auto-generated method stub
		return memberDao.quliuyan(enterId, currentpage, PAGE_SIZE, memberIdint);
	}

	@Override
	public List<Integrals> getListIntegrals(int id, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.getListIntegrals(id, enterId);
	}

	@Transactional
	public void addMessage(int enterId, Message message) {
		// TODO Auto-generated method stub
		memberDao.addMessage(enterId, message);
	}

	@Override
	public Integrals2 getOneIntegrals(int id, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.getOneIntegrals(id, enterId);
	}

	@Transactional
	public Message getmessagebyid(int enterId, int messageidint) {
		// TODO Auto-generated method stub
		return memberDao.getmessagebyid(enterId, messageidint);
	}

	@Transactional
	public Member find_by_member_memberid(int enterId, int member_id) {
		// TODO Auto-generated method stub
		return memberDao.find_by_member_memberid(enterId, member_id);
	}

	@Transactional
	public int add_storeRecod(String money, String recordtime, int memberid,
			String businessName, Double present_money, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.add_storeRecod(money, recordtime, memberid,
				businessName, present_money, enterId);
	}

	@Transactional
	public PageBean showMessage(int enterId, int start, int number, int memberId) {
		// TODO Auto-generated method stub
		return memberDao.showMessage(enterId, start, number, memberId);
	}

	@Transactional
	public List<Message> getByMessageId(int enterId, int messageId, int type) {
		// TODO Auto-generated method stub
		return memberDao.getByMessageId(enterId, messageId, type);
	}

	@Transactional
	public Message getByMessageIds(int enterId, int messageId, int type) {
		// TODO Auto-generated method stub
		return memberDao.getByMessageIds(enterId, messageId, type);
	}

	@Transactional
	public List<Activity> getActivity(int enterId, int id) {
		// TODO Auto-generated method stub
		return memberDao.getActivity(enterId, id);
	}

	@Transactional
	public PageBean getByIdActivity(int enterId, int start, int pageSize) {
		// TODO Auto-generated method stub
		return memberDao.getByIdActivity(enterId, start, pageSize);
	}

	@Override
	public PageBean spiltPageStorerecord(int enterpriseId, int curPage,
			int pageSize, int memberId) {
		return memberDao.spiltPageStorerecord(enterpriseId, curPage, pageSize,
				memberId);
	}

	@Override
	public List<Integrals> getFenye(int id, int enterId, int start, int number) {
		// TODO Auto-generated method stub
		return memberDao.getFenye(id, enterId, start, number);
	}

	@Override
	public Integrals getxiang(int id, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.getxiang(id, enterId);
	}

	@Transactional
	public List<Message> quhuifuliuyan(int enterId, Integer messageId) {
		// TODO Auto-generated method stub
		return memberDao.quhuifuliuyan(enterId, messageId);
	}

	@Override
	@Transactional
	public void getChange(int id, String xiangxidizhi, int nianling,
			String shoujihao, int xingbie, String xingming, int enterId) {
		// TODO Auto-generated method stub
		memberDao.getChange(id, xiangxidizhi, nianling, shoujihao, xingbie,
				xingming, enterId);
	}

	@Override
	public void getChangePassword(int id, int enterId, String ps) {
		// TODO Auto-generated method stub
		memberDao.getChangePassword(id, enterId, ps);
	}

	@Override
	public int getJifen(int id, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.getJifen(id, enterId);
	}

	@Override
	public int getChangejifen(int id, int enterId, int jifen) {
		// TODO Auto-generated method stub
		return memberDao.getChangejifen(id, enterId, jifen);
	}

	// 促销活动add
	@Transactional
	public void addActivity(int enterId, Activity activity) {
		// TODO Auto-generated method stub
		memberDao.addActivity(enterId, activity);
	}

	// 促销活动update
	@Transactional
	public void updateActivity(int enterId, Activity activity, int activityId) {
		// TODO Auto-generated method stub
		memberDao.updateActivity(enterId, activity, activityId);
	}

	@Transactional
	public void delactivity(int enterId, int activityidint) {
		// TODO Auto-generated method stub
		memberDao.delactivity(enterId, activityidint);
	}

	@Transactional
	public Activity getactivitybyid(int enterId, int activityidint) {
		// TODO Auto-generated method stub
		return memberDao.getactivitybyid(enterId, activityidint);
	}

	@Transactional
	public PageBean getByMemberId(int memberId, int enterId, int start,
			int pageSize) {
		// TODO Auto-generated method stub
		return memberDao.getByMemberId(memberId, enterId, start, pageSize);
	}

	@Transactional
	public List<Coupons> getCouponsById(int couponsId, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.getCouponsById(couponsId, enterId);
	}

	@Transactional
	public void getChangeGrade(int id, int enterId, int Grade) {
		// TODO Auto-generated method stub
		memberDao.getChangeGrade(id, enterId, Grade);
	}

	@Override
	public String chuzhi(int id, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.chuzhi(id, enterId);
	}

	@Override
	public int getChangechuzhi(int id, int enterId, String chuzhi) {
		// TODO Auto-generated method stub
		return memberDao.getChangechuzhi(id, enterId, chuzhi);
	}

	@Transactional
	public Storerecord findStorerecordByid(int id, int enterId) {
		// TODO Auto-generated method stub
		return memberDao.findStorerecordByid(id, enterId);
	}

	@Transactional
	@Override
	public void updateMember(String sql, int enterId) {
		// TODO Auto-generated method stub
		memberDao.updateMember(sql, enterId);
	}

	@Transactional
	@Override
	public List<MamberGrade> getAll(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return memberDao.getAll(sql, page, pageSize);
	}

	@Transactional
	@Override
	public void addGrade(String sql) {
		// TODO Auto-generated method stub
		memberDao.addGrade(sql);
	}

	@Transactional
	@Override
	public void updateGrade(String sql, int enterId) {
		// TODO Auto-generated method stub
		memberDao.updateGrade(sql, enterId);
	}

	@Transactional
	@Override
	public void deleteGrade(String sql) {
		// TODO Auto-generated method stub
		memberDao.deleteGrade(sql);
	}
	@Transactional
	@Override
	public MamberGrade getById(String sql) {
		// TODO Auto-generated method stub
		return memberDao.getById(sql);
	}
	@Transactional
	@Override
	public List<MamberGrade> getList(String sql) {
		// TODO Auto-generated method stub
		return memberDao.getList(sql);
	}

}
