<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="javax.persistence.EntityManager"%>
<%@ page import="javax.persistence.EntityManagerFactory"%>
<%@ page import="javax.persistence.Query"%>
<%@ page import="net.risesoft.soa.info.tools.SpringUtil"%>
<%@ page import="net.risesoft.soa.ac.manager.ActorManager"%>



<%
////资源别名（ 存储资源编号）
String   sql123 = "select s.id,s.aliasname,s.name from ac_resource s where s.parent_id='_08o0UQ6NEeWobedMQdjMkA' order by s.aliasname";
EntityManagerFactory  emf1 = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");
EntityManager em1 = emf1.createEntityManager();

Query query1 = em1.createNativeQuery(sql123);
List searchList = query1.getResultList();

EntityManagerFactory emf = (EntityManagerFactory)SpringUtil.getBean("entityManagerFactory");
EntityManager em = emf.createEntityManager();



String acshortnum=(String)searchList.get(0);

out.print("<chart caption='资源分类' palette='2' animation='1' formatNumberScale='0'  pieSliceDepth='30' startingAngle='125'>");
for(int i=0;i<searchList.size();i++){
	Object[] obj = (Object[]) searchList.get(i); 
String sql="select count(*) from (SELECT LEVEL ls FROM ac_resource E CONNECT BY PRIOR E.Id = E.Parent_Id  START WITH E.Id ='"+obj[0]+"') where ls=3";
Query query = em.createNativeQuery(sql);
List searchList1 = query.getResultList();
out.print("<set label='"+obj[2]+"' value='"+searchList1.get(0)+"' isSliced='1' link='n- http://localhost:8000/info/index.jsp?infoID=_dkVpUE0kEeWa-8QI_ixwrA&shortacnum="+obj[1]+"&acid="+obj[0]+"'/>");

}



out.print("<styles><definition><style type='font' name='CaptionFont' size='15' color='666666' />");
out.print("<style type='font' name='SubCaptionFont' bold='0' />");
out.print("</definition><application><apply toObject='caption' styles='CaptionFont' />");
out.print("<apply toObject='SubCaption' styles='SubCaptionFont' /></application></styles></chart>");




%>
