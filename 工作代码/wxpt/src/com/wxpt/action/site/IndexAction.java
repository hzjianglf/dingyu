package com.wxpt.action.site;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.FileUploadBean;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Fans;
import com.wxpt.site.entity.FansImage;
import com.wxpt.site.entity.FansImageVisit;
import com.wxpt.site.entity.FansUser;
import com.wxpt.site.entity.pojo.FansImagePojo;
import com.wxpt.site.entity.pojo.FansPojo;
import com.wxpt.site.entity.pojo.FansUserPojo;
import com.wxpt.site.service.FansService;


@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
	  @Result(name = "keyword", type = "json", params = { "root", "result" }),
		@Result(name = "success", type = "json", params = { "root", "result" }),
		@Result(name = "one", location = "/templates/sheying/jingdian.jsp"),
		@Result(name = "two", location = "/templates/sheying/food.jsp"),
		@Result(name = "fansImage", location = "/templates/sheying/huodong.jsp")})
public class IndexAction extends ParentAction {

	//GetCookie cookies = new GetCookie();
	//int enterId = cookies.getCookie();
	@SuppressWarnings("unused")
	private String templateName;
	private String logoName;
	@SuppressWarnings("unused")
	private int menuID;
	private String menuName;
	private int menuNameID;
	private int type;
	private int pageID;
	// 推荐
	private String bannerName;
	private String msgContent;
	private String msgContacts;
	private String msgWay;

	private String fromUser;
	/*public String sheYing(){
		templateName = super.getEnterUserTemplate().getTemplates().getFolder();
		fansUserList = fansService.getFansUserList("from FansUser where fansUserId in ( select fansUser.fansUserId from FansImage order by fansImageId desc)");
		fansImageList = fansService.getList("from FansImage where checkState = 1");
		fansImageVisitList = fansService.getFansImageVisitList("from FansImageVisit where fansUserName = '" + fromUser + "' and fansVisitKey = 'key_zan' group by fansUser" );
		return "three";
	}*/

	public String huZan(){
		FansImageVisit fansImageVisit=null;//
		int state=0;
		List <FansImageVisit> flist = fansService
				.getFansImageVisitList("select * from wxpt"+enterId2+".fans_image_visit where fans_visit_key = 'key_zan' and fans_user_name = '"+ fromUser+ "' and fans_image_id = "+ fansImageId);
		   //System.out.println(fansImageVisit);
		   if(flist.size()>0){
			   fansImageVisit=flist.get(0);
			   if(fansImageVisit.getFansVisitValue().endsWith("1")){
				   fansService.getFansImageVisit(enterId2,"UPDATE wxpt"+enterId2+".`fans_image_visit` SET `fans_visit_key`='key_zan' , `fans_visit_value` = '0' where `fans_image_id` = " + fansImageId +" and `fans_user_name` = '" +fromUser +"'");
					state = 0; 
					//0是wei赞
				   
			   }else{
				   fansService.getFansImageVisit(enterId2,"UPDATE wxpt"+enterId2+".`fans_image_visit` SET `fans_visit_key`='key_zan' , `fans_visit_value` = '1' where `fans_image_id` = " + fansImageId +" and `fans_user_name` = '" +fromUser +"'");
					state = 1;
					//1是已赞
			   }
			   
			   
		   }else{
			   
			   FansImage fansImage =null;
				List<FansImage> finagelist= fansService.getList2("select * from wxpt"+enterId2+".fans_image where fans_image_id =" + fansImageId);		
				if(finagelist.size()!=0){
					System.out.println(fromUser);
					fansImage=finagelist.get(0);
					//FansImageVisit fansImageVisit = new FansImageVisit(fansImage.getFansUser(), fansImage, "key_zan", fromUser, "1");
					 fansImageVisit=new FansImageVisit();
					fansImageVisit.setFansVisitKey("key_zan");
					fansImageVisit.setFansVisitValue("1");
					fansImageVisit.setFansUser(fansImage.getFansUser());
					fansImageVisit.setFansImage(fansImage);
					fansImageVisit.setFansUserName(fromUser);
					fansImage.setFansImageId(fansImageId);
					fansService.addFansImageVisit(enterId2,fansImageVisit);
					state = 1;
				}
		   }
		
		
		super.out.print("{\"state\":\""+state+"\"}");
		super.out.flush();
		super.out.close();
		return "success";
		}
	

	
	
	

	JSONArray jsonls;
	int count;// 总记录数
	int rows;
	int page=1;
	int pageSize=5;
	int sumPage=1;
	private List<FansUser> SumUserList;
	private List<FansImage> fansImages;
	private List<FansImagePojo> fansImagePojos;
	private List<FansImageVisit> fansImageVisitList;
	private String nickname;
	private String signature;
	private int fansId;
	private String fansName;
	FansUser fansUser;
	Fans fans;
	private List<FansUser> fansUserLists;
	private List<FansUser> UserList;
	private List<FansUserPojo> fansUserList=new ArrayList<FansUserPojo>();
	private List<FansUserPojo> fansList=new ArrayList<FansUserPojo>();
	List<FansImage> fansImageList =new ArrayList<FansImage>();
	List<FansPojo> fanspojoList = new ArrayList<FansPojo>();
	private FileUploadBean bean = new FileUploadBean();
	TimeUtil time=new TimeUtil();
	File touxiang;
	FansService fansService;
	int enterId2;


	private int fansIamgeId;
	private int checkState;
	private String message;



private int fansUserId;
private int fansImageId;
public String getImageByUserId(){
	List<FansImageVisit> fanimageslist=null;
	String sql = "select *  from wxpt"+enterId2+".fans_image where fans_user_id	= " + fansUserId;
	fansImageList = fansService.getList2(sql);
	int num=0;
	for(int i=0;i<fansImageList.size();i++){
		if(fansImageList.get(i).getFansImageId()==fansImageId){
			num =i;
			break;
		}
	}
	List<FansImage>  l1 = fansImageList.subList(0, num);
	List<FansImage>  l2 = fansImageList.subList(num, fansImageList.size());
	List<FansImage> list = new ArrayList<FansImage>();
	list.addAll(l2);
	list.addAll(l1);
	fansImageList=list;
	StringBuffer sb = new StringBuffer();
	try {
		JsonConfig jsonConfig = new JsonConfig();		
		jsonConfig.setExcludes(new String[] { "fansImageVisits","fansUser" });
			jsonls = JSONArray.fromObject(fansImageList,jsonConfig);
			String str =jsonls.toString();
		//拼接json字符串
		for(int i=0;i<fansImageList.size();i++){
			String temp = "";
			int fasImageId =fansImageList.get(i).getFansImageId();
			String hql="select * from wxpt"+enterId2+".fans_image_visit where fans_user_name='"+fansName+"' and fans_image_id="+fasImageId;
			System.out.println(hql); 
			fanimageslist=fansService.getFansImageVisitList(hql);
			System.out.println(fanimageslist.size());
			temp =str.substring(0,str.indexOf("}"));				
			sb.append(temp);	
			if(fanimageslist.size()>0){
				String fans_visit_value=fanimageslist.get(0).getFansVisitValue();
				//String fansusername=fanimageslist.get(0).getFansUserName();
				int fansuserId=fanimageslist.get(0).getFansUser().getFansUserId();
				if(fans_visit_value.endsWith("1")){
					fans_visit_value="已赞";
				}
				if(fans_visit_value.endsWith("0")){
					fans_visit_value="赞";
				}
			
				 //sb.append(",\"fansusername\":\""+fansusername+"\"");
				 sb.append(",\"fansName\":\""+fansName+"\"");
				 sb.append(",\"state\":\""+fans_visit_value+"\"");
				 sb.append(",\"fansuserId\":\""+fansuserId+"\"");
			}else{
				//String fansusername=fansImageList.get(i).getFansUser().getFansUserName();
				int fansuserId=fansImageList.get(i).getFansUser().getFansUserId();
				String fans_visit_value="0";
				if(fans_visit_value.endsWith("1")){
					fans_visit_value="已赞";
				}
				if(fans_visit_value.endsWith("0")){
					fans_visit_value="赞";
				}
				// sb.append(",\"fansusername\":\""+fansusername+"\"");
				 sb.append(",\"fansName\":\""+fansName+"\"");
				sb.append(",\"state\":\""+fans_visit_value+"\"");
				sb.append(",\"fansuserId\":\""+fansuserId+"\"");
			}
			 
			sb.append("}");
			str =str.substring(str.indexOf("}")+1);
		}
		sb.append("]");
		
	   System.out.println(sb.toString()+"========");
	} catch (Exception e) {
		// TODO: handle exception
	}
	System.out.println(sb.toString());
	super.out.print(sb.toString());
	super.out.flush();
	super.out.close();
	return "keyword";
}


public int getEnterId2() {
	return enterId2;
}
public void setEnterId2(int enterId2) {
	this.enterId2 = enterId2;
}


	//获取fans、fansImage
	public String getFansAndFansImage(){
		System.out.println(enterId2);
		int zonghe=0;
		String sql="select * from  wxpt"+enterId2+".fans_user where 1=1";
		System.out.println(sql);
		SumUserList=fansService.getFansCount(sql);
		//UserList = fansService.getFansUserList2("select * from wxpt"+enterId2+".fans_user where fans_user_id in ( select fans_user_id from wxpt"+enterId2+".fans_image order by fans_image_id desc)",page, pageSize);
		SumUserList=fansService.getFansCount(sql);
		UserList = fansService.getFansUserList2(sql,page, pageSize);
		if(SumUserList.size()>0){
			sumPage=(SumUserList.size())/pageSize+1;
		}
		if(SumUserList.size()<=pageSize){
			sumPage=1;
		}
		for(int i=0;i<UserList.size();i++){
			int count=0;
			String sql1=" select * from wxpt"+enterId2+".fans_image_visit where fans_user_id = '"+UserList.get(i).getFansUserId()+"' and fans_visit_value=1 ";
			fansImageVisitList=fansService.getFansImageVisitList(sql1);
			if(fansImageVisitList==null){
				count=0;
			}else{
				count=fansImageVisitList.size();
			}
			FansUserPojo userPojo=new FansUserPojo();
			userPojo.setFansUserId(UserList.get(i).getFansUserId());
			userPojo.setFansUserName(UserList.get(i).getFansUserName());
			userPojo.setNickname(UserList.get(i).getNickname());
			userPojo.setSignature(UserList.get(i).getSignature());
			userPojo.setUpdateTime(UserList.get(i).getUpdateTime());
			userPojo.setTouxiang(UserList.get(i).getTouxiang());
			userPojo.setCount(count);
			fansList.add(userPojo);
		}
		System.out.println(page);
		System.out.println(rows);
		fansImageList = fansService.getList2("select * from wxpt"+enterId2+".fans_image where check_state = 1");
		fansImageVisitList = fansService.getFansImageVisitList("select * from  wxpt"+enterId2+".fans_image_visit where fans_user_name = '"+fansName+"' and fans_visit_key = 'key_zan' group by fans_user_id" );
		System.out.println(fansList.size());
		System.out.println(fansImageList.size());
		System.out.println(fansImageVisitList.size());
		System.out.println(fansName);
		//fansImageVisitList = fansService.getFansImageVisitList("from FansImageVisit where fansUserName = '"+fansName+"' and fansVisitKey = 'key_zan' " );
		return "fansImage";
	}

	
	public int getSumPage() {
		return sumPage;
	}
	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}
	public List<FansUser> getSumUserList() {
		return SumUserList;
	}
	public void setSumUserList(List<FansUser> sumUserList) {
		SumUserList = sumUserList;
	}
	public int getCheckState() {
		return checkState;
	}

	public void setCheckState(int checkState) {
		this.checkState = checkState;
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

	public int getFansIamgeId() {
		return fansIamgeId;
	}

	public void setFansIamgeId(int fansIamgeId) {
		this.fansIamgeId = fansIamgeId;
	}

	public String getNickname() {
		return nickname;
	}

	public List<FansUser> getFansUserLists() {
		return fansUserLists;
	}
	public void setFansUserLists(List<FansUser> fansUserLists) {
		this.fansUserLists = fansUserLists;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSignature() {
		return signature;
	}


	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getFansId() {
		return fansId;
	}

	public void setFansId(int fansId) {
		this.fansId = fansId;
	}

	public String getFansName() {
		return fansName;
	}

	public List<FansUserPojo> getFansUserList() {
		return fansUserList;
	}


	public void setFansUserList(List<FansUserPojo> fansUserList) {
		this.fansUserList = fansUserList;
	}


	public FansService getFansService() {
		return fansService;
	}


	public void setFansService(FansService fansService) {
		this.fansService = fansService;
	}


	public void setFansName(String fansName) {
		this.fansName = fansName;
	}

	public List<FansImage> getFansImages() {
		return fansImages;
	}

	public void setFansImages(List<FansImage> fansImages) {
		this.fansImages = fansImages;
	}

	public List<FansImagePojo> getFansImagePojos() {
		return fansImagePojos;
	}

	public void setFansImagePojos(List<FansImagePojo> fansImagePojos) {
		this.fansImagePojos = fansImagePojos;
	}

	public FansUser getFansUser() {
		return fansUser;
	}

	public void setFansUser(FansUser fansUser) {
		this.fansUser = fansUser;
	}



	public List<FansPojo> getFanspojoList() {
		return fanspojoList;
	}

	public void setFanspojoList(List<FansPojo> fanspojoList) {
		this.fanspojoList = fanspojoList;
	}

	

	public List<FansUserPojo> getFansList() {
		return fansList;
	}
	public void setFansList(List<FansUserPojo> fansList) {
		this.fansList = fansList;
	}

	public File getTouxiang() {
		return touxiang;
	}
	public void setTouxiang(File touxiang) {
		this.touxiang = touxiang;
	}
	public FileUploadBean getBean() {
		return bean;
	}
	public void setBean(FileUploadBean bean) {
		this.bean = bean;
	}
	public TimeUtil getTime() {
		return time;
	}
	public void setTime(TimeUtil time) {
		this.time = time;
	}

	public List<FansUser> getUserList() {
		return UserList;
	}
	public void setUserList(List<FansUser> userList) {
		UserList = userList;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}	



	

	public String zanEdit(){
		int state = 0;
		try {
			FansImageVisit fansImageVisit = fansService.getFansImageVisitList(
					"select * from wxpt"+enterId2+".fans_image_visit where fans_visit_key = 'key_zan' and fans_user_name = '"
							+ fromUser + "' and fans_image_id = "
							+ fansImageId).get(0);
			state = Integer.parseInt(fansImageVisit.getFansVisitValue());
		} catch (Exception e) {
			// TODO: handle exception
			state = 0;
		}
		super.out.print("{\"state\":\""+state+"\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}
	
	
	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgContacts() {
		return msgContacts;
	}

	public void setMsgContacts(String msgContacts) {
		this.msgContacts = msgContacts;
	}

	public String getMsgWay() {
		return msgWay;
	}

	public void setMsgWay(String msgWay) {
		this.msgWay = msgWay;
	}


	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}


	public int getMenuNameID() {
		return menuNameID;
	}

	public void setMenuNameID(int menuNameID) {
		this.menuNameID = menuNameID;
	}

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}


	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPageID() {
		return pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}




	public List<FansImage> getFansImageList() {
		return fansImageList;
	}

	public void setFansImageList(List<FansImage> fansImageList) {
		this.fansImageList = fansImageList;
	}

	public List<FansImageVisit> getFansImageVisitList() {
		return fansImageVisitList;
	}

	public void setFansImageVisitList(List<FansImageVisit> fansImageVisitList) {
		this.fansImageVisitList = fansImageVisitList;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public int getFansUserId() {
		return fansUserId;
	}

	public void setFansUserId(int fansUserId) {
		this.fansUserId = fansUserId;
	}

	public int getFansImageId() {
		return fansImageId;
	}

	public void setFansImageId(int fansImageId) {
		this.fansImageId = fansImageId;
	}
	
}
