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
		@Result(name = "success", location = "success.jsp") ,
		@Result(name = "fansImage", location = "/templates/sheying/huodong.jsp")})
public class ManageSheyingAction extends ParentAction {
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
	@Autowired
	FansService fansService;
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();
	int enterId2;
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		fansImages = fansService.getList("select * from wxpt"+enterId+".fans_image order by fans_image_id desc",page, rows);
		fansImagePojos = new ArrayList<FansImagePojo>();
		for (int i=0;i< fansImages.size();i++) {
			String state = "";
			if (fansImages.get(i).getCheckState()==0) {
				state = "未审核";
			} else {
				state = "已审核";
			}
			FansImagePojo fansImagePojo = new FansImagePojo(
					fansImages.get(i).getFansImageId(), fansImages.get(i).getFansUser()
							.getFansUserId(), "<img src=\""
							+ fansImages.get(i).getFansImageValue()
							+ "\" style=\"width:400px ; height:300px ;\"/>",
							fansImages.get(i).getImageUpdateTime(), state);
			fansImagePojo.setState(fansImages.get(i).getCheckState());
			fansImagePojos.add(fansImagePojo);
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "fansImageVisits","fansUser" });
			jsonls = JSONArray.fromObject(fansImagePojos, jsonConfig);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		count = fansService.getFansImageCount("select * from wxpt"+enterId+".fans_image");
		
		System.out.println(count);
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	/**
	 * ( 审核
	 * 
	 * @return
	 */
	private int fansIamgeId;
	private int checkState;
	private String message;

	public String check() {
		if (checkState == 0) {
			checkState = 1;
		} else {
			checkState = 0;
		}
		String hql = "UPDATE wxpt"+enterId+".`fans_image` SET `check_state`= " + checkState
				+ " WHERE `fans_image_id` = " + fansIamgeId;
		int result = fansService.getupdateFansImage(enterId,hql);
		System.out.println(result);
		if (result == 0) {
			message = "审核失败!";
		} else {
			if(checkState==0){
				message = "已取消审核成功!";
			}else if(checkState==1)
			message = "审核成功!";
		}
		super.out.print("{\"message\":\"" + message + "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}
	// 获取所有参与摄影的fans
	public void getFas() {
		String touxiang="";
		String sql="select * from  wxpt"+enterId+".fans_user where 1=1";
		//String sql ="select * from wxpt"+enterId+".fans_user where fans_user_id in ( select fans_user_id from wxpt"+enterId+".fans_image order by fans_image_id desc)";
		System.out.println(sql);
		fansUserLists = fansService.getAllFans(sql, page, rows);
		for(int i=0;i<fansUserLists.size();i++){
			int zonghe=0;
			System.out.println(fansUserLists.get(i).getFansUserId());
			String sql1=" select * from wxpt"+enterId+".fans_image_visit where fans_user_id = '"+fansUserLists.get(i).getFansUserId()+"' and fans_visit_value=1 ";
			fansImageVisitList=fansService.getFansImageVisitList(sql1);
			if(fansImageVisitList==null){
				zonghe=0;
			}else{
				zonghe=fansImageVisitList.size();
			}
			FansUserPojo userPojo=new FansUserPojo();
			userPojo.setFansUserName(fansUserLists.get(i).getFansUserName());
			userPojo.setNickname(fansUserLists.get(i).getNickname());
			userPojo.setSignature(fansUserLists.get(i).getSignature());
			userPojo.setUpdateTime(fansUserLists.get(i).getUpdateTime());
			//touxiang ="<img  width='80' height='86' src='..\\\\..\\web\\images\\"+fansUserLists.get(i).getTouxiang()+"' ///>";
			touxiang="<img  src=\"../../wxpt/web/images/"+enterId+"/"	+ fansUserLists.get(i).getTouxiang()+ "\" width='80' height='86' />";	
			userPojo.setTouxiang(touxiang);
			userPojo.setCount(zonghe);
			fansUserList.add(userPojo);
		}
		
		if (fansUserLists == null) {
			count=0;
		}else{
			count = fansService.getFansCount(sql).size();
		}
		try {
			
			  /*JsonConfig jsonConfig = new JsonConfig();
			  jsonConfig.setExcludes(new String[]{"fansImageVisits","fansImages"});*/
			 
			jsonls = JSONArray.fromObject(fansUserList);
		} catch (Exception e) {
			// TODO: handle exception
		}
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
	}
//修改昵称   个性签名
	public void updateFans(){
		fansUser=fansService.getFansUserByName(enterId,fansName);
		fansUser.setNickname(nickname);
		fansUser.setSignature(signature);
		fansService.updateFansUser(enterId,fansUser);
	}
public String  sheTouXiang(){
	String zhuangtai="-1";
	fansUser=fansService.getFansUserByName(enterId,fansName);
	try {
		if(touxiang!=null){
			bean.upLoadEnterId(enterId,fansName+".jpg",touxiang);
			fansUser.setTouxiang(fansName+".jpg");
			fansService.updateFansUser(enterId,fansUser);
			zhuangtai="1";
		}else{
			zhuangtai="0";
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		zhuangtai="-1";
	}
	return zhuangtai;
}


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
public int getFansImageId() {
	return fansImageId;
}

public void setFansImageId(int fansImageId) {
	this.fansImageId = fansImageId;
}

public int getFansUserId() {
	return fansUserId;
}

public void setFansUserId(int fansUserId) {
	this.fansUserId = fansUserId;
}

	//获取fans、fansImage
	public String getFansAndFansImage(){
		System.out.println(enterId2);
		int zonghe=0;
		String sql="select * from  wxpt"+enterId2+".fans_user where 1=1";
		SumUserList=fansService.getFansCount(sql);
		UserList = fansService.getFansUserList2("select * from wxpt"+enterId2+".fans_user where fans_user_id in ( select fans_user_id from wxpt"+enterId2+".fans_image order by fans_image_id desc)",page, pageSize);
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

	
	public int getEnterId() {
		return enterId;
	}
	public void setEnterId(int enterId) {
		this.enterId = enterId;
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

	public List<FansImage> getFansImageList() {
		return fansImageList;
	}

	public void setFansImageList(List<FansImage> fansImageList) {
		this.fansImageList = fansImageList;
	}

	public List<FansPojo> getFanspojoList() {
		return fanspojoList;
	}

	public void setFanspojoList(List<FansPojo> fanspojoList) {
		this.fanspojoList = fanspojoList;
	}

	public List<FansUser> getFansUserList() {
		return fansUserLists;
	}

	public List<FansUserPojo> getFansList() {
		return fansList;
	}
	public void setFansList(List<FansUserPojo> fansList) {
		this.fansList = fansList;
	}
	public List<FansImageVisit> getFansImageVisitList() {
		return fansImageVisitList;
	}
	public void setFansImageVisitList(List<FansImageVisit> fansImageVisitList) {
		this.fansImageVisitList = fansImageVisitList;
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
	public void setFansUserList(List<FansUserPojo> fansUserList) {
		this.fansUserList = fansUserList;
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


}
