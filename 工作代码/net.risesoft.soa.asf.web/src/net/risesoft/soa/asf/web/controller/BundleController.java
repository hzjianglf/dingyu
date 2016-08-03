package net.risesoft.soa.asf.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.risesoft.soa.asf.web.helper.BundleHelper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;
import org.osgi.service.packageadmin.RequiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.osgi.context.BundleContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/bundles"})
public class BundleController implements BundleContextAware
{
  private static final Logger log = LoggerFactory.getLogger(BundleController.class);
  private BundleContext bundleContext;

  @Autowired
  private PackageAdmin packageAdmin;

  @Autowired
  private BundleHelper helper;

  @Autowired
  private SessionFactory sessionFactory;
  
  @RequestMapping
  public String index()
  {
    return "redirect:bundles/index.html";
  }

  @RequestMapping({"index.html", "index.do", "index","indexupload.html"})
  public void index(HttpServletRequest req, Map<String, String> model) {
	  model.put("AppRoot", req.getContextPath());
  }

  @RequestMapping({"list.json"})
  @ResponseBody
  public List<Map<String, Object>> list(@RequestParam(value="keyword", defaultValue="") String keyword)
  {
    Bundle[] bundles = getBundleContext().getBundles();
    List model = new ArrayList();
    for (Bundle b : bundles) {
      if (this.helper.matches(b, keyword)) {
        Map map = new HashMap();
        map.put("BundleId", Long.valueOf(b.getBundleId()));
        map.put("SymbolicName", b.getSymbolicName());
        map.put("Name", b.getHeaders().get("Bundle-Name"));
        map.put("State", this.helper.toStateString(b.getState()));
        map.put("Version", b.getHeaders().get("Bundle-Version"));
        map.put("Gjly","A1");
        map.put("Operation", "<a href=\"javascript:operation(\'start\',\'"+Long.valueOf(b.getBundleId())+"\')\" >启动</a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:operation(\'stop\',\'"+Long.valueOf(b.getBundleId())+"\')\">停止</a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:operation(\'logout\',\'"+Long.valueOf(b.getBundleId())+"\')\" >注销</a>");
        model.add(map);
      }
    }
    return model;
  }
  
  @RequestMapping({"listuploadbundles.json"})
  @ResponseBody
  public List<Map<String, Object>> listuploadbundles(@RequestParam(value="keyword", defaultValue="") String keyword)
  {// add by sunming 20150821
    List model = helper.findUploadComponent();
    return model;
  }
  
  @RequestMapping(value={"start/{bundleId}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public void start(@PathVariable("bundleId") long bundleId,HttpServletResponse response)
  {
    BundleContext context = getBundleContext();
    response.setContentType("application/json; charset=utf-8"); 
    PrintWriter out=null;
    String  newcode= "{status: \"error\", message:\"unknown\"}";
    if (context != null) {
      Bundle bundle = context.getBundle(bundleId);
      try {
        bundle.start();
        newcode= "{status: \"sucess\", message:\"启动插件[" + bundleId + ", name=" + bundle.getSymbolicName() + "]成功.\"}";
//      return "{status: \"sucess\", message:\"启动插件[" + bundleId + ", name=" + bundle.getSymbolicName() + "]成功.\"}";
      } catch (BundleException ex) {
        log.error("启动插件[" + bundleId + ", name=" + bundle.getSymbolicName() + "]失败.", ex);
        newcode = "启动插件[" + bundleId + ", name=" + bundle.getSymbolicName() + "]失败.";
//      return "{status: \"error\", message:\"" + ex.getMessage() + "\"}";
      }
    }
    try {
		out = response.getWriter();
    } catch (IOException e) {
		e.printStackTrace();
	}   
	out.write(newcode);  
//    return "{status: \"error\", message:\"unknown\"}";
  }
  @RequestMapping(value={"stop/{bundleId}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public void stop(@PathVariable("bundleId") long bundleId,HttpServletResponse response)
  {
    BundleContext context = getBundleContext();
    response.setContentType("application/json; charset=utf-8"); 
    PrintWriter out=null;
    String  newcode= "{status: \"error\", message:\"unknown\"}";
    if (context != null) {
      Bundle bundle = context.getBundle(bundleId);      
      try {
        bundle.stop();
        newcode= "{status: \"sucess\", message:\"停止插件[" + bundleId + ", name=" + bundle.getSymbolicName() + "]成功.\"}";
      } catch (BundleException ex) {
        log.error("停止插件[" + bundleId + ", name=" + bundle.getSymbolicName() + "]", ex);
//      return "{status: \"error\", message:\"" + ex.getMessage() + "\"}";
        newcode="{status: \"error\", message:\"停止插件失败,原因：" + ex.getMessage() + "\"}";
      } 
    }
    try {
	   out = response.getWriter();
    } catch (IOException e) {
	   e.printStackTrace();
	}   
	out.write(newcode); 
//    return "{status: \"error\", message:\"unknown\"}";
  }
  @RequestMapping(value={"update/{bundleId}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public void update(@PathVariable long bundleId,HttpServletResponse response)
  {
    BundleContext context = getBundleContext();
    response.setContentType("application/json; charset=utf-8"); 
    PrintWriter out=null;
    String  newcode= "{status: \"error\", message:\"unknown\"}";
    if (context != null) {
      Bundle bundle = context.getBundle(bundleId);
      try {
        bundle.update();
        newcode= "{status: \"sucess\", message:\"更新插件[" + bundleId + ", name=" + bundle.getSymbolicName() + "]成功.\"}";
//        return "{status: \"sucess\", message:\"更新插件[" + bundleId + ", name=" + bundle.getSymbolicName() + "]成功.\"}";
      } catch (BundleException ex) {
        log.error("更新插件[" + bundleId + ", name=" + bundle.getSymbolicName() + "]", ex);
        newcode = "{status: \"error\", message:\"更新插件失败，原因：" + ex.getMessage() + "\"}";
//        return "{status: \"error\", message:\"更新插件失败，原因：" + ex.getMessage() + "\"}";
      }
    }
    try {
 	   out = response.getWriter();
     } catch (IOException e) {
 	   e.printStackTrace();
 	}   
 	out.write(newcode); 
//    return "{status: \"error\", message:\"unknown\"}";
  }
  
  @RequestMapping(value={"logout/{bundleId}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public void logout(@PathVariable("bundleId") long bundleId,HttpServletResponse response)
  {
    BundleContext context = getBundleContext();
    response.setContentType("application/json; charset=utf-8"); 
    PrintWriter out=null;
    String  newcode= "{status: \"error\", message:\"unknown\"}";
    if (context != null) {
      Bundle bundle = context.getBundle(bundleId);
      try {
        bundle.uninstall();
        newcode="{status: \"sucess\", message:\"注销插件[" + bundleId + ", name=" + bundle.getSymbolicName() + "]成功.\"}";
//        return "{status: \"sucess\", message:\"注销插件[" + bundleId + ", name=" + bundle.getSymbolicName() + "]成功.\"}";
      } catch (BundleException ex) {
        log.error("注销插件[" + bundleId + ", name=" + bundle.getSymbolicName() + "]", ex);
        newcode="{status: \"error\", message:\"注销插件失败,原因：" + ex.getMessage() + "\"}";
//        return "{status: \"error\", message:\"" + ex.getMessage() + "\"}";
      }
    }
    try {
 	   out = response.getWriter();
    } catch (IOException e) {
 	   e.printStackTrace();
 	}   
 	out.write(newcode); 
//    return "{status: \"error\", message:\"unknown\"}";
  }

  @RequestMapping(value={"upload"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public void upload(@RequestParam MultipartFile[] myfiles, HttpServletRequest request,HttpServletResponse response) throws IOException{  
      //如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解   
       //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解   
      //并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件 
	  int i=0;
	  String filePath="";
      for(MultipartFile myfile : myfiles){  
          if(myfile.isEmpty()){  
        	  
          }else{  
        	// 文件保存路径     
//           String filePath = request.getSession().getServletContext() + "upload/"  + myfile.getOriginalFilename(); 
        	 String path=System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("work"))+"bundlesupload/";
        	 path=path.replace("\\","/");
             filePath = path+myfile.getOriginalFilename();         
        	// 转存文件  
             myfile.transferTo(new File(filePath)); 
          }  
      }
      String bundlename = request.getParameter("bundlename");
      String bundledesc = request.getParameter("bundledesc");            
	  Session session = this.sessionFactory.openSession();
	  response.setContentType("application/json; charset=utf-8"); 
	  PrintWriter out=null;
	  String  newcode= "{status: \"error\", message:\"unknown\"}";
	  try {
	    	Connection conn = session.connection(); // createQuery("from LoginLog t where t.userDN like ? or t.loginName like ? order by t.loginTime desc");
	    	Statement  st=conn.createStatement();
	    	st.executeUpdate("insert into asf_uploadbundles(id,name,path,description,isinstall) values (hibernate_sequence.Nextval,'"+bundlename+"','"+"file:/"+filePath+"','"+bundledesc+"','1')");
	 	    st.close();
	    	conn.close();
	        newcode= "{status: \"sucess\", message:\"上传构件成功.\"}";
	    }catch (Exception e) {
			e.printStackTrace();
	        newcode= "{status: \"error\", message:\"上传构件失败.\"}";
		} finally {
	      session.close();
	    }
	    try {
			out = response.getWriter();
	    } catch (IOException e) {
			e.printStackTrace();
		}   
		out.write(newcode);  
   }
  
  
  @RequestMapping(value={"setup/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public void setup(@PathVariable("id") long id,HttpServletResponse response){   
	  Session session = this.sessionFactory.openSession();
	  response.setContentType("application/json; charset=utf-8"); 
	  PrintWriter out=null;
	  String  newcode= "{status: \"error\", message:\"unknown\"}";
	  Long  bundleid;
	  String bundlepath="";
	  try {
	    	Connection conn = session.connection(); // createQuery("from LoginLog t where t.userDN like ? or t.loginName like ? order by t.loginTime desc");
	    	Statement  st=conn.createStatement();
	    	ResultSet  rs = st.executeQuery("select * from asf_uploadbundles where id='"+id+"'");
	    	Bundle bundle=null;
	    	while(rs.next()){    	
	    		bundlepath=rs.getString("PATH");
	    		bundle=getBundleContext().installBundle(bundlepath);
	    	}   
	    	st.executeUpdate("update asf_uploadbundles set isinstall='0',bundleid='"+bundle.getBundleId()+"'");
	    	rs.close();
	    	st.close();
	    	conn.close();
	        newcode= "{status: \"sucess\", message:\"安装构件成功.\",bundleid:\""+bundle.getBundleId()+"\"}";
		    this.copybundlefile(bundlepath, bundlepath.replace("bundlesupload","bundlessetup"));
	    }catch (Exception e) {
			e.printStackTrace();
	        newcode= "{status: \"error\", message:\"安装构件失败.\"}";
		} finally {
	      session.close();
	    }
	    try {
			out = response.getWriter();
	    } catch (IOException e) {
			e.printStackTrace();
		}   
		out.write(newcode);  
  }
  @RequestMapping(value={"delete/{id}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public void delete(@PathVariable("id") long id,HttpServletResponse response){   
	  Session session = this.sessionFactory.openSession();
	  response.setContentType("application/json; charset=utf-8"); 
	  PrintWriter out=null;
	  String  newcode= "{status: \"error\", message:\"unknown\"}";
	  try {
	    	Connection conn = session.connection(); // createQuery("from LoginLog t where t.userDN like ? or t.loginName like ? order by t.loginTime desc");
	    	Statement  st=conn.createStatement();
	    	ResultSet  rs = st.executeQuery("select * from asf_uploadbundles where id='"+id+"'");
	    	while(rs.next()){
//	    		getBundleContext().installBundle(rs.getString("PATH"));
	    		File file = new File(rs.getString("PATH"));  
	    		file.delete();
	    	}   
	    	st.executeUpdate("delete from asf_uploadbundles where id='"+id+"'");
	    	rs.close();
	    	st.close();
	    	conn.close();
	        newcode= "{status: \"sucess\", message:\"删除构件成功.\"}";
	    }catch (Exception e) {
			e.printStackTrace();
	        newcode= "{status: \"error\", message:\"删除构件失败.\"}";
		} finally {
	      session.close();
	    }
	    try {
			out = response.getWriter();
	    } catch (IOException e) {
			e.printStackTrace();
		}   
		out.write(newcode);  
  }
  @RequestMapping(value={"detail/{bundleId}.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public List<String> detail(@PathVariable("bundleId") long bundleId) {
    Bundle bundle = getBundle(bundleId);
    if (bundle == null) return Collections.emptyList();
    List model = new ArrayList();
    ServiceReference[] services = bundle.getRegisteredServices();
    if (services != null) {
      model.add("已注册的 OSGi Service: ");
      for (ServiceReference ref : services){
        model.add("&nbsp;&nbsp;&nbsp;&nbsp;" + ref.toString());
      }
    }else {
      model.add("没有注册 OSGi Service.");
    }
    services = bundle.getServicesInUse();
    if (services != null) {
      model.add("已引用的 OSGi Service: ");
      for (ServiceReference ref : services){
        model.add("&nbsp;&nbsp;&nbsp;&nbsp;" + ref.toString());
      }
    }else {
      model.add("没有引用 OSGi Service.");
    }
    ExportedPackage[] exportedPackages = this.packageAdmin.getExportedPackages(bundle);
    if (exportedPackages != null) {
      model.add("已导出的包: ");
      for (int j = 0; j < exportedPackages.length; ++j) {
        StringBuffer buff = new StringBuffer();
        buff.append("&nbsp;&nbsp;&nbsp;&nbsp;");
        buff.append(exportedPackages[j].getName());
        buff.append("; version=\"");
        buff.append(exportedPackages[j].getVersion());
        buff.append("\"");
        model.add(buff.toString());
      }
    } else {
      model.add("没有导出任何包.");
    }
    if ((this.packageAdmin.getBundleType(bundle) & 0x1) > 0) {
      Bundle[] hosts = this.packageAdmin.getHosts(bundle);
      if (hosts != null) {
        model.add("Host 插件: ");
        for (int l = 0; l < hosts.length; ++l){
          model.add("&nbsp;&nbsp;&nbsp;&nbsp;" + hosts[l]);
        }
      }else {
        model.add("没有 Host 插件.");
      }
    } else {
      Bundle[] fragments = this.packageAdmin.getFragments(bundle);
      if (fragments != null) {
        model.add("Fragment 插件: ");
        for (int l = 0; l < fragments.length; ++l){
          model.add("&nbsp;&nbsp;&nbsp;&nbsp;" + fragments[l]);
        }
      } else {
        model.add("没有 Fragment 插件.");
      }
    }
    RequiredBundle[] requiredBundles = this.packageAdmin.getRequiredBundles(null);
    RequiredBundle requiredBundle = null;
    if (requiredBundles != null) {
      for (int i1 = 0; i1 < requiredBundles.length; ++i1) {
        if (requiredBundles[i1].getBundle() == bundle) {
          requiredBundle = requiredBundles[i1];
          break;
        }
      }
    }
    if (requiredBundle == null) {
      model.add("没有依赖的插件.");
    } else {
      model.add("依赖的插件: ");
      for (int i1 = 0; i1 < requiredBundles.length; ++i1)
        if (requiredBundles[i1] != requiredBundle) {
          Bundle[] depBundles = requiredBundles[i1].getRequiringBundles();
          if (depBundles != null)
            for (int j = 0; j < depBundles.length; ++j) {
              StringWriter stringWriter = new StringWriter();
              PrintWriter printer = new PrintWriter(stringWriter);
              if (depBundles[j] == bundle) {
                printer.print("  ");
                printer.print(requiredBundles[i1]);
                Bundle provider = requiredBundles[i1].getBundle();
                printer.print("<");
                printer.print(provider);
                printer.println(">");
                model.add(stringWriter.toString());
              }
            }
        }
    }
    return model;
  }

  @RequestMapping({"/count.json"})
  @ResponseBody
  public Map<String, Object> count(@RequestParam(value="keyword", defaultValue="") String keyword) {
    BundleContext bundleContext = getBundleContext();
    Bundle[] bundles = bundleContext.getBundles();
    int all = bundles.length; int display = 0; int installed = 0; int resolved = 0; int active = 0; int unknown = 0;
    for (Bundle b : bundles) {
      if (this.helper.matches(b, keyword)) {
        ++display;
        switch (b.getState())
        {
        case 2:
          ++installed;
          break;
        case 4:
          ++resolved;
          break;
        case 32:
          ++active;
          break;
        default:
          ++unknown;
        }
      }
    }

    Map map = new HashMap();
    map.put("all", Integer.valueOf(all));
    map.put("display", Integer.valueOf(display));
    map.put("installed", Integer.valueOf(installed));
    map.put("resolved", Integer.valueOf(resolved));
    map.put("active", Integer.valueOf(active));
    map.put("unknown", Integer.valueOf(unknown));
    return map;
  }
  private void copybundlefile(String oldpath,String newpath){//add by sunming 20150825 注销后放到注销文件夹下，上传的安装后放到pickup文件夹下
	   try {
		   InputStream inStream = new FileInputStream(oldpath); //读入原文件 
		   FileOutputStream fs  = new FileOutputStream(newpath); 
		   int bytesum  = 0; 
		   int byteread = 0; 
		   byte[] buffer = new byte[1444]; 
		   int length; 
		   while ( (byteread = inStream.read(buffer)) != -1) { 
			   bytesum += byteread; //字节数 文件大小 
			   fs.write(buffer, 0, byteread); 
		   } 
		   inStream.close();
		   deletebundlefile(oldpath);
	   } catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	   } 
  }
  private void deletebundlefile(String oldpath){
	  File file = new File(oldpath);
	  file.delete();
  }
  private Bundle getBundle(long bundleId) {
    return getBundleContext().getBundle(bundleId);
  }

  public void setBundleContext(BundleContext bundleContext) {
    this.bundleContext = bundleContext;
  }

  public BundleContext getBundleContext() {
    return this.bundleContext;
  }
}