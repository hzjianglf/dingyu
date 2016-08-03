<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.*,com.ibm.hibernate.*, com.opensymphony.xwork2.util.*" %>
<%@taglib prefix="s" uri="/struts-tags" %>
	
<script type="text/javascript" src='scripts/customAction.js'></script>
<div class="navtree">
<script type="text/javascript" src='scripts/navTree.js'></script>
			  
			  <!-- 代理监控 -->
			  <div class="navigationsection">
	        <div id="brokermonitor" class="main-task">	      	      
	            <a href="javascript:navToggle('brokermonitor')" id="link_brokermonitor" title="展开" class="blacknounderline" tabindex="1">
	              <img id="img_brokermonitor" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
	              代理监控
	  	        </a>			      			      
	  			</div>

		      <div id="child_brokermonitor" class="nav-child-container collapsed">						
						<div id="monitorrecord" class="sub-task">          
            	<a href="javascript:navToggle('monitorrecord')" id="link_monitorrecord" title="展开" class="blacknounderline" tabindex="1">
              	<img id="img_monitorrecord" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
              	监控记录
            	</a>

        		</div>
        		<div id="child_monitorrecord" class="sub-child-container collapsed">
							<ul class="nav-child">
        				<li>
        						<a href="searchcurrent.action" tabindex="1">实时监控</a>
								</li> 
        				<li>
										<a href="searchhistory.action" tabindex="1">历史监控</a>
								</li> 
        				<li>
          					<a href="searchfailure.action" tabindex="1">失败监控</a>
								</li> 
								<li>
					      		<a href="querymonitorinfo.action" tabindex="1">监控查询</a>
								</li>
     					</ul>
     				</div>
						
						<div id="monitorstat" class="sub-task">          
            	<a href="javascript:navToggle('monitorstat')" id="link_monitorstat" title="展开" class="blacknounderline" tabindex="1">
              	<img id="img_monitorstat" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
              	统计分析
            	</a>
        		</div>
        		<div id="child_monitorstat" class="sub-child-container collapsed">
							<ul class="nav-child">
        				<li>
        						<a href="getcurrentcharts.action" tabindex="1">实时统计</a>
								</li> 
        				<li>
										<a href="gethistorycharts.action" tabindex="1">历史统计</a>
								</li> 
     					</ul>
     				</div>

						<div id="wsmonitor" class="sub-task">          
            	<a href="javascript:navToggle('wsmonitor')" id="link_wsmonitor" title="展开" class="blacknounderline" tabindex="1">
              	<img id="img_wsmonitor" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
              	服务接口监控
            	</a>
        		</div>
        		<div id="child_wsmonitor" class="sub-child-container collapsed">
							<ul class="nav-child">
        				<li>
        						<a href="call2searchrequest.action" tabindex="1">服务请求</a>
								</li> 
        				<li>
										<a href="call2searchprovide.action" tabindex="1">服务提供</a>
								</li> 
        				<li>
										<a href="mappinginterfaces.action" tabindex="1">服务注册</a>
								</li>								
     					</ul>
     				</div>
     				
   				</div>    	
    		</div> 

    		<!-- 系统监控 -->
			  <div class="navigationsection">			  	
          <div id="systemmonitor" class="main-task">
                <a href="javascript:navToggle('systemmonitor')" id="link_systemmonitor" title="折叠" class="blacknounderline" tabindex="1">
                  <img id="img_systemmonitor" src="theme/standard/images/expanded.gif" alt="折叠" title="折叠"/>
                  系统监控
      	        </a>
      		</div>
	      	<div id="child_systemmonitor" class="nav-child-container expanded">
						<ul class="nav-child">		
						   <li>
						   		<a href="selectqmgrforsysinfo.action" tabindex="1">总体资源监控</a>
							 </li> 
						   <li>
						      <a href="selectqmgrforbrokerinfo.action" tabindex="1">代理资源监控</a>
							 </li> 						 					   
				    </ul>
     			</div>    	
    		</div>

    		<!-- SLA管控 -->    							
			  <div class="navigationsection">			  	
          <div id="slacontrol" class="main-task">
                <a href="javascript:navToggle('slacontrol')" id="link_slacontrol" title="展开" class="blacknounderline" tabindex="1">
                  <img id="img_slacontrol" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
                  配置管理
      	        </a>
      		</div>
      		
					<div id="child_slacontrol" class="nav-child-container collapsed">						

						<div id="route" class="sub-task">          
            	<a href="javascript:navToggle('route')" id="link_route" title="展开" class="blacknounderline" tabindex="1">
              	<img id="img_route" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
              	路由管理
            	</a>
        		</div>        		
        		<div id="child_route" class="sub-child-container collapsed">
							<ul class="nav-child">
        				<li>
        						<a href="listrouteinfo.action" tabindex="1">路由配置</a>
								</li>							 								
     					</ul>
     				</div>
     										
						<div id="alert" class="sub-task">          
            	<a href="javascript:navToggle('alert')" id="link_alert" title="展开" class="blacknounderline" tabindex="1">
              	<img id="img_alert" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
              	告警信息
            	</a>
        		</div>
        		
        		<div id="child_alert" class="sub-child-container collapsed">
							<ul class="nav-child">
        				<li>
        						<a href="listalertconditions.action" tabindex="1">告警条件设置</a>
								</li>								
        				<li>
										<a href="listalertinfo.action" tabindex="1">告警信息列表</a>
								</li> 
        				<li>
        						<a href="setalertnotification.action" tabindex="1">告警通知设置</a>
								</li> 								 								
     					</ul>
     				</div>

						<div id="flowcontrol" class="sub-task">          
            	<a href="javascript:navToggle('flowcontrol')" id="link_flowcontrol" title="展开" class="blacknounderline" tabindex="1">
              	<img id="img_flowcontrol" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
              	流量控制
            	</a>
        		</div>        		
        		<div id="child_flowcontrol" class="sub-child-container collapsed">
							<ul class="nav-child">
				
        				<li>
										<a href="listpartofcontrol.action" tabindex="1">流量控制</a>
								</li> 								 								
     					</ul>
     				</div>
     				
 						<div id="timecontrol" class="sub-task">          
            	<a href="javascript:navToggle('timecontrol')" id="link_timecontrol" title="展开" class="blacknounderline" tabindex="1">
              	<img id="img_timecontrol" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
              	超时控制
            	</a>
        		</div>        		
        		<div id="child_timecontrol" class="sub-child-container collapsed">
							<ul class="nav-child">
        				<li>
        						<a href="sethttptimeout.action" tabindex="1">HTTP超时</a>
								</li>								
        				<li>
										<a href="" tabindex="1">Socket超时</a>
								</li> 								 								
     					</ul>
     				</div>   	
     				
 				<div id="ipcontrol" class="sub-task">          
            	<a href="javascript:navToggle('ipcontrol')" id="link_ipcontrol" title="展开" class="blacknounderline" tabindex="1">
              	<img id="img_ipcontrol" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
              	IP访问控制
            	</a>
        		</div>        		
        		<div id="child_ipcontrol" class="sub-child-container collapsed">
							<ul class="nav-child">
        				<li>
        		         		<a href="permitipaddress.action" tabindex="1">信任IP地址</a>
								</li>									 								
     					</ul>
     			</div>     			
     			     							     				     				
   				</div>       	
    		</div> 
    		    		    		
    		<s:if test="#session.groups=='Administrators'">     	
    		<!-- 代理管理 -->
			  <div class="navigationsection">			  	
          <div id="brokermanage" class="main-task">
                <a href="javascript:navToggle('brokermanage')" id="link_brokermanage" title="展开" class="blacknounderline" tabindex="1">
                  <img id="img_brokermanage" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
                  代理管理
      	        </a>
      		</div>
	      	<div id="child_brokermanage" class="nav-child-container collapsed">
						<ul class="nav-child">		 
						   <li>
						      <a href="listbrokers.action" tabindex="1">代理列表</a>
							 </li>
						   <li>
						      <a href="selectfortopology.action" tabindex="1">部署管理</a>						      
							 </li>							  								 					   
				    </ul>
				    
				    <div id="parametersetting" class="sub-task">          
	            	<a href="javascript:navToggle('parametersetting')" id="link_parametersetting" title="展开" class="blacknounderline" tabindex="1">
	              	<img id="img_parametersetting" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
	              	参数设置
	            	</a>
	        		</div>
	        		<div id="child_parametersetting" class="sub-child-container collapsed">
								<ul class="nav-child">
								   <li>
								      <a href="selectforruntimeparams.action" tabindex="1">运行环境参数</a>
									 </li> 	
								   <li>
								      <a href="selectforcfgserviceparams.action" tabindex="1">配置服务参数</a>
									 </li>
								   <li>
								      <a href="createconfigservice.action" tabindex="1">新建配置服务</a>
									 </li>									 	
	     					</ul>
	     			</div>
	     							    
     			</div>    	
    		</div> 
    	
    		<!-- 安全管理 -->    							
			  <div class="navigationsection">			  	
          <div id="usermanage" class="main-task">
                <a href="javascript:navToggle('usermanage')" id="link_usermanage" title="展开" class="blacknounderline" tabindex="1">
                  <img id="img_usermanage" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
                  安全管理
      	        </a>
      		</div>
	      	<div id="child_usermanage" class="nav-child-container collapsed">
						<ul class="nav-child">		
						   <li>
						   		<a href="listusers.action" tabindex="1">用户管理</a>
							 </li> 
						   <li>
						      <a href="listgroups.action" tabindex="1">用户组管理</a>
							 </li> 
						   <li>
						      <a href="modpwd.action" tabindex="1">密码修改</a>
							 </li> 						   
				    </ul>
     			</div>    	
    		</div> 
    	
    		<!-- 访问控制     							
			  <div class="navigationsection">
	      	<div id="accesscontrol" class="main-task">	      	      
	        		<a href="javascript:navToggle('accesscontrol')" id="link_accesscontrol" title="展开" class="blacknounderline" tabindex="1">
	            	<img id="img_accesscontrol" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
	              访问控制
        	      </a>			     			      
	      	</div>

	      	<div id="child_accesscontrol" class="nav-child-container collapsed">
						<ul class="nav-child">
        			<li>
          			<a href="" tabindex="1">用户访问控制</a>
							</li>
							<li>
          			<a href="" tabindex="1">IP访问控制</a>
							</li>
							<li>
          			<a href="" tabindex="1">IP访问记录</a>
							</li>														
     				</ul>
     			</div>
    		</div> -->
				
				</s:if>
    		<s:elseif test="#session.groups=='Users'">
				  <div class="navigationsection">			  	
	          <div id="usermanage" class="main-task">
	                <a href="javascript:navToggle('usermanage')" id="link_usermanage" title="展开" class="blacknounderline" tabindex="1">
	                  <img id="img_usermanage" src="theme/standard/images/collapsed.gif" alt="展开" title="展开"/>
	                  安全管理
	      	        </a>
	      		</div>
		      	<div id="child_usermanage" class="nav-child-container collapsed">
							<ul class="nav-child">  
							   <li>
							      <a href="modpwd.action" tabindex="1">密码修改</a>
								 </li> 						   
					    </ul>
	     			</div>    	
	    		</div>     			
    		</s:elseif>    		    		    	    		
</div> <!-- navtree -->