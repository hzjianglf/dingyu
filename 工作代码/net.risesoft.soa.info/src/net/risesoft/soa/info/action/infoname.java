package net.risesoft.soa.info.action;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import net.risesoft.soa.info.tools.SpringUtil;
public class infoname 
{


  public String getinfoname(String acid){
	  String infoname="";
	  try{
          //资源名称
          String sql11="select  s.name from ac_resource s where s.id='"+acid+"'";
          EntityManagerFactory  emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");
          EntityManager  em = emf.createEntityManager();
          Query  query = em.createNativeQuery(sql11);
       List   searchList = query.getResultList();
       infoname=(String)searchList.get(0);
	  }catch(Exception e){
		  
	  }
	  return infoname;
  }
  

}