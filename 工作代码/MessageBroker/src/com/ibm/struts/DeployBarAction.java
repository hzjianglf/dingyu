package com.ibm.struts;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.broker.config.proxy.BrokerConnectionParameters;
import com.ibm.broker.config.proxy.BrokerProxy;
import com.ibm.broker.config.proxy.ConfigManagerProxyException;
import com.ibm.broker.config.proxy.DeployResult;
import com.ibm.broker.config.proxy.ExecutionGroupProxy;
import com.ibm.broker.config.proxy.MQBrokerConnectionParameters;
import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class DeployBarAction extends ActionSupport {

	private static final Logger log = LoggerFactory.getLogger(DeployBarAction.class);
	
	private static final long serialVersionUID = 1L;
	Map<String, String> qmgrMap = new LinkedHashMap<String, String>();
	Map<String, String> egMap = new LinkedHashMap<String, String>();
	private String qmgrname;
	private String brokername;	
	private String getFilePath;
	private String selectedExecutionGroup;
	private String comment1;	
	private String title = "消息流部署";	
	private String viewdescription = "在该页面用户可以查看部署消息流的状态。";	
	private String operation = "deploybars";	
	BrokerInfoDAO dao = new BrokerInfoDAO();
	private String appname;
	/*****************文件上传*********************/
	JakartaMultiPartRequest f;
	private File imgFile;
	private String imgFileFileName;
	private String caption;		
	
	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}
		
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
    
	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	/*********************************************/
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getViewdescription() {
		return viewdescription;
	}

	public void setViewdescription(String viewdescription) {
		this.viewdescription = viewdescription;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public String getGetFilePath() {
		return getFilePath;
	}

	public void setGetFilePath(String getFilePath) {
		this.getFilePath = getFilePath;
	}

	public String getSelectedExecutionGroup() {
		return selectedExecutionGroup;
	}

	public void setSelectedExecutionGroup(String selectedExecutionGroup) {
		this.selectedExecutionGroup = selectedExecutionGroup;
	}
	
	public String getBrokername() {
		return brokername;
	}

	public void setBrokername(String brokername) {
		this.brokername = brokername;
	}

	public Map<String, String> getEgMap() {
		return egMap;
	}

	public void setEgMap(Map<String, String> egMap) {
		this.egMap = egMap;
	}

	public String getQmgrname() {
		return qmgrname;
	}

	public void setQmgrname(String qmgrname) {
		this.qmgrname = qmgrname;
	}

	public Map<String, String> getQmgrMap() {
		return qmgrMap;
	}

	public void setQmgrMap(Map<String, String> qmgrMap) {
		this.qmgrMap = qmgrMap;
	}

	/**
	 * @return
	 */
	public String queryBrokerByQmgr() {
		String flag = null;
		
		if (!qmgrname.equalsIgnoreCase("")) {
			BrokerInfo broker = new BrokerInfo();
			broker = (BrokerInfo) dao.findByQmgrname(qmgrname).iterator().next();
			brokername = broker.getBname(); // Get
			setQmgrname(qmgrname);
			setAppname(appname);
			
			BrokerProxy b = connect(broker.getHostname(), new Integer(
					broker.getPort()).intValue(), broker.getQmgrname());
			if (b != null) {
				try {
					egMap = getExecutionGroup(b);
					flag = SUCCESS;					
				} catch (ConfigManagerProxyException e) {
					//e.printStackTrace();
					comment1 = "获取消息代理 " + broker.getBname() + " 信息失败，请重试。";
					operation = "returntopology";
					log.error(comment1);
					flag = ERROR;
				} finally {
					if (b != null) {
						b.disconnect();
					}									
				}
			}
		} else {
			comment1 = "队列管理器为空，请重试。";
			operation = "returntopology";
			flag = ERROR;
		}

		return flag;
	}

	public String submitDeployment() {
		operation = "deploybars";
		BrokerInfo broker = new BrokerInfo();
		// 路径不能为空
		if (this.imgFileFileName != null ) {
			//int strLen = this.imgFileFileName.length();
			//int byteLen = this.imgFileFileName.getBytes().length;			
			//文件路径或文件名中不能有中文
			//if (strLen == byteLen) {							
			if (this.imgFileFileName.contains(".bar")) {
				broker = (BrokerInfo) dao.findByQmgrname(qmgrname).iterator().next();
				File imageFile = new File(ServletActionContext.getServletContext()
						.getRealPath("/upload") + "/" + this.imgFileFileName);		
				
				//System.out.println(ServletActionContext.getServletContext().getContextPath());
				//System.out.println(ServletActionContext.getServletContext().getRealPath("/data"));
				copy(this.imgFile, imageFile, imgFile.length());				
				String result = deployBAR(broker.getHostname(), new Integer(broker.getPort()).intValue(), qmgrname, selectedExecutionGroup, imageFile.getPath(), 60);
				//String result = null;
				if (result == null) {
					comment1 = "代理 " + broker.getBname() + " 或执行组 " + selectedExecutionGroup + " 已停止，部署失败，请重新部署!";
					log.error(comment1);
					return ERROR;			
				} else if (result.equalsIgnoreCase("success")) {
					comment1 = "代理 " + broker.getBname() + " 执行组 " + selectedExecutionGroup + " 消息流部署成功。";
					log.error(comment1);
					return SUCCESS;
				} else {
					comment1 = "代理 " + broker.getBname() + " 执行组 " + selectedExecutionGroup + "部署失败，请重新部署!";
					log.error(comment1);
					return ERROR;	
				}
			
			} else {
				comment1 = "请部署正确的Bar文件。";
				return ERROR;
			}
				
//			} else {
//				comment1 = "路径或文件名中不能有中文。";
//				return ERROR;				
//			}
			
		} else {
			comment1 = "代理归档文件路径不能为空。";
			return ERROR;			
		}
	}

	private BrokerProxy connect(String hostname, int port, String qmgrname) {
		BrokerProxy b = null;
		BrokerConnectionParameters bcp = new MQBrokerConnectionParameters(
				hostname, port, qmgrname);

		try {
			b = BrokerProxy.getInstance(bcp);

			boolean brokerIsResponding = b.hasBeenPopulatedByBroker(true);

			if (brokerIsResponding) {
				// System.out.println("Broker is running");
			} else {
				// System.out.println("Broker is Stopped");
				b.disconnect();
				b = null;
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return b;
	}

	private Map<String, String> getExecutionGroup(BrokerProxy b)
			throws ConfigManagerProxyException {

		Map<String, String> egMap = new LinkedHashMap<String, String>();

		Enumeration<ExecutionGroupProxy> allEGsInThisBroker = b
				.getExecutionGroups(null);
		if (allEGsInThisBroker.hasMoreElements()) {

			while (allEGsInThisBroker.hasMoreElements()) {
				ExecutionGroupProxy thisEG = allEGsInThisBroker.nextElement();

				boolean isEgRunning = thisEG.isRunning();
				if (isEgRunning) {
					String executionGroupName = thisEG.getName();
					egMap.put(executionGroupName, executionGroupName);
				}
			}

		}

		return egMap;
	}

	public String deployBAR(String brokerHostName, int brokerPort,
			String brokerQmgrName, String executionGroupName,
			String barFileName, int timeoutSecs) {
		
		String result = null;
		BrokerConnectionParameters bcp = new MQBrokerConnectionParameters(
				brokerHostName, brokerPort, brokerQmgrName);
		BrokerProxy b = null;

		try {
			b = BrokerProxy.getInstance(bcp);

			// Has the broker responded to the connection attempt?
			if (!b.hasBeenPopulatedByBroker(true)) {
				System.out.println("Broker is not responding.");
			} else {
				ExecutionGroupProxy eg = b
						.getExecutionGroupByName(executionGroupName);

				// If the execution group exists, deploy to it.
				if (eg == null) {
					//System.out.println("Execution group not found");
				} else {
					// Deploy the BAR file and display the result
					//System.out.println("Deploying " + barFileName + "...");
					try {
						DeployResult deployResult = eg.deploy(barFileName, true, timeoutSecs * 1000); 

						//System.out.println("Result = " + deployResult.getCompletionCode());
						result = deployResult.getCompletionCode().toString();
					} catch (Exception ioEx) {
						// e.g. if BAR file doesn't exist
						log.error(ioEx.getMessage());
					}

				}

			}
		} catch (ConfigManagerProxyException cmpEx) {
			log.error(cmpEx.getMessage());
		} finally {
			if (b != null) {
				b.disconnect();
			}
		}
		
		return result;
	}
	
	/*****************文件上传*************************************************************************/
	private void copy(File src, File dst, long length) {
		try  {
            InputStream in = null ;
            OutputStream out = null ;
             try  {                
                in = new BufferedInputStream( new FileInputStream(src), new Long(length).intValue());
                out = new BufferedOutputStream( new FileOutputStream(dst), new Long(length).intValue());
                 byte [] buffer = new byte [new Long(length).intValue()];
                 while (in.read(buffer) > 0 )  {
                    out.write(buffer);
                } 
             } finally  {
                 if ( null != in)  {
                    in.close();
                } 
                  if ( null != out)  {
                    out.close();
                } 
            } 
         } catch (Exception e)  {
            log.error(e.getMessage());
        } 

	}	
	
}