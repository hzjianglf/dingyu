1.环境部署。
(1)本地搭建项目环境，mysql5.0+eclipse j2ee版+tomcat6.0（eclipse下载地址http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/junosr2，根据操作系统选择合适版本）
(2)使用eclipse新建一个web项目，将src和webroot文件夹拷入新项目；JDK选择WMB自带JDK，路径为：安装路径\WMBT800\jdk；语言设置为gbk。
(3) 根据本地数据库，修改数据库配置文件hibernate.cfg.xml，数据库的生成，可使用提供的sql文件，或者运行项目中ExportDB.java，然后在user_info表中添加初始登录的用户信息。
(4)新建服务器，部署项目，启动服务器，在浏览器（非ie）输入http://localhost:8090/项目名称/login.jsp可进入登录页面。



2.ChangeList
一、代理列表
    1、增加"监控线程数"一列，在启动监控时可以选择监控所使用的线程数
二、部署管理
    1、增加选择application的下拉框，展示消息流时可以根据应用来选择展示。
    2、部署应用，只要bar里已经有application，部署该bar即可部署应用
    3、删除应用，选择应用，并直接删除应用，或者选择删除应用中的消息流时也会提示此操作将删除应用
    4、设置实例数，原有代码逻辑有误，已做修改（使用虚拟机上已部署好的mmplus无此问题，应该是已经修改好了）
三、代理监控
    1、方正提出Monitor_main和Monitor_message_flow表记录缺失和Monitor_message_flow出现其他flowname的异常，
    回来使用perfharness测试后发现：
    ·问题一确实存在，经排查发现，原因有二：
    （1）MonitorUtils.getLocalTime函数，会出现参数strTime长度小于23的情况，导致strTime.substring(0,23)异		常，出现情况为毫秒数为0，例如正常情况下时间格式为2013-04-09T07:22:58.000999Z，而偶尔时间格式为			2013-04-09T07:22:58Z。原有逻辑未做区分，统一按正常格式处理，因此增加入参检测和补全。
    （2）MBMONITORDATAQ队列里极少情况下会出现队列消息事件顺序颠倒的情况，比如正常情况是
	transaction.start->transaction.end，原有逻辑是在start事件时执行save，在end事件时执行update，此时		工作正常。可当事件顺序颠倒即transaction.end->transaction.start时（概率一万条记录出现十几次），原		有逻辑先执行update，会出现异常。因此在update或save之前增加判断如果记录存在则update，否则save。
    ·问题二暂未发现，可能是因为jmsconsumer类方正那边是旧的
    2、监控多线程的实现。
       根据代理列表中所设置的监控线程数开启多线程监控，为其中操作数据库的代码段增加线程锁，实现互斥。
    3、修改了startmonitor和stopmonitor函数，以使其支持多线程功能

   ----------------------------------------------------------------------------------------------------
   2013-7-18
   修改监控后台，解决bug：监控到失败的消息流后，即使消息流运行成功，监控记录依然记录为失败
   ----------------------------------------------------------------------------------------------------
附：修改文件列表
src/struts.xml	
src/com/ibm/paser/PaserMonitorMsg.java	
src/com/ibm/hibernate/BrokerInfo.java	
src/com/ibm/hibernate/MonitorMainDAO.java	
src/com/ibm/hibernate/MonitorMain.hbm.xml	
src/com/ibm/hibernate/BrokerInfo.hbm.xml	
src/com/ibm/hibernate/BrokerInfoDAO.java	
src/com/ibm/hibernate/MonitorMain.java	
src/com/ibm/struts/BrokerTopologyAction.java	
src/com/ibm/struts/DeployBarAction.java	
src/com/ibm/struts/ListBrokersAction.java	
src/com/ibm/paser/JmsConsumer.java	
src/com/ibm/paser/MonitorUtils.java	
WebRoot/displayTopology.jsp	
WebRoot/submitSuccess.jsp	
WebRoot/listBrokers.jsp	
WebRoot/createEG.jsp	
WebRoot/deployBars.jsp	
WebRoot/submitError.jsp	