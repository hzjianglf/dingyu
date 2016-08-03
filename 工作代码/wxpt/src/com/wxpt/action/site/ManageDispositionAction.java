package com.wxpt.action.site;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.wxpt.common.CreateDatabase;
import com.wxpt.common.Md5;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.impl.EnterDaoImpl;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.pojo.EnterInforp;
import com.wxpt.site.service.EnterService;
@Results({
	@Result(name="success", location="/web/success.jsp")
	
})
@SuppressWarnings("serial")
public class ManageDispositionAction extends ParentAction {
	JSONArray jsonls;
	int count;// 总记录数
	int rows;
	int page;
	EnterService enterService;
	private String enterId;
	private String queryName;
	private String startTime;
	private String endTime;
	private String userName;
	private String enterName;
	private String enter;
	private String url;
	private String token;
	private String phone;
	private String email;
	private String address;
	private String appSecret;
	private String appId;
	int size;
	int enId;
	GetCookie cookies = new GetCookie();
	int enterId2 = cookies.getCookie();
	List<EnterInforp> enterList = new ArrayList<EnterInforp>();
	List<EnterInfor> list = new ArrayList<EnterInfor>();
	EnterInfor enterInfor;
	String qyId;
	Md5 md5=new Md5();
	TimeUtil time=new TimeUtil();
	String sql = "SELECT * FROM webchat.enter_infor where enter_infor_id="
			+ enterId2;

	public String getList() {
		list = enterService.getAll(sql, page, rows);
		for (int i = 0; i < list.size(); i++) {
			EnterInforp ep = new EnterInforp();
			ep.setAppId(list.get(i).getAppId());
			ep.setAppSecret(list.get(i).getAppSecret());
			ep.setAccessToken(list.get(i).getAccessToken());
			ep.setEmail(list.get(i).getEmail());
			ep.setEnterAddress(list.get(i).getEnterAddress());
			ep.setEnterInforId(list.get(i).getEnterInforId());
			ep.setEnterName(list.get(i).getEnterName());
			ep.setEnterPerson(list.get(i).getEnterPerson());
			ep.setEnterPhone(list.get(i).getEnterPhone());
			ep.setPowerCode(list.get(i).getPowerCode());
			ep.setPowerTime(list.get(i).getPowerTime());
			ep.setRegistTime(list.get(i).getRegistTime());
			ep.setState(list.get(i).getState());
			ep.setToken(list.get(i).getToken());
			ep.setUrl(list.get(i).getUrl());
			ep.setUserName(list.get(i).getUserName());
			ep.setBanenr("<img  width='180' height='180' src='/wxpt/web/images/"+enterId2+"/"
					+ list.get(i).getBanner() + "' ///>");
			enterList.add(ep);
		}

		try {
			JsonConfig jsonConfig = new JsonConfig();
			// jsonConfig.setExcludes(new String[] {"userTemplates"});
			jsonls = JSONArray.fromObject(enterList);
			// jsonls = JSONArray.fromObject(list.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		count = enterService.enterCount(sql);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	// 获取子企业信息
	public void getQyList() {
		enterInfor = enterService.getById2(enId);
		if (enterInfor == null) {

		} else {
			qyId = enterInfor.getChilendQyId();
			System.out.println(qyId);
			if (qyId == null || qyId.equals("")||qyId.equals("null")) {
				System.out.println("没有子企业");
			} else {
				String[] qy = qyId.split(";");
				for (int i = 0; i < qy.length; i++) {
					enterInfor = enterService.getById2(Integer.parseInt(qy[i]));
					list.add(enterInfor);
				}
				for (int i = 0; i < list.size(); i++) {
					EnterInforp ep = new EnterInforp();
					ep.setAppId(list.get(i).getAppId());
					ep.setAppSecret(list.get(i).getAppSecret());
					ep.setAccessToken(list.get(i).getAccessToken());
					ep.setEmail(list.get(i).getEmail());
					ep.setEnterAddress(list.get(i).getEnterAddress());
					ep.setEnterInforId(list.get(i).getEnterInforId());
					ep.setEnterName(list.get(i).getEnterName());
					ep.setEnterPerson(list.get(i).getEnterPerson());
					ep.setEnterPhone(list.get(i).getEnterPhone());
					ep.setPowerCode(list.get(i).getPowerCode());
					ep.setPowerTime(list.get(i).getPowerTime());
					ep.setRegistTime(list.get(i).getRegistTime());
					ep.setState(list.get(i).getState());
					ep.setToken(list.get(i).getToken());
					ep.setUrl(list.get(i).getUrl());
					ep.setUserName(list.get(i).getUserName());
					ep.setBanenr("<img  width='180' height='180' src='/wxpt/web/images/"+enterId2+"/"
							+ list.get(i).getBanner() + "' ///>");
					enterList.add(ep);
				}

			}
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			// jsonConfig.setExcludes(new String[] {"userTemplates"});
			jsonls = JSONArray.fromObject(enterList);
			// jsonls = JSONArray.fromObject(list.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		count = enterList.size();
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
	}

	//添加子企业
	public void addQy(){
		String sql="INSERT INTO webchat.enter_infor(`user_name`, `enter_name`,  `enter_phone`, `enter_address`, `regist_time`, `email`, `token`,`chilend_qy_id`,  `state`, `appSecret`, `appId`, `banner`) VALUES" +
				"('"+userName+"','"+enter+"','"+phone+"','"+address+"','"+time.getTime()+"','"+email+"','"+token+"','null',0,'"+appSecret+"','"+appId+"','')";
		enterService.addQyPeiZhi(sql);
		enterInfor = enterService.getById2(enId);
		String chileId=enterInfor.getChilendQyId();
		String select="SELECT * FROM webchat.enter_infor WHERE `user_name`='"+userName+"'";
		enterInfor=enterService.getByEnterInfor(select).get(0);
		if(chileId==null||chileId.equals("null")||chileId=="null"){
			chileId=enterInfor.getEnterInforId()+";";
		}else{
			chileId+=enterInfor.getEnterInforId()+";";
		}
		
		String url="http://www.uniqyw.com/wxpt/site/wei-xin-wxpt!valid?enterId="+enterInfor.getEnterInforId();
		String update0="UPDATE webchat.enter_infor SET `url`='"+url+"' WHERE `enter_infor_id`="+enterInfor.getEnterInforId();
		enterService.updateVshopBanenr(update0);
		String update="UPDATE webchat.enter_infor SET `chilend_qy_id`='"+chileId+"' WHERE `enter_infor_id`="+enId;
		enterService.updateVshopBanenr(update);
		String insert="INSERT INTO webchat.manage_user(`manage_user_name`, `passwrod`, `user_type`, `register_time`, `user_parent_id`, `role_list`, `status`, `enterid`) VALUES" +
				" ('"+userName+"','"+md5.GetMd5("123456")+"',1,'"+time.getTime()+"',0,1,0,"+enterInfor.getEnterInforId()+")";
		enterService.addQyPeiZhi(insert);
		//创建数据库
		CreateDatabase Data=new CreateDatabase();
		Data.createDateBase("wxpt"+enterInfor.getEnterInforId());
	}
	
	//判断用户名是否存在
	public String  panduan(){
		String select="SELECT * FROM webchat.enter_infor WHERE `user_name`='"+userName+"'";
		list=enterService.getByEnterInfor(select);
		System.out.println(list.size());
		if(list.size()==0){
			size=0;
			return "success";
		}else{
			size=1;
			return "success";
		}
	}
	
	/*public void deleteChile(){
		System.out.println(enId);
		enterService.deleEnter(enId);
	}
	public String delete() {
		String ids[] = enterId.split(",");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
			enterService.deleEnter(Integer.parseInt(ids[i]));
		}
		return "success";
	}*/
/*private String userName;
private String enter;
private String token;
private String phone;
private String email;
private String address;
private String appSecret;
private String appId;*/
	// 更新
	public String update() {
		System.out.println(enterId);
		EnterInfor enterInfor = enterService.getById(Integer.parseInt(enterId));
		enterInfor.setToken(token);
		enterInfor.setAppId(appId);
		enterInfor.setAppSecret(appSecret);
		EnterDaoImpl ss = new EnterDaoImpl();
		String sql = "UPDATE webchat.enter_infor SET `token`='"+token+"',`appSecret`='"+appSecret+"',`appId`='"+appId+"' WHERE `enter_infor_id`="
				+ enterInfor.getEnterInforId() + "";
		ss.updateEnter(sql);
		// enterService.updateEnter(enterInfor);
		return "success";
	}
	public String update2(){

		System.out.println(enterId);
		EnterInfor enterInfor = enterService.getById(Integer.parseInt(enterId));
		enterInfor.setToken(token);
		enterInfor.setAppId(appId);
		enterInfor.setAppSecret(appSecret);
		EnterDaoImpl ss = new EnterDaoImpl();
		String sql = "UPDATE webchat.enter_infor SET `user_name`='"+userName+"',`enter_name`='"+enter+"',`enter_phone`='"+phone+"',`enter_address`='"+address+"'," +
				"`email`='"+email+"',`token`='"+token+"', `appId`='"+appId+
				"',`appSecret`='"+appSecret+"' WHERE `enter_infor_id`="+enterInfor.getEnterInforId()+"";
		ss.updateEnter(sql);
		// enterService.updateEnter(enterInfor);
		return "success";
	
	}

	public EnterService getEnterService() {
		return enterService;
	}

	public void setEnterService(EnterService enterService) {
		this.enterService = enterService;
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

	public String getEnterId() {
		return enterId;
	}

	public void setEnterId(String enterId) {
		this.enterId = enterId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEnterName() {
		return enterName;
	}

	public void setEnterName(String enterName) {
		this.enterName = enterName;
	}

	public String getEnter() {
		return enter;
	}

	public void setEnter(String enter) {
		this.enter = enter;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<EnterInforp> getEnterList() {
		return enterList;
	}

	public void setEnterList(List<EnterInforp> enterList) {
		this.enterList = enterList;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getEnId() {
		return enId;
	}

	public void setEnId(int enId) {
		this.enId = enId;
	}

}
