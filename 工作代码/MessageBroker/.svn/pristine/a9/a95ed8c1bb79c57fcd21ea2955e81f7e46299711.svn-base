<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*,com.ibm.hibernate.*, com.opensymphony.xwork2.util.*" %>
<%@taglib prefix="s" uri="/struts-tags" %>
	
<div class="mainmenu">
  <ul id="menubar" class="menubar">
    <li><a href="mainpage.action" tabindex="1">主页</a></li>			
		<li><a class="menutitle" href="#"   tabindex="1"><span>代理监控</span></a>
			<ul>				
     			<li class="submenu"><a class="nolink" href="#"   tabindex="1">监控记录</a>
						<ul>		
							<li><a href="searchcurrent.action" tabindex="1">实时监控</a></li>			
							<li><a href="searchhistory.action" tabindex="1">历史监控</a></li>
							<li><a href="searchfailure.action" tabindex="1">失败监控</a></li>
							<li><a href="querymonitorinfo.action" tabindex="1">监控查询</a></li>													  
	     			</ul>
     			</li>
     			<li class="submenu"><a class="nolink" href="#"   tabindex="1">统计分析</a>
						<ul>		
							<li><a href="getcurrentcharts.action" tabindex="1">实时统计</a></li>			
							<li><a href="gethistorycharts.action" tabindex="1">历史统计</a></li>																		  
	     			</ul>
     			</li>
     			<li class="submenu"><a class="nolink" href="#"   tabindex="1">服务接口监控</a>
						<ul>		
							<li><a href="call2searchrequest.action" tabindex="1">服务请求</a></li>			
							<li><a href="call2searchprovide.action" tabindex="1">服务提供</a></li>
        			<li><a href="mappinginterfaces.action" tabindex="1">服务注册</a></li> 																			  
	     			</ul>
     			</li>     			
	    </ul>
		</li>

		<li><a class="menutitle" href="#"   tabindex="1"><span>系统监控</span></a>
			<ul>
					<li><a href="selectqmgrforsysinfo.action" tabindex="1">总体资源监控</a></li>
					<li><a href="selectqmgrforbrokerinfo.action" tabindex="1">代理资源监控</a></li>							  
	     </ul>
    </li>

		<li><a class="menutitle" href="#"   tabindex="1"><span>配置管理</span></a>
			<ul>
				  <li class="submenu"><a class="nolink" href="#"   tabindex="1">路由管理</a>
						<ul>		
							<li><a href="listrouteinfo.action" tabindex="1">路由配置</a></li>													  
	     			</ul>
     			</li>
     			<li class="submenu"><a class="nolink" href="#"   tabindex="1">告警信息</a>
						<ul>		
							<li><a href="listalertconditions.action" tabindex="1">告警条件设置</a></li>			
							<li><a href="listalertinfo.action" tabindex="1">告警信息列表</a></li>
							<li><a href="setalertnotification.action" tabindex="1">告警通知设置</a></li>											  
	     			</ul>
     			</li>
     			
     			<li class="submenu"><a class="nolink" href="#"   tabindex="1">流量控制</a>
						<ul>		
							<li><a href="setallcontrol.action" tabindex="1">总量控制</a></li>			
							<li><a href="listpartofcontrol.action" tabindex="1">分量控制</a></li>										  
	     			</ul>
     			</li>     			

     			<li class="submenu"><a class="nolink" href="#"   tabindex="1">超时控制</a>
						<ul>		
							<li><a href="sethttptimeout.action" tabindex="1">HTTP超时</a></li>			
							<li><a href="setsockettimeout.action" tabindex="1">Socket超时</a></li>										  
	     			</ul>
     			</li>  		
     			
     			<li class="submenu"><a class="nolink" href="#"   tabindex="1">IP访问控制</a>
						<ul>		
							<li><a href="permitipaddress.action" tabindex="1">信任IP地址</a></li>												  
	     			</ul>
     			</li>    
     			     						  
	     </ul>
    </li>
        
    <s:if test="#session.groups=='Administrators'">	
				<li><a class="menutitle" href="#"   tabindex="1"><span>代理管理</span></a>
					<ul>
							<li><a href="listbrokers.action" tabindex="1">代理列表</a></li>
							<li><a href="selectfortopology.action" tabindex="1">部署管理</a></li>
							
		     			<li class="submenu"><a class="nolink" href="#"   tabindex="1">参数配置</a>
								<ul>		
									<li><a href="selectforruntimeparams.action" tabindex="1">运行环境参数</a></li>
									<li><a href="selectforcfgserviceparams.action" tabindex="1">配置服务参数</a></li>
								  <li><a href="createconfigservice.action" tabindex="1">新建配置服务</a></li>																			  
			     			</ul>
		     			</li> 							
			     </ul>			     
		    </li>
		
				<li><a class="menutitle" href="#"   tabindex="1"><span>安全管理</span></a>
					<ul>
							<li><a href="listusers.action" tabindex="1">用户管理</a></li>
							<li><a href="listgroups.action" tabindex="1">用户组管理</a></li>				
							<!-- <li><a href="" tabindex="1">角色管理</a></li>	-->			
							<li><a href="modpwd.action" tabindex="1">密码修改</a></li>
			    </ul>
		    </li>
		    <!--
				<li><a class="menutitle" href="#" tabindex="1"><span>访问控制</span></a>
					<ul>
							<li><a href="" tabindex="1">用户访问控制</a></li>
							<li><a href="" tabindex="1">IP访问控制</a></li>
							<li><a href="" tabindex="1">IP访问记录</a></li>
		    	</ul>
		   	</li>-->
 		</s:if>
 		<s:elseif test="#session.groups=='Users'">
				<li><a class="menutitle" href="#"   tabindex="1"><span>安全管理</span></a>
					<ul>
							<li><a href="modpwd.action" tabindex="1">密码修改</a></li>
			    </ul>
		    </li> 			
 		</s:elseif>	
 		
  </ul>
</div> <!-- menu -->