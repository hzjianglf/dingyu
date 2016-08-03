
package com.lmd.sync;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import net.risesoft.soa.org.util.SyncInterface;

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
			System.out.println("------"+ixp+"--------");
			OrgSyncYdxcImpl osi = new OrgSyncYdxcImpl();
			sync = osi.sync(eventType, data, targetUID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sync;
	}

	public static void main(String[] args) {
		
	}

}


