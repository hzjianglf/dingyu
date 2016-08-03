
package com.lmd.sync.rtx;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import net.risesoft.soa.org.util.SyncInterface;

import com.lmd.sync.rtx.service.client.RtxServiceImplServiceLocator;
import com.lmd.sync.rtx.service.client.RtxServiceImplServiceSoapBindingStub;

/**
 * @author soilor2
 *
 */  

public class OrgSyncYdxc implements SyncInterface {
	private String ipaddre;

	public String getIpaddre() {
		return ipaddre;
	}

	public void setIpaddre(String ipaddre) {
		this.ipaddre = ipaddre;
	}

	public boolean getLink(String link) {
		if (link == null || link.length() == 0){
			return false;
		}
		String[] links = link.split(";");
		for (int i = 0; i < links.length; i++) {
			String string = links[i];
			System.out.println(string+"--getlink()------sh_ydxc-----");
		}
		setIpaddre(links[0]);
		System.out.println("ipaddre:"+getIpaddre()+"--");
		boolean isConn = true;
        return isConn;
		
	}
	int ixp=0;
	public boolean synchronize(String eventType, String data, String targetUID) {
		boolean sync = false;
		try {
			ixp=ixp+1;
			//System.out.println("------"+ixp+"-----11---");
			OrgSyncYdxcImpl osi = new OrgSyncYdxcImpl();
			sync = osi.sync(eventType, data, targetUID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sync;
	}

	public static void main(String[] args) throws ServiceException, RemoteException {
		
		for(int i=0;i<1000;i++){
			RtxServiceImplServiceLocator rs = new RtxServiceImplServiceLocator();
			RtxServiceImplServiceSoapBindingStub uservice = (RtxServiceImplServiceSoapBindingStub)rs.getRtxServiceImplPort();
			//，将 平台的父id作为参数传入，从文件中获取对应的rtx的id
				String status = uservice.addUser("lisan"+i, 1+"", "name", "111111");//密码都默认设置为111111
				if(status.equals("success")){
					System.out.println("lisan"+i+" 人员添加成功");
				}else{
					System.out.println("lisan"+i+ "  人员添加失败");
				}
		}
	}

}


