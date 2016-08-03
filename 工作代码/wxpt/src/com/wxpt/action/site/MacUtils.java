package com.wxpt.action.site;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class MacUtils {

	public static String getMac() {
		try {

			Process process = Runtime.getRuntime().exec("ipconfig /all");

			InputStreamReader ir = new InputStreamReader(
					process.getInputStream(), "GBK");

			LineNumberReader input = new LineNumberReader(ir);

			String line;
			List<String> list = new ArrayList<String>();
			while ((line = input.readLine()) != null) {
				System.out.println(line);
				list.add(line);
			}
			System.out.println(list.size());
			String Mac = null;
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
				/*
				 * if(list.get(i).indexOf("物理地址")>0||list.get(i).indexOf(
				 * "Physical Address")>0){ Mac=list.get(i); break; }
				 */
				if (list.get(i).indexOf("Physical Address") > 0
						|| list.get(i).indexOf("物理地址") > 0) {

					String MACAddr = list.get(i).substring(
							list.get(i).indexOf("-") - 2);
					Mac = MACAddr;
					return Mac;
					/* System.out.println("MAC address = [" + MACAddr + "]"); */
					// break;
				}

			}
			/*
			 * String []MAC=Mac.split(":"); System.out.println(MAC[1]);
			 */

		} catch (java.io.IOException e) {

			System.err.println("IOException " + e.getMessage());

		}
		return null;
	}
}