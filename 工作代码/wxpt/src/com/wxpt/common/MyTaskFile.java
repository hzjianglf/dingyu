package com.wxpt.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import com.wxpt.action.site.MenuUtil;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.Move;
import com.wxpt.site.entity.UserSitePagemeta;
import com.wxpt.site.service.EnterService;
import com.wxpt.site.service.MoveService;
import com.wxpt.site.service.UserService;
import com.wxpt.site.service.WebSiteService;
public class MyTaskFile extends TimerTask {
	private int enterId;
	List<Move> moveList;
	List<ManageUser>userList=new ArrayList<ManageUser>();
	@Autowired
	UserService userService=Parent.getUserService();
	MoveService moveService = Parent.getMoveService();
	EnterService enterService = Parent.getEnterService();
	private static String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void run() {
		try {
			CheckMoveTime();
			// updateEnterInfor();
			//System.out.println("当前时间："+TimeUtil.getHours());
			if (TimeUtil.getHours().equals("00")) {
				deleteFile();
			}
			getAllUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	FileUploadBean bean = new FileUploadBean();
	FileViewer fileViewer = new FileViewer();
	@SuppressWarnings({ "unchecked",  "static-access" })
	/**
	 * 删除垃圾文件
	 */
	private void deleteFile() {
		// TODO Auto-generated method stub
		EnterService enterService = Parent.getEnterService();
		List<EnterInfor> enterList = enterService.getAllEnter();
		WebSiteService webSiteService = Parent.getWebSiteService();
		for (EnterInfor enterInfor : enterList) {
			List<String> allFilePath = fileViewer.getListFiles(path+enterInfor.getEnterInforId());
			System.out.println(enterInfor.getEnterInforId()+"所有文件路径："+allFilePath.size());
			String sql = "select * from wxpt" + enterInfor.getEnterInforId()
					+ ".user_site_pagemeta where meta_key = 'txt_editor_1' ";
			List<UserSitePagemeta> userSitePagemetas = webSiteService
					.getSitePageMetaList(sql);
			for (UserSitePagemeta userSitePagemeta : userSitePagemetas) {
				List<String> fileList = fileViewer.getFilePath(userSitePagemeta
						.getMetaValue());
				if (fileList.size() == 0) {
					continue;
				} else {
					for (int i = 0; i < allFilePath.size(); i++) {
						for (String string : fileList) {
							System.out.println(string);
							System.out.println(allFilePath.get(i));
							if(allFilePath.get(i).endsWith(string.replace("/", "\\"))){
								allFilePath.remove(i);
							}
						}
					}
					for (String string : allFilePath) {
						System.out.println("删除文件路径:"+string);
						bean.deletefileByAllPath(string);
					}
				}
			}
		}
	}

	
	

	public void updateEnterInfor() {
		// 自动更新access_token
		EnterService enterService = Parent.getEnterService();
		List<EnterInfor> enterList = enterService.getAllEnter();
		EnterInfor enter;
		String appId;
		String appSecret;
		String access_token;
		for (int i = 0; i < enterList.size(); i++) {
			appId = new String();
			appSecret = new String();
			access_token = new String();
			enter = new EnterInfor();
			enter = enterList.get(i);
			appId = enter.getAppId();
			appSecret = enter.getAppSecret();
			access_token = MenuUtil.getAccess_token(appId, appSecret);
			String sql = "UPDATE webchat.enter_infor SET " + "`access_token`='"
					+ access_token + "' WHERE `enter_infor_id`="
					+ enter.getEnterInforId() + "";
			enterService.updateEnter(sql);
		}
	}

	public void CheckMoveTime() throws ParseException {
		// HttpServletRequest request = ServletActionContext.getRequest();
		// HttpServletResponse response = ServletActionContext.getResponse();
		// Cookie[] cookies =request.getCookies();
		// Cookie c =null;
		// for(int i=0;i<cookies.length;i++){
		// c = cookies[i];
		// if (c.getName().equals("wxpts")) {
		// String[] cString = c.getValue().split(":");
		// String name=cString[0];
		// String enter=cString[2];
		// enterId=Integer.parseInt(enter);
		//
		// }
		//
		// }
		List<EnterInfor> enterList = new ArrayList<EnterInfor>();
		enterList = enterService.getAllEnter();
		for (int j = 0; j < enterList.size(); j++) {
			enterId = enterList.get(j).getEnterInforId();
			try {
				moveList = moveService.getAllMove(enterId);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (moveList == null) {
				return;
			}
			Move move = new Move();
			for (int i = 0; i < moveList.size(); i++) {
				move = moveList.get(i);
				if (isFinish(move)) {
					try {
						move.setMoveContent(move.getMoveContent());
						move.setMoveEndTime(move.getMoveEndTime());
						move.setMoveName(move.getMoveName());
						move.setMoveStartTime(move.getMoveStartTime());
						move.setMoveState(0);
						move.setMoveStateDesc("已开启");
						moveService.update(move, enterId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						move.setMoveContent(move.getMoveContent());
						move.setMoveEndTime(move.getMoveEndTime());
						move.setMoveName(move.getMoveName());
						move.setMoveStartTime(move.getMoveStartTime());
						move.setMoveState(1);
						move.setMoveStateDesc("已关闭");
						moveService.update(move, enterId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	private boolean isFinish(Move move) throws ParseException {
		boolean isFinish = false;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date date = new Date();
		String curTime = df.format(date);
		String strNow = curTime.replaceAll("/", "-");
		String strEnd = move.getMoveEndTime().replaceAll("/", "-")
				+ " 00:00:00";
		String strStart = move.getMoveStartTime().replaceAll("/", "-")
				+ " 00:00:00";
		Date d1 = df.parse(strNow);
		Date d2;
		try {
			d2 = df.parse(strEnd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return true;
		}
		Date d3 = df.parse(strStart);
		System.out.println(d1);
		System.out.println(d2);
		long diff = d1.getTime() - d2.getTime();
		long diff2 = d1.getTime() - d3.getTime();
		// long hours= diff/(1000*60*60);
		// long mins= diff/(1000*60);
		// long days = diff / (1000 * 60 * 60 * 24);
		if (diff > 0 || diff2 < 0) {
			isFinish = false;
		} else {
			isFinish = true;
		}
		return isFinish;
	}
	
//获取用户
	String userEndTime;
	public void getAllUser(){
		List<EnterInfor> enterList = new ArrayList<EnterInfor>();
		enterList = enterService.getAllEnter();
		for(int i=0;i<enterList.size();i++){
			if(enterList.get(i).getEnterPerson()==null||enterList.get(i).getEnterPerson().equals("null")){
			}else{
				userEndTime=addDate(addDate2(enterList.get(i).getRegistTime()),Integer.parseInt(enterList.get(i).getEnterPerson()));
				String time[]=userEndTime.split(" ");
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				String curTime = df.format(date);
				Date d1;
				Date d2;
				try {
					d1 = df.parse(time[0]);//到期时间
					d2 = df.parse(curTime);//现在时间
					long a=d2.getTime();
					long b=d1.getTime();
					long c=b-a;
					if(c==0||c<0){
						//用户冻结
						String sql="UPDATE webchat.manage_user SET `status`=1 WHERE `enterid`="+enterList.get(i).getEnterInforId();
						userService.updateUser(sql);
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	//时间加2个月
	public static String addDate(String dateStr,int Month) {
		  Calendar ca = Calendar.getInstance();
		  String s = "";
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  Date dd;
		  try {
		   dd = sdf.parse(dateStr);
		   ca.setTime(dd);
		   ca.add(Calendar.MONTH, Month);
		   s = sdf.format(ca.getTime());
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  
		  return s;
		 }
	public static String addDate2(String dateStr) {
		  Calendar ca = Calendar.getInstance();
		  String s = "";
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  Date dd;
		  try {
		   dd = sdf.parse(dateStr);
		   ca.setTime(dd);
		   ca.add(Calendar.DATE, 1);
		   s = sdf.format(ca.getTime());
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  
		  return s;
		 }


	public EnterService getEnterService() {
		return enterService;
	}

	public void setEnterService(EnterService enterService) {
		this.enterService = enterService;
	}

	public static void main(String[] args) {
	}
}