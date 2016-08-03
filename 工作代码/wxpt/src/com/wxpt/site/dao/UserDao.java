package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.Keywordexplicit;
import com.wxpt.site.entity.Keywords;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.User;
import com.wxpt.site.entity.UserCount;
import com.wxpt.site.entity.Vote;
import com.wxpt.site.entity.pojo.KeyListPojo;
import com.wxpt.site.entity.pojo.UserPojo;

public interface UserDao {
	public void saveUser(int enterId,String userName,String userNum);
	public void saveVote(int enterId,String name);
	public List<Vote> getAll(String sql,int page, int rows) ;
	public List<Vote> getAll(String sql) ;
	public void updateVote(int enterId,Vote vote);
	public Vote getByVoteId(String sql);
	public void deleteVote(String sql);
	public List<Vote> getAll(int enterId);
	public User getUserByNum(String userNum);
	public List<UserPojo> getUserPojo(String paixu);
	public List<UserCount> getUserCountList(int userId);
	public UserCount getUserCountBySend(int enterId,String sendName);
	public List<User> getUserList();
	public User getUserByName(int enterId,String userName);
	public void saveKeywords(int enterId,Keywords keywords);
	public Keywords getMaxkeywords(int enterId);
	public void saveKeywordsexplicit(int enterId,Keywordexplicit keywordexplicit);
	public Keywords getKeyBysendContent(int enterId,String keywordcontent);
	public List<Keywordexplicit> getkExplicitByKeyID(int keywordsID,int enterId);
	public void deleteKeywords(int enterId,int keywordsID);
	public List<Keywords> getkeyList(int enterId); 
	public Keywords getByID(int enterId,int keywordsid);
	public void saveUserCount(int enterId,String time,String forUser,int userId);
	public List<Keywordexplicit> getkExplicitByEkey(int enterId,int keywordsID,String eKey,int type);
	public List<KeyListPojo> keyListPojo ();
	public List<UserPojo> getSqlPaixu(String sql);
	public void updateKeyword(int keywordid,String keywordsContent);
	public void deleteSql(int enterId,String ids);
	public void deleteKeyBySql(int id);
	public Keywordexplicit getkExplicitBy(int enterId,int explicitiD);
	public List<User> getList(String ids);
	public int getUserCountById(int userId);
	public List<UserCount> getUserCounts(int enterId);
	public List<UserCount> getUserCounts(int enterId,String sendUsers);
	public List<UserCount> getUserCount(int enterId,String ids);
	public List<UserPojo> getUserPojo(int enterId,String paixu,int pageNo,int pageSize);
	public List<User> getUserList(String sql);
	public int getUserCountById(String sql);
	public List<Keywordexplicit> getkExplicitByEkey(int enterId,int keywordsID,int type);
	public ManageUser checkLogin(int enterId,String uname, String password);
	
	public ManageUser getManageUserByName(int enterId,String userName);
	public ManageUser getManageUserById(int enterId,int userid);
	
	
	public void update(int enterId,String sql);
	public List<Keywordexplicit> getKeyList(int enterId,int page, int rows);
	public List<Keywords> getBykeyList(int enterId,int page, int rows);
	public List<User> getUserList(int page, int rows,String hql);
	public User getUserByID(int enterId,int userId);
	public void deleteByUserID(int enterId,int userId);
	public List<UserCount> getUserCountList(int enterId,int page, int rows, int userId);
	//
	public int getKeyWordId(int enterId,int explicitiD);
	public void updateKeywordsexplicit(int enterId,Keywordexplicit keywordexplicit);
	public int getuserCountCount(String sql);
	public List<Keywordexplicit> getkExplicitByEkey(Integer keywordId, int type);
	public int  getRecordId(int enterId,int laId);
	
	public ManageUser getByEnterId(String sql);
	
	//获取全部用户
	public List<ManageUser> getAllUser(String sql);
	
	//更新用户
	public void updateUser(String sql);
	
}
