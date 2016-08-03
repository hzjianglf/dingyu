package lmd.extend.wso.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lmd.extend.wso.util.SbJsonUtil;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping({"/carts"})
public class CartsManagerController{
//  @Autowired
//  private MonitorMFDao monitorMFDao;
  @Autowired
  private SessionFactory sessionFactory;
  @RequestMapping({"/list.do"})
  public void operlist(HttpServletResponse res, HttpServletRequest req) throws IOException{
	  String start =  req.getParameter("start")==null?"0":req.getParameter("start");
	  String limit =  req.getParameter("limit")==null?"0":req.getParameter("limit");
	  String userId =  req.getParameter("userid")==null?"0":req.getParameter("userid");
	  Session session = this.sessionFactory.openSession();
      Connection conn = null; 
  	  Statement  st= null;
  	  ResultSet rs = null;
	  StringBuffer sql = new StringBuffer();
	  sql.append("select * from (select rownum rn,ac_shop.* from ac_shop");
	  if(!"0".equals(userId)){
		  sql.append(" where user_id='").append(userId).append("' and status='0')");
	  }
	  sql.append("where rn > '").append(Integer.parseInt(start)).append("' ");
	  sql.append("and rn <= '").append(Integer.parseInt(start)+Integer.parseInt(limit)).append("' ");
	  StringBuffer countsql = new StringBuffer();
	  countsql.append("select count(*) rowcount from ac_shop ");
	  countsql.append(" where user_id='").append(userId).append("' and status='0'");
	  try {
		  conn = session.connection();
		  st = conn.createStatement();
		  rs = st.executeQuery(countsql.toString());
		  int rowCount = 0 ;
		  while(rs.next()){
			  rowCount = rs.getInt("rowcount");
		  }
		  int over = rowCount-Integer.parseInt(start);
		  int index=0;
		  int overIndex=0;
		  if(over>=10){
			  overIndex=10;
		  }
		  if(over>0 && over<10){
			  overIndex=over;
		  }
		  if(over<=0){
			  overIndex=rowCount;
		  }
		  rs = st.executeQuery(sql.toString());
		  StringBuffer sbjson = new StringBuffer();
		  sbjson.append("{results:[");
		 
		  while(rs.next()){
			  index++;
			  sbjson.append("{")
			  .append("id:'").append(rs.getString("id")==null?"":rs.getString("id")).append("',")
			  .append("user_id:'").append(rs.getString("user_id")==null?"":rs.getString("user_id")).append("',")
			  .append("resource_id:'").append(rs.getString("resource_id")==null?"":rs.getString("resource_id")).append("',")
			  .append("resource_name:'").append(rs.getString("resource_name")==null?"":rs.getString("resource_name")).append("',")
			  .append("task_id:'").append(rs.getString("task_id")==null?"":rs.getString("task_id")).append("',")
			  .append("createtime:'").append(rs.getString("createtime")==null?"":rs.getString("createtime")).append("',")
			  .append("status:'").append(rs.getString("status")==null?"":rs.getString("status")).append("',");
			  if(index<overIndex){
				  sbjson.append("},");
			  }else{
				  sbjson.append("}");
			  }
			  
		  }
		  sbjson.append("],totalCount:'").append(rowCount).append("'}");
		  String data = SbJsonUtil.toJason(sbjson);
//		  String jsonStr="[{id:'1',name:'zhang'},{id:'2',name:'wang'}]";
		  //res.getWriter().write("{results:[{xtmc:'测政服务',monitorid:'04c4a323-790a-4eaa-9f01-c4ba81cebbe9',enddate:'2015-09-24 13:01:21',maxwaittime:'',remoteip:'10.10.23.29',startdate:'2015-10-21 07:55:20',wsurl:'POST http://10.10.10.207:7803/TLWComponentWeb/SPJGGS/TLW_SPJGGS_YDYS.asmx HTTP/1.1',attribute:'M007_020020001000033700001000_1_0',resstatus:''},{xtmc:'管理员',monitorid:'ff1e5c7c-46c2-40b6-be88-fa4c12f1a949',enddate:'2015-10-08 15:12:48.464815',maxwaittime:'',remoteip:'10.10.23.21',startdate:'2015-10-21 07:55:20',wsurl:'POST http://10.10.10.207:7801/services/org/PersonManager HTTP/1.1',attribute:'M007_020020001000033700001000_1_0',resstatus:'200'},{xtmc:'管理员',monitorid:'b6e54f82-9d50-4976-b4ef-452ad6027f76',enddate:'2015-10-08 15:12:57.324628',maxwaittime:'',remoteip:'10.10.23.21',startdate:'2015-10-21 07:55:20',wsurl:'POST http://10.10.10.207:7801/services/org/PersonManager HTTP/1.1',attribute:'M007_M020020001000033700001000',resstatus:'200'}],totalCount:'3'}");
		  res.getWriter().write(data);
	} catch (HibernateException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}

	  
  }
  @RequestMapping({"/delete.do"})
  public void deleteCart(HttpServletResponse res, HttpServletRequest req) throws IOException{
	  String ids =  req.getParameter("ids")==null?"000":req.getParameter("ids");
	  String userId =  req.getParameter("userid")==null?"0":req.getParameter("userid");
	  StringBuffer sql = new StringBuffer();
	  sql.append("delete ac_shop where status='0' and user_id='").append(userId).append("' and id in(").append(ids).append(")");
	  Session session = this.sessionFactory.openSession();
      Connection conn = null; 
  	  Statement  st= null;
  	  int rs = 0;
	  conn = session.connection();
	  try {
		st = conn.createStatement();
		rs = st.executeUpdate(sql.toString());
		res.getWriter().write("删除成功");
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
		res.getWriter().write("删除失败");
	}finally{
		try {
			conn.close();
			st.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
//	  res.getOutputStream().print("{msg:success}");
  }
  
  @RequestMapping(value={"/commit.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public void commitCart(HttpServletResponse res, HttpServletRequest req) throws IOException{
	  String ids =  req.getParameter("ids")==null?"000":req.getParameter("ids");
	  String userId =  req.getParameter("userid")==null?"0":req.getParameter("userid");
	  StringBuffer sql = new StringBuffer();
	  sql.append("update ac_shop set status='2' where user_id='").append(userId).append("' and id in(").append(ids).append(")");
	  Session session = this.sessionFactory.openSession();
      Connection conn = null; 
  	  Statement  st= null;
  	  int rs = 0;
	  conn = session.connection();
	  try {
		st = conn.createStatement();
		rs = st.executeUpdate(sql.toString());
		System.out.println("rsrsrsrsrsrsrsrsrsrsrs:"+rs);
		res.getWriter().write("提交申请成功");
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
		res.getWriter().write("提交申请失败");
	}finally{
		try {
			conn.close();
			st.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
  }
  
//  @RequestMapping({"/getTemplate.do"})
//  public void getTemplate(HttpServletResponse res, HttpServletRequest req) throws Exception{
//	  String id =  req.getParameter("id")==null?"000":req.getParameter("id");
//	  Map root = new HashMap();
//	  InformationIndexDao informationIndexDao = (InformationIndexDao)SpringUtil.getBean("informationIndexDao");
//	  InformationIndex informationIndex = (InformationIndex)informationIndexDao.findOne(id);
//	  if (informationIndex != null) {
//		  String infoId = informationIndex.getInfoID();
//	  }
//  }
}