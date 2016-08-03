/**
 * 
 */
package com.lmd.sync.rtx;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.rpc.ServiceException;

import net.risesoft.soa.framework.util.ExAttrHelper;

import com.lmd.sync.rtx.service.client.RtxServiceImplServiceLocator;
import com.lmd.sync.rtx.service.client.RtxServiceImplServiceSoapBindingStub;
import com.lmd.sync.rtx.util.ProperUtil;


public class OrgSyncYdxcImpl {
	private URL ipaddress;	

	public URL getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(URL ipaddress) {
		this.ipaddress = ipaddress;
	}
		
	public boolean sync(String eventType, String data, String targetUID) throws UnsupportedEncodingException{
		boolean sync = false; 
		String temp[] ;
		String roleid="";
		Map<String, String> base = ExAttrHelper.map(data);
		String orgType = base.get("orgType");
		
		if (base.size() > 0){
			if ("SYNC".equalsIgnoreCase(eventType.toUpperCase())){
				if("Organization".equals(orgType)){
					String id=base.get("UID");
					String description= base.get("description");
					if(description==null){description=" ";}
					String name=base.get("name");
					String parent_id=base.get("parentID");
					String deptphone =base.get("deptphone");
					if(deptphone==null){deptphone=" ";}
					String deptfax=base.get("deptfax");
					if(deptfax==null){deptfax=" ";}
					String deptaddress = base.get("deptaddress");
					if(deptaddress==null){deptaddress=" ";}
					String zipcode=base.get("zipcode");
					if(zipcode==null){zipcode=" ";}
					String status="error";
				
					try {
						RtxServiceImplServiceLocator rs = new RtxServiceImplServiceLocator();
						RtxServiceImplServiceSoapBindingStub service = (RtxServiceImplServiceSoapBindingStub) rs.getRtxServiceImplPort();
						//首先获取rtx部门编号，并+1 作为将要插入的rtx的部门id 插入rtx系统
						int rtxdeptId = Integer.parseInt((String)(ProperUtil.getRtxDeptId("count")==null?"0":ProperUtil.getRtxDeptId("count")))+1;
						status = service.addDept(rtxdeptId+"", description, name, "");//此处为组织机构 parentId 默认为空 即可
						if("success".equals(status)){
							//将平台的id与rtx的id对应关系写入文件
							ProperUtil.saveOrUpdate(rtxdeptId+"", id);
							//将count更新
							ProperUtil.saveOrUpdate(rtxdeptId+"", "count");	
						}
						System.out.println(name+"------------------:"+id);
					} catch (RemoteException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (ServiceException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						//System.out.println("add org success.--ydxc");
						sync=true;
					}else{
						sync=false;
						//System.out.println("add org error.--ydxc");
					}
				
				}else if("Department".equals(orgType)){
					String id=base.get("UID");
					String description= base.get("description");
					if(description==null){description=" ";}
					String name=base.get("name");
					String parent_id=base.get("parentID");
					String deptphone =base.get("deptphone");
					if(deptphone==null){deptphone=" ";}
					String deptfax=base.get("deptfax");
					if(deptfax==null){deptfax=" ";}
					String deptaddress = base.get("deptaddress");
					if(deptaddress==null){deptaddress=" ";}
					String zipcode=base.get("zipcode");
					if(zipcode==null){zipcode=" ";}

					String status="error";
					
					try {
						RtxServiceImplServiceLocator rs = new RtxServiceImplServiceLocator();
						RtxServiceImplServiceSoapBindingStub service = (RtxServiceImplServiceSoapBindingStub) rs.getRtxServiceImplPort();
						//首先获取rtx部门编号，并+1 作为将要插入的rtx的部门id 插入rtx系统
						int rtxdeptId = Integer.parseInt((String)ProperUtil.getRtxDeptId("count"))+1;
						//，将 平台的父id作为参数传入，从文件中获取对应的rtx的id
						int rtxparentDeptId = Integer.parseInt((String)ProperUtil.getRtxDeptId(parent_id));
						status = service.addDept(rtxdeptId+"", description, name, rtxparentDeptId+"");
						if("success".equals(status)){
							//将平台的id与rtx的id对应关系写入文件
							ProperUtil.saveOrUpdate(rtxdeptId+"", id);
							//将count更新
							ProperUtil.saveOrUpdate(rtxdeptId+"", "count");	
						}
						System.out.println(name+"--------------------:"+id);
					} catch (RemoteException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (ServiceException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						
							sync=true;
							//System.out.println("add dept success.--ydxc");
						
					}else{
						sync=false;
						//System.out.println("add dept error.--ydxc");
					}
				
					 
				}else if("Person".equals(orgType)){
					String id=base.get("UID");
					String createtime=base.get("createTime");
			    	String loginname = base.get("loginName");
			    	String officeaddress = base.get("officeAddress");
			    	String orgtype=base.get("orgType");
			    	if(orgtype==null){orgtype=" ";}
			    	String sex=base.get("sex");
			    	String policitalstatus = base.get("policitalStatus");
			    	String idNum = base.get("idNum");
			    	if(idNum==null){idNum=" ";}
			    	String tabindex = base.get("tabIndex");
			    	String properties= base.get("properties");
			    	String maritalstatus =base.get("maritalStatus");
			    	String password = base.get("password");
			    	String officephone =base.get("officePhone");
			    	if(officephone==null){officephone=" ";}
			    	String version= base.get("version");
			    	String description= base.get("description");
			    	if(description==null){description=" ";}
			    	String dutylevel=base.get("dutyLevel");
			    	String duty=base.get("duty");
			    	if(duty==null){duty=" ";}
			    	String name=base.get("name");
			    	String official=base.get("official");
			    	String attributes=base.get("attributes");
			    	String dn=base.get("dn");
			    	String mobile = base.get("mobile");
			    	if(mobile==null){mobile=" ";}
			    	
			    	String officeFax=base.get("officeFax");
			    	String email=base.get("email");
			    	if(email==null){email=" ";}
			    	String parent_id=base.get("parentID");

			    	String status="error";
			    	if("耿保民".equals(name)){
			    		System.out.println(sync);
			    	}
					try {
						RtxServiceImplServiceLocator rs = new RtxServiceImplServiceLocator();
						RtxServiceImplServiceSoapBindingStub uservice = (RtxServiceImplServiceSoapBindingStub)rs.getRtxServiceImplPort();
						//，将 平台的父id作为参数传入，从文件中获取对应的rtx的id
						int rtxparentDeptId = Integer.parseInt((String)ProperUtil.getRtxDeptId(parent_id));
							status = uservice.addUser(loginname, rtxparentDeptId+"", name, "111111");//密码都默认设置为111111
							if(status.equals("success")){
								System.out.println(name+" 人员添加成功");
							}else{
								System.out.println(name+ "  人员添加失败");
							}
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						
							sync=true;
							System.out.println("add person success.--ydxc");
						
					}else{
						sync=false;
						System.out.println("add person error.--ydxc");
					}
				
					
				}else if("Role".equals(orgType)){/*
					String id=base.get("UID");
			    	String name=base.get("name");
					X5Action service=new X5ActionLocator();
					String status = " ";
					try {
						status=service.getx5ActionHttpPort(getIpaddress()).appendRoleAction(id, id, name);
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						sync=true;
						System.out.println("add Role success.--ydxc");
					}else{
						sync=false;
						System.out.println("add Role error.--ydxc");
					}
				*/
					sync=true;
					}else if("Admin".equals(orgType)){
					String id=base.get("UID");
					String createtime=base.get("createTime");
			    	String loginname = base.get("loginName");
			    	String officeaddress = base.get("officeAddress");
			    	String orgtype=base.get("orgType");
			    	if(orgtype==null){orgtype=" ";}
			    	String sex=base.get("sex");
			    	String policitalstatus = base.get("policitalStatus");
			    	String idNum = base.get("idNum");
			    	if(idNum==null){idNum=" ";}
			    	String tabindex = base.get("tabIndex");
			    	String properties= base.get("properties");
			    	String maritalstatus =base.get("maritalStatus");
			    	String password = base.get("password");
			    	String officephone =base.get("officePhone");
			    	if(officephone==null){officephone=" ";}
			    	String version= base.get("version");
			    	String description= base.get("description");
			    	if(description==null){description=" ";}
			    	String dutylevel=base.get("dutyLevel");
			    	String duty=base.get("duty");
			    	if(duty==null){duty=" ";}
			    	String name=base.get("name");
			    	String official=base.get("official");
			    	String attributes=base.get("attributes");
			    	String dn=base.get("dn");
			    	String mobile = base.get("mobile");
			    	if(mobile==null){mobile=" ";}
			    	
			    	String officeFax=base.get("officeFax");
			    	String email=base.get("email");
			    	if(email==null){email=" ";}
			    	String parent_id=base.get("parentID");
			    	
			    	

			    	String status="error";
					try {
						RtxServiceImplServiceLocator rs = new RtxServiceImplServiceLocator();
						RtxServiceImplServiceSoapBindingStub uservice = (RtxServiceImplServiceSoapBindingStub)rs.getRtxServiceImplPort();
						//，将 平台的父id作为参数传入，从文件中获取对应的rtx的id
						int rtxparentDeptId = Integer.parseInt((String)ProperUtil.getRtxDeptId(parent_id));
//						if("zhangchunxia".equals(name)){
							status = uservice.addUser(loginname, rtxparentDeptId+"", name, "111111");//密码都默认设置为111111
							if(status.equals("success")){
								System.out.println(name+" 人员添加成功");
							}else{
								System.out.println(name+ "  人员添加失败");
							}
//						}
//						status = uservice.addUser(loginname, rtxparentDeptId+"", name, "111111");//密码都默认设置为111111
//						if(status.equals("success")){
//							System.out.println("人员添加成功");
//						}else{
//							System.out.println("人员添加失败");
//						}
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						
							sync=true;
							System.out.println("add person success.--ydxc");
						
					}else{
						sync=false;
						System.out.println("add person error.--ydxc");
					}
				
				}else{
					sync=true;
				}
			}
			if ("C".equalsIgnoreCase(eventType.toUpperCase())){
				if("Organization".equals(orgType)){/*
					String id=base.get("UID");
					String description= base.get("description");
					if(description==null){description=" ";}
					String name=base.get("name");
					String parent_id=base.get("parentID");
					String deptphone =base.get("deptphone");
					if(deptphone==null){deptphone=" ";}
					String deptfax=base.get("deptfax");
					if(deptfax==null){deptfax=" ";}
					String deptaddress = base.get("deptaddress");
					if(deptaddress==null){deptaddress=" ";}
					String zipcode=base.get("zipcode");
					if(zipcode==null){zipcode=" ";}

					String status="error";
				
					try {
						Org_manageServiceService s=new Org_manageServiceServiceLocator();
						Org_manageServiceBindingStub service=(Org_manageServiceBindingStub)s.getorg_manageServicePort(getIpaddress());
						status=	service.addDepartment(name, id, "1");
					} catch (RemoteException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (ServiceException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						System.out.println("add org success.--ydxc");
						sync=true;
					}else{
						sync=false;
						System.out.println("add org error.--ydxc");
					}
				*/
					sync=true;	
				}else if("Department".equals(orgType)){
					String id=base.get("UID");
					String description= base.get("description");
					if(description==null){description=" ";}
					String name=base.get("name");
					String parent_id=base.get("parentID");
					String deptphone =base.get("deptphone");
					if(deptphone==null){deptphone=" ";}
					String deptfax=base.get("deptfax");
					if(deptfax==null){deptfax=" ";}
					String deptaddress = base.get("deptaddress");
					if(deptaddress==null){deptaddress=" ";}
					String zipcode=base.get("zipcode");
					if(zipcode==null){zipcode=" ";}

					String status="error";
					
					try {
						RtxServiceImplServiceLocator rs = new RtxServiceImplServiceLocator();
						RtxServiceImplServiceSoapBindingStub service = (RtxServiceImplServiceSoapBindingStub) rs.getRtxServiceImplPort();
						//首先获取rtx部门编号，并+1 作为将要插入的rtx的部门id 插入rtx系统
						int rtxdeptId = Integer.parseInt((String)ProperUtil.getRtxDeptId("count"))+1;
						//，将 平台的父id作为参数传入，从文件中获取对应的rtx的id
						int rtxparentDeptId = Integer.parseInt((String)ProperUtil.getRtxDeptId(parent_id));
						status = service.addDept(rtxdeptId+"", description, name, rtxparentDeptId+"");
						if("success".equals(status)){
							//将平台的id与rtx的id对应关系写入文件
							ProperUtil.saveOrUpdate(rtxdeptId+"", id);
							//将count更新
							ProperUtil.saveOrUpdate(rtxdeptId+"", "count");	
						}
						System.out.println(name+"--------------------:"+id);
					} catch (RemoteException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (ServiceException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						
							sync=true;
							System.out.println("add dept success.--ydxc");
						
					}else{
						sync=false;
						System.out.println("add dept error.--ydxc");
					}
				
					 
				}else if ("Person".equals(orgType)){
					String id=base.get("UID");
					String createtime=base.get("createTime");
			    	String loginname = base.get("loginName");
			    	String officeaddress = base.get("officeAddress");
			    	String orgtype=base.get("orgType");
			    	if(orgtype==null){orgtype=" ";}
			    	String sex=base.get("sex");
			    	String policitalstatus = base.get("policitalStatus");
			    	String idNum = base.get("idNum");
			    	if(idNum==null){idNum=" ";}
			    	String tabindex = base.get("tabIndex");
			    	String properties= base.get("properties");
			    	String maritalstatus =base.get("maritalStatus");
			    	String password = base.get("password");
			    	String officephone =base.get("officePhone");
			    	if(officephone==null){officephone=" ";}
			    	String version= base.get("version");
			    	String description= base.get("description");
			    	if(description==null){description=" ";}
			    	String dutylevel=base.get("dutyLevel");
			    	String duty=base.get("duty");
			    	if(duty==null){duty=" ";}
			    	String name=base.get("name");
			    	String official=base.get("official");
			    	String attributes=base.get("attributes");
			    	String dn=base.get("dn");
			    	String mobile = base.get("mobile");
			    	if(mobile==null){mobile=" ";}
			    	
			    	String officeFax=base.get("officeFax");
			    	String email=base.get("email");
			    	if(email==null){email=" ";}
			    	String parent_id=base.get("parentID");

			    	String status="error";
			    	String per_status="error";
					try {
						RtxServiceImplServiceLocator rs = new RtxServiceImplServiceLocator();
						RtxServiceImplServiceSoapBindingStub uservice = (RtxServiceImplServiceSoapBindingStub)rs.getRtxServiceImplPort();
						//，将 平台的父id作为参数传入，从文件中获取对应的rtx的id
						int rtxparentDeptId = Integer.parseInt((String)ProperUtil.getRtxDeptId(parent_id));
							status = uservice.addUser(loginname, rtxparentDeptId+"", name, "111111");//密码都默认设置为111111
							if(status.equals("success")){
								System.out.println(name+" 人员添加成功");
							}else{
								System.out.println(name+ "  人员添加失败");
							}
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						
							sync=true;
							System.out.println("add person success.--ydxc");
						
					}else{
						sync=false;
						System.out.println("add person error.--ydxc");
					}
				
					
				}else if ("Role".equals(orgType)){/*
					String id=base.get("UID");
			    	String name=base.get("name");
					X5Action service=new X5ActionLocator();
					String status = " ";
					try {
						status=service.getx5ActionHttpPort(getIpaddress()).appendRoleAction(id, id, name);
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						sync=true;
						System.out.println("add Role success.--ydxc");
					}else{
						sync=false;
						System.out.println("add Role error.--ydxc");
					}
				*/
					sync=true;
					}else{
					sync=true;//其他情况不予考虑直接返回true
				}
			}
//			if ("U".equalsIgnoreCase(eventType.toUpperCase())){
//				if("Organization".equals(orgType)){
//					String id=base.get("UID");
//					String description= base.get("description");
//					String name=base.get("name");
//					String parent_id=base.get("parentID");
//					String deptphone =base.get("deptphone");
//					if(deptphone==null){deptphone=" ";}
//					String deptfax=base.get("deptfax");
//					if(deptfax==null){deptfax=" ";}
//					String deptaddress = base.get("deptaddress");
//					if(deptaddress==null){deptaddress=" ";}
//					String zipcode=base.get("zipcode");
//					if(zipcode==null){zipcode=" ";}
//
//					String status="error";
//					
//					try {
//						Org_manageServiceService s=new Org_manageServiceServiceLocator();
//						Org_manageServiceBindingStub service=(Org_manageServiceBindingStub)s.getorg_manageServicePort(getIpaddress());
//						status=	service.editDepartment(id, name);
//					} catch (RemoteException e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					} catch (ServiceException e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}
//					if(status.equals("success")){
//						
//							sync=true;
//							System.out.println("upd org success.--ydxc");
//						
//					}else{
//						sync=false;
//						System.out.println("upd org error.--ydxc");
//					}
//				}else if ("Department".equals(orgType)){
//					String id=base.get("UID");
//					String description= base.get("description");
//					String name=base.get("name");
//					String parent_id=base.get("parentID");
//					String deptphone =base.get("deptphone");
//					if(deptphone==null){deptphone=" ";}
//					String deptfax=base.get("deptfax");
//					if(deptfax==null){deptfax=" ";}
//					String deptaddress = base.get("deptaddress");
//					if(deptaddress==null){deptaddress=" ";}
//					String zipcode=base.get("zipcode");
//					if(zipcode==null){zipcode=" ";}
//
//					String status="error";
//					
//					try {
//						Org_manageServiceService s=new Org_manageServiceServiceLocator();
//						Org_manageServiceBindingStub service=(Org_manageServiceBindingStub)s.getorg_manageServicePort(getIpaddress());
//						status=	service.editDepartment(id, name);
//					} catch (RemoteException e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					} catch (ServiceException e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}
//					if(status.equals("success")){
//						
//							sync=true;
//							System.out.println("upd dept success.--ydxc");
//						
//					}else{
//						sync=false;
//						System.out.println("upd dept error.--ydxc");
//					}
//				
//					 
//				}else if ("Person".equals(orgType)){
//					String id=base.get("UID");
//					String createtime=base.get("createTime");
//			    	String loginname = base.get("loginName");
//			    	String officeaddress = base.get("officeAddress");
//			    	String orgtype=base.get("orgType");
//			    	if(orgtype==null){orgtype=" ";}
//			    	String sex=base.get("sex");
//			    	String policitalstatus = base.get("policitalStatus");
//			    	String idNum = base.get("idNum");
//			    	if(idNum==null){idNum=" ";}
//			    	String tabindex = base.get("tabIndex");
//			    	String properties= base.get("properties");
//			    	String maritalstatus =base.get("maritalStatus");
//			    	String password = base.get("password");
//			    	String officephone =base.get("officePhone");
//			    	if(officephone==null){officephone=" ";}
//			    	String version= base.get("version");
//			    	String description= base.get("description");
//			    	if(description==null){description=" ";}
//			    	String dutylevel=base.get("dutyLevel");
//			    	String duty=base.get("duty");
//			    	if(duty==null){duty=" ";}
//			    	String name=base.get("name");
//			    	String official=base.get("official");
//			    	String attributes=base.get("attributes");
//			    	String dn=base.get("dn");
//			    	String mobile = base.get("mobile");
//			    	if(mobile==null){mobile=" ";}
//			    	String email=base.get("email");
//			    	if(email==null){email=" ";}
//
//			    	String status="error";
//			    	String per_status="error";
//					try {
//						Org_manageServiceService s=new Org_manageServiceServiceLocator();
//						Org_manageServiceBindingStub service=(Org_manageServiceBindingStub)s.getorg_manageServicePort(getIpaddress());
//						
//						per_status=	service.editUser(loginname, "111111", name, email, "1",id);
//					
//					} catch (Exception e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}
//					if(per_status.equals("success")){
//						
//							sync=true;
//							System.out.println("upd person success.--ydxc");
//						
//					}else{
//						sync=false;
//						System.out.println("upd person error.--ydxc");
//					}
//				
//					
//				}else if ("Role".equals(orgType)){/*
//					String id=base.get("UID");
//			    	String name=base.get("name");
//					X5Action service=new X5ActionLocator();
//					String status = " ";
//					try {
//						status=service.getx5ActionHttpPort(getIpaddress()).updateRoleAction(id, id, name);
//					} catch (Exception e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}
//					if(status.equals("success")){
//						sync=true;
//						System.out.println("upd Role success.--ydxc");
//					}else{
//						sync=false;
//						System.out.println("upd Role error.--ydxc");
//					}
//				*/}
//			}
			if ("D".equalsIgnoreCase(eventType.toUpperCase())){
				if("Organization".equals(orgType)){/*
					String id=base.get("UID");
					String description= base.get("description");
					String name=base.get("name");
					String parent_id=base.get("parentID");
					String deptphone =base.get("deptphone");
					if(deptphone==null){deptphone=" ";}
					String deptfax=base.get("deptfax");
					if(deptfax==null){deptfax=" ";}
					String deptaddress = base.get("deptaddress");
					if(deptaddress==null){deptaddress=" ";}
					String zipcode=base.get("zipcode");
					if(zipcode==null){zipcode=" ";}

					String status="error";

					try {
						Org_manageServiceService s=new Org_manageServiceServiceLocator();
						Org_manageServiceBindingStub service=(Org_manageServiceBindingStub)s.getorg_manageServicePort(getIpaddress());
						
						status=	service.deleteDepartment(id);
					} catch (RemoteException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (ServiceException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						sync=true;
						System.out.println("del org success.--ydxc");
					}else{
						sync=false;
						System.out.println("del org error.--ydxc");
					}
				*/
					sync=true;
					}else if("Department".equals(orgType)){
					String id=base.get("UID");
					String description= base.get("description");
					String name=base.get("name");
					String parent_id=base.get("parentID");
					String deptphone =base.get("deptphone");
					if(deptphone==null){deptphone=" ";}
					String deptfax=base.get("deptfax");
					if(deptfax==null){deptfax=" ";}
					String deptaddress = base.get("deptaddress");
					if(deptaddress==null){deptaddress=" ";}
					String zipcode=base.get("zipcode");
					if(zipcode==null){zipcode=" ";}

					String status="error";

					try {
						RtxServiceImplServiceLocator rs = new RtxServiceImplServiceLocator();
						RtxServiceImplServiceSoapBindingStub uservice = (RtxServiceImplServiceSoapBindingStub)rs.getRtxServiceImplPort();
						//，将 平台的父id作为参数传入，从文件中获取对应的rtx的id
						int rtxparentDeptId = Integer.parseInt((String)ProperUtil.getRtxDeptId(id));
							status = uservice.deleteDept(rtxparentDeptId+"", "1");//0表示只删除当前节点，其余默认上移至最近的根节点，因为平台默认为级联删除，所以此处设为1级联删除
					} catch (RemoteException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					} catch (ServiceException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						sync=true;
						System.out.println("del dept success.--ydxc");
					}else{
						sync=false;
						System.out.println("del dept error.--ydxc");
					}
				
					 
				}else if ("Person".equals(orgType)){
					String id=base.get("UID");
					String createtime=base.get("createTime");
			    	String loginname = base.get("loginName");
			    	String officeaddress = base.get("officeAddress");
			    	String orgtype=base.get("orgType");
			    	String sex=base.get("sex");
			    	String policitalstatus = base.get("policitalStatus");
			    	String idNum = base.get("idNum");
			    	String tabindex = base.get("tabIndex");
			    	String properties= base.get("properties");
			    	String maritalstatus =base.get("maritalStatus");
			    	String password = base.get("password");
			    	String officephone =base.get("officePhone");
			    	String version= base.get("version");
			    	String description= base.get("description");
			    	String dutylevel=base.get("dutyLevel");
			    	String duty=base.get("duty");
			    	String name=base.get("name");
			    	String official=base.get("official");
			    	String attributes=base.get("attributes");
			    	String dn=base.get("dn");
			    	String mobile = base.get("mobile");
			    	String officeFax=base.get("officeFax");
			    	String email=base.get("email");
			    	String parent_id=base.get("parentID");

			    	String status="error";
			    	String per_status="error";
					try {
						RtxServiceImplServiceLocator rs = new RtxServiceImplServiceLocator();
						RtxServiceImplServiceSoapBindingStub uservice = (RtxServiceImplServiceSoapBindingStub)rs.getRtxServiceImplPort();
						//，将 平台的父id作为参数传入，从文件中获取对应的rtx的id
						status=uservice.deleteUser(loginname);
						System.out.println("id:"+id);
						System.out.println("parent_id:"+parent_id);
						if(status.equals("success")){
							System.out.println("人员从部门移除成功");
						}else{
							System.out.println("人员从部门移除失败");
						}
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						sync=true;
						System.out.println("del person success.--ydxc");
					}else{
						sync=false;
						System.out.println("del person error.--ydxc");
					}
				
					
				}else if ("Role".equals(orgType)){/*
					String id=base.get("UID");
			    	String name=base.get("name");
					X5Action service=new X5ActionLocator();
					String status = " ";
					try {
						status=service.getx5ActionHttpPort(getIpaddress()).deleteRoleAction(id);
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					if(status.equals("success")){
						sync=true;
						System.out.println("del Role success.--ydxc");
					}else{
						sync=false;
						System.out.println("del Role error.--ydxc");
					}
				*/
					sync=true;	
				}else{
					sync=true;
				}
			}
//			if ("M".equalsIgnoreCase(eventType.toUpperCase())){
//				if("Organization".equals(orgType)){
//					
//				}else if("Department".equals(orgType)){/*
//					String id=base.get("UID");
//					String parent_id=base.get("parentID");
//					System.out.println("id:"+id+"----");
//					System.out.println("parent_id:"+parent_id+"----");
//					String status="error";
//			    	
//					try {
//						Org_manageServiceService s=new Org_manageServiceServiceLocator();
//						Org_manageServiceBindingStub service=(Org_manageServiceBindingStub)s.getorg_manageServicePort(getIpaddress());
//						
//						status=	service.editUser2Department(id, parent_id);
//						
//					} catch (Exception e) {
//						// TODO 自动生成的 catch 块
//						e.printStackTrace();
//					}
//					if(status.equals("success")){
//						sync=true;
//						System.out.println("move Department success.--ydxc");
//					}else{
//						sync=false;
//						System.out.println("move Department error.--ydxc");
//					}		
//				*/}else if ("Person".equals(orgType)){
//					String id=base.get("UID");
//					String parent_id=base.get("parentID");
//					System.out.println("id:"+id+"----");
//					System.out.println("parent_id:"+parent_id+"----");
//					String status="error";
//			    	
//					try {
//						Org_manageServiceService s=new Org_manageServiceServiceLocator();
//						Org_manageServiceBindingStub service=(Org_manageServiceBindingStub)s.getorg_manageServicePort(getIpaddress());
//						System.out.println("id:"+id);
//						System.out.println("parent_id:"+parent_id);
//						status=	service.editUser2Department(id, parent_id);
//					} catch (Exception e) {
//						// TODO 自动生成的 catch 块 
//						e.printStackTrace();
//					}
//					if(status.equals("success")){
//						sync=true;
//						System.out.println("move person success.--ydxc");
//					}else{
//						sync=false;
//						System.out.println("move person error.--ydxc");
//					}		
//				}
//			}
			if ("A".equalsIgnoreCase(eventType.toUpperCase())){/*
				if ("Person".equals(orgType)){
					String role_id=targetUID;
					String loginname=base.get("loginName");
					String personid_role=base.get("UID");
					System.out.println(roleid+"----------------------roleid--");
			    	System.out.println(personid_role+"----------------------personid_role--");
			    	System.out.println(loginname+"----------------------loginname--");
			    	try {
						if(stub.addRoleToUser(loginname, role_id)){
							System.out.println("add role to user success---ydxc");
							return true;
						}else{
							System.out.println("add role to user error---ydxc");
							return false;
						}
					} catch (RemoteException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			*/
				sync=true;
				}
//			if ("R".equalsIgnoreCase(eventType.toUpperCase())){/*
//				if ("Person".equals(orgType)){
//					String role_id=targetUID;
//					String loginname=base.get("loginName");
//					String personid_role=base.get("UID");
//					System.out.println(roleid+"----------------------roleid--");
//			    	System.out.println(personid_role+"----------------------personid_role--");
//			    	System.out.println(loginname+"----------------------loginname--");
//			    	try {
//						if(stub.removeRoleFromUser(loginname, role_id)){
//							System.out.println("remove role to user success---ydxc");
//							return true;
//						}else{
//							System.out.println("remove role to user error---ydxc");
//							return false;
//						}
//					} catch (Exception e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}
//				}
//			*/}	
		}
		return sync;
	}
	public static Date str2Date(String str) {
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date date = null;
		 // String杞珼ate
		 //str = "2007-1-18";
		 try {
			 date = format.parse(str); // Thu Jan 18 00:00:00 CST 2007
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
		 return date;
	}
	public static XMLGregorianCalendar long2Gregorian(Date date) {
		 DatatypeFactory dataTypeFactory;
		 try {
			 dataTypeFactory = DatatypeFactory.newInstance();
		 } catch (DatatypeConfigurationException e) {
			 throw new RuntimeException(e);
		 }
		 GregorianCalendar gc = new GregorianCalendar();
		 gc.setTimeInMillis(date.getTime());
		 return dataTypeFactory.newXMLGregorianCalendar(gc);
	}
	
}
