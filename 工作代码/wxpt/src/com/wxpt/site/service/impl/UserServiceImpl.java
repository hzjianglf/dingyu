package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.UserDao;
import com.wxpt.site.entity.Keywordexplicit;
import com.wxpt.site.entity.Keywords;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.User;
import com.wxpt.site.entity.UserCount;
import com.wxpt.site.entity.Vote;
import com.wxpt.site.entity.pojo.KeyListPojo;
import com.wxpt.site.entity.pojo.UserPojo;
import com.wxpt.site.service.UserService;

public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Transactional
	@Override
	public void saveUser(int enterId, String userName, String userNum) {
		// TODO Auto-generated method stub
		userDao.saveUser(enterId, userName, userNum);
	}

	@Transactional
	public User getUserByNum(String userNum) {
		return userDao.getUserByNum(userNum);
	}

	@Transactional
	@Override
	public List<UserPojo> getUserPojo(String paixu) {
		// TODO Auto-generated method stub
		return userDao.getUserPojo(paixu);
	}

	@Transactional
	@Override
	public List<UserCount> getUserCountList(int userId) {
		// TODO Auto-generated method stub
		return userDao.getUserCountList(userId);
	}

	@Transactional
	@Override
	public UserCount getUserCountBySend(int enterId, String sendName) {
		// TODO Auto-generated method stub
		return userDao.getUserCountBySend(enterId, sendName);
	}

	@Transactional
	@Override
	public void saveUserCount(int enterId, String time, String forUser,
			int userId) {
		// TODO Auto-generated method stub
		userDao.saveUserCount(enterId, time, forUser, userId);
	}

	@Transactional
	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return userDao.getUserList();
	}

	@Transactional
	@Override
	public User getUserByName(int enterId, String userName) {
		// TODO Auto-generated method stub
		return userDao.getUserByName(enterId, userName);
	}

	@Transactional
	@Override
	public void saveKeywords(int enterId, Keywords keywords) {
		// TODO Auto-generated method stub
		System.out.println(keywords.getWordCount());
		userDao.saveKeywords(enterId, keywords);
	}

	@Transactional
	@Override
	public Keywords getMaxkeywords(int enterId) {
		// TODO Auto-generated method stub
		return userDao.getMaxkeywords(enterId);
	}

	@Transactional
	@Override
	public void saveKeywordsexplicit(int enterId,
			Keywordexplicit keywordexplicit) {
		// TODO Auto-generated method stub
		userDao.saveKeywordsexplicit(enterId, keywordexplicit);
	}

	@Transactional
	public Keywords getKeyBysendContent(int enterId, String keywordcontent) {
		return userDao.getKeyBysendContent(enterId, keywordcontent);
	}

	@Transactional
	@Override
	public List<Keywordexplicit> getkExplicitByKeyID(int enterId, int keywordsID) {
		// TODO Auto-generated method stub
		return userDao.getkExplicitByKeyID(keywordsID, enterId);
	}

	@Transactional
	@Override
	public List<Keywords> getkeyList(int enterId) {
		// TODO Auto-generated method stub
		return userDao.getkeyList(enterId);
	}

	@Transactional
	@Override
	public Keywords getByID(int enterId, int keywordsid) {
		// TODO Auto-generated method stub
		return userDao.getByID(enterId, keywordsid);
	}

	@Transactional
	@Override
	public List<Keywordexplicit> getkExplicitByEkey(int enterId,
			int keywordsID, String eKey, int type) {
		// TODO Auto-generated method stub
		return userDao.getkExplicitByEkey(enterId, keywordsID, eKey, type);
	}

	@Transactional
	@Override
	public List<KeyListPojo> keyListPojo() {
		// TODO Auto-generated method stub
		return userDao.keyListPojo();
	}

	@Transactional
	public List<UserPojo> getSqlPaixu(String sql) {
		return userDao.getSqlPaixu(sql);
	}

	@Transactional
	@Override
	public void updateKeyword(int keywordid, String keywordsContent) {
		// TODO Auto-generated method stub
		userDao.updateKeyword(keywordid, keywordsContent);
	}

	@Transactional
	@Override
	public void deleteSql(int enterId, String ids) {
		// TODO Auto-generated method stub
		userDao.deleteSql(enterId, ids);
	}

	@Transactional
	@Override
	public void deleteKeyBySql(int id) {
		// TODO Auto-generated method stub
		userDao.deleteKeyBySql(id);
	}

	@Transactional
	@Override
	public Keywordexplicit getkExplicitBy(int enterId, int explicitiD) {
		// TODO Auto-generated method stub
		return userDao.getkExplicitBy(enterId, explicitiD);
	}

	@Transactional
	@Override
	public List<User> getList(String ids) {
		// TODO Auto-generated method stub
		return userDao.getList(ids);
	}

	@Transactional
	@Override
	public int getUserCountById(int userId) {
		// TODO Auto-generated method stub
		return userDao.getUserCountById(userId);
	}

	@Transactional
	@Override
	public List<UserCount> getUserCounts(int enterId) {
		// TODO Auto-generated method stub
		return userDao.getUserCounts(enterId);
	}

	@Transactional
	@Override
	public List<UserCount> getUserCounts(int enterId, String sendUsers) {
		// TODO Auto-generated method stub
		return userDao.getUserCounts(enterId, sendUsers);
	}

	@Transactional
	@Override
	public List<UserPojo> getUserPojo(int enterId, String paixu, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		return userDao.getUserPojo(enterId, paixu, pageNo, pageSize);
	}

	@Override
	public List<UserCount> getUserCount(int enterId, String ids) {
		// TODO Auto-generated method stub
		return userDao.getUserCount(enterId, ids);
	}

	@Transactional
	@Override
	public List<User> getUserList(String sql) {
		// TODO Auto-generated method stub
		return userDao.getUserList(sql);
	}

	@Transactional
	@Override
	public int getUserCountById(String sql) {
		// TODO Auto-generated method stub
		return userDao.getUserCountById(sql);
	}

	@Transactional
	@Override
	public List<Keywordexplicit> getkExplicitByEkey(int enterId,
			int keywordsID, int type) {
		// TODO Auto-generated method stub
		return userDao.getkExplicitByEkey(enterId, keywordsID, type);
	}

	@Transactional
	@Override
	public ManageUser checkLogin(int enterId, String uname, String password) {
		// TODO Auto-generated method stub
		return userDao.checkLogin(enterId, uname, password);
	}

	@Transactional
	@Override
	public ManageUser getManageUserByName(int enterId, String userName) {
		// TODO Auto-generated method stub
		return userDao.getManageUserByName(enterId, userName);
	}

	@Transactional
	@Override
	public void update(int enterId, String sql) {
		// TODO Auto-generated method stub
		userDao.update(enterId, sql);
	}

	@Transactional
	@Override
	public List<Keywordexplicit> getKeyList(int enterId, int page, int rows) {
		// TODO Auto-generated method stub
		return userDao.getKeyList(enterId, page, rows);
	}

	@Transactional
	@Override
	public List<Keywords> getBykeyList(int enterId, int page, int rows) {
		// TODO Auto-generated method stub
		return userDao.getBykeyList(enterId, page, rows);
	}

	@Transactional
	@Override
	public List<User> getUserList(int page, int rows, String hql) {
		// TODO Auto-generated method stub
		return userDao.getUserList(page, rows, hql);
	}

	@Transactional
	@Override
	public User getUserByID(int enterId, int userId) {
		// TODO Auto-generated method stub
		return userDao.getUserByID(enterId, userId);
	}

	@Transactional
	@Override
	public void deleteByUserID(int enterId, int userId) {
		// TODO Auto-generated method stub
		userDao.deleteByUserID(enterId, userId);
	}

	@Transactional
	@Override
	public List<UserCount> getUserCountList(int enterId, int page, int rows,
			int userId) {
		// TODO Auto-generated method stub
		return userDao.getUserCountList(enterId, page, rows, userId);
	}

	@Override
	public int getKeyWordId(int enterId, int explicitiD) {
		// TODO Auto-generated method stub
		return userDao.getKeyWordId(enterId, explicitiD);
	}

	@Transactional
	@Override
	public void updateKeywordsexplicit(int enterId,
			Keywordexplicit keywordexplicit) {
		// TODO Auto-generated method stub
		userDao.updateKeywordsexplicit(enterId, keywordexplicit);
	}

	@Transactional
	@Override
	public int getuserCountCount(String sql) {
		// TODO Auto-generated method stub
		return userDao.getuserCountCount(sql);
	}

	@Override
	@Transactional
	public void saveVote(int enterId, String name) {
		// TODO Auto-generated method stub
		userDao.saveVote(enterId, name);
	}

	@Override
	@Transactional
	public List<Vote> getAll(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return userDao.getAll(sql, page, rows);
	}

	@Transactional
	@Override
	public void updateVote(int enterId, Vote vote) {
		// TODO Auto-generated method stub
		userDao.updateVote(enterId, vote);
	}

	@Transactional
	@Override
	public Vote getByVoteId(String sql) {
		// TODO Auto-generated method stub
		return userDao.getByVoteId(sql);
	}

	@Transactional
	@Override
	public void deleteVote(String sql) {
		// TODO Auto-generated method stub
		userDao.deleteVote(sql);
	}

	@Override
	@Transactional
	public List<Vote> getAll(int enterId) {
		// TODO Auto-generated method stub
		return userDao.getAll(enterId);
	}

	@Transactional
	@Override
	public List<Vote> getAll(String sql) {
		// TODO Auto-generated method stub
		return userDao.getAll(sql);
	}

	@Transactional
	public List<Keywordexplicit> getkExplicitByEkey(Integer keywordId, int type) {
		// TODO Auto-generated method stub
		return userDao.getkExplicitByEkey(keywordId, type);
	}

	@Override
	@Transactional
	public int getRecordId(int enterId, int laId) {
		// TODO Auto-generated method stub
		return userDao.getRecordId(enterId, laId);
	}

	@Override
	@Transactional
	public ManageUser getManageUserById(int enterId, int userid) {
		// TODO Auto-generated method stub
		return userDao.getManageUserById(enterId, userid);
	}

	@Override
	@Transactional
	public void deleteKeywords(int enterId, int keywordsID) {
		// TODO Auto-generated method stub
		userDao.deleteKeywords(enterId, keywordsID);

	}

	@Override
	public ManageUser getByEnterId(String sql) {
		// TODO Auto-generated method stub
		return userDao.getByEnterId(sql);
	}

	@Transactional
	@Override
	public List<ManageUser> getAllUser(String sql) {
		// TODO Auto-generated method stub
		return userDao.getAllUser(sql);
	}
	@Transactional
	@Override
	public void updateUser(String sql) {
		// TODO Auto-generated method stub
		userDao.updateUser(sql);
	}

}
