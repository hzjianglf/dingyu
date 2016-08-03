package com.wxpt.action.site;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.wxpt.common.FileUploadBean;
import com.wxpt.site.dao.impl.EnterDaoImpl;

import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.pojo.EnterInforp;

import com.wxpt.site.service.EnterService;

@SuppressWarnings("serial")
public class ManagePayAction extends ParentAction {
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
	private String merId;
	private String curyId;
	private String transtType;
	private String version;
	private String gateId;
	private String privl;
	private String partner;
	private String sellerEmail;
	private String key;
	private String body;
	GetCookie cookies = new GetCookie();
	int enterId2 = cookies.getCookie();
	List<EnterInforp> enterList = new ArrayList<EnterInforp>();

	public String getYList() {
		String sql = "SELECT * FROM webchat.enter_infor where enter_infor_id="
				+ enterId2;
		List<EnterInfor> list = enterService.getAll(sql, page, rows);
		for (int i = 0; i < list.size(); i++) {
			EnterInforp ep = new EnterInforp();
			EnterInfor enter=new EnterInfor();
			enter=list.get(i);
			ep.setAppId(list.get(i).getAppId());
			System.out.println(list.get(i).getAppSecret());
			ep.setAppSecret(list.get(i).getAppSecret());
			ep.setAccessToken(list.get(i).getAccessToken());
			ep.setEmail(list.get(i).getEmail());
			System.out.println(list.get(i).getEnterAddress());
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
			ep.setBanenr("<img  width='180' height='180' src='/wxpt/web/images/"+enterId+"/"
					+ list.get(i).getBanner() + "' ///>");
			try {
				ep.setMerId(enter.getMerId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ep.setMerId("暂无配置");
			}
			try {
				ep.setCuryId(enter.getCuryId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ep.setCuryId("暂无配置");
			}
			try {
				ep.setTranstType(enter.getTranstType());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ep.setTranstType("暂无配置");
			}
			try {
				ep.setVersion(enter.getVersion());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ep.setVersion("暂无配置");
			}
			try {
				ep.setGateId(enter.getGateId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ep.setGateId("暂无配置");
			}
			try {
				ep.setPrivl(enter.getPrivl());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ep.setPrivl("暂无配置");
			}
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
	
	public String getZList() {
		String sql = "SELECT * FROM webchat.enter_infor where enter_infor_id="
				+ enterId2;
		List<EnterInfor> list = enterService.getAll(sql, page, rows);
		for (int i = 0; i < list.size(); i++) {
			EnterInforp ep = new EnterInforp();
			EnterInfor enter=new EnterInfor();
			enter=list.get(i);
			ep.setAppId(list.get(i).getAppId());
			System.out.println(list.get(i).getAppSecret());
			ep.setAppSecret(list.get(i).getAppSecret());
			ep.setAccessToken(list.get(i).getAccessToken());
			ep.setEmail(list.get(i).getEmail());
			System.out.println(list.get(i).getEnterAddress());
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
			ep.setBanenr("<img  width='180' height='180' src='/wxpt/web/images/"+enterId+"/"
					+ list.get(i).getBanner() + "' ///>");
			ep.setPartner(list.get(i).getPartner());
			ep.setKey(list.get(i).getKey());
			ep.setSellerEmail(list.get(i).getSellerEmail());
			ep.setBody(list.get(i).getBody());
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


	// 更新
	private File merKey;
	private String messages; 
	private File pgpubk;
	public String update() {
		try {
			System.out.println(enterId);
			String merKeyname="merKey"+enterId+".key";
			String pgpubkName="pgpubk"+enterId+".key";
			FileUploadBean bean=new FileUploadBean();
			bean.upLoad(merKeyname, merKey);
			bean.upLoad(pgpubkName, pgpubk);
			EnterInfor enterInfor = enterService.getById(Integer.parseInt(enterId));
			enterInfor.setMerId(merId);
			enterInfor.setCuryId(curyId);
			enterInfor.setTranstType(transtType);
			enterInfor.setVersion(version);
			enterInfor.setGateId(gateId);
			enterInfor.setPrivl(privl);
			EnterDaoImpl ss = new EnterDaoImpl();
			String sql = "UPDATE webchat.enter_infor SET mer_id='"
					+enterInfor.getMerId()+"',cury_id='"
					+enterInfor.getCuryId()+"',transt_type='"
					+enterInfor.getTranstType()+"',version='"
					+enterInfor.getVersion()+"',gate_id='"
					+enterInfor.getGateId()+"',privl='"
					+enterInfor.getPrivl()+"' WHERE `enter_infor_id`="
					+ enterInfor.getEnterInforId() + "";
			ss.updateEnter(sql);
			// enterService.updateEnter(enterInfor);
			//return "success";
			messages="修改成功！";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			messages="修改失败！";
			return ERROR;
		}
		return SUCCESS;
	}

	public void updateZ() {
		System.out.println(enterId);
		EnterInfor enterInfor = enterService.getById(Integer.parseInt(enterId));
		enterInfor.setPartner(partner);
		enterInfor.setKey(key);
		enterInfor.setSellerEmail(sellerEmail);
		enterInfor.setBody(body);
		EnterDaoImpl ss = new EnterDaoImpl();
		String sql = "UPDATE webchat.enter_infor SET `partner`='"
				+enterInfor.getPartner()+"',`key`='"
				+enterInfor.getKey()+"',`seller_email`='"
				+enterInfor.getSellerEmail()+"',`body`='"
				+enterInfor.getBody()+"' WHERE `enter_infor_id`="
				+ enterInfor.getEnterInforId() + "";
		ss.updateEnter(sql);
		// enterService.updateEnter(enterInfor);
		//return "success";
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

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getCuryId() {
		return curyId;
	}

	public void setCuryId(String curyId) {
		this.curyId = curyId;
	}

	public String getTranstType() {
		return transtType;
	}

	public void setTranstType(String transtType) {
		this.transtType = transtType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	public String getPrivl() {
		return privl;
	}

	public void setPrivl(String privl) {
		this.privl = privl;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public File getMerKey() {
		return merKey;
	}

	public void setMerKey(File merKey) {
		this.merKey = merKey;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public File getPgpubk() {
		return pgpubk;
	}

	public void setPgpubk(File pgpubk) {
		this.pgpubk = pgpubk;
	}

}
