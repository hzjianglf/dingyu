package com.lmd.sync.rtx;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class WriteHandlerThread extends Thread{

	private Socket client;
	private String writeStr;
	private String status;
	public WriteHandlerThread(Socket client,String writeStr) {
		this.client = client;
		this.writeStr = writeStr;
	}
	public void run() {
		DataOutputStream dos = null;
		BufferedReader br = null;
		DataInputStream dis = null;
		try {
//			while(true){
				//取得输出流
				dos = new DataOutputStream(client.getOutputStream());
				dos.writeUTF(this.writeStr);  
				dis = new DataInputStream(client.getInputStream());
				String receive = dis.readUTF();   
//				SocketClient.handler(receive);
				this.status=receive;
				System.out.println("服务器端返回过来的是: " + receive);  
//			}
		} catch (IOException e) {
			this.status="error";
//			SocketClient.handler("error");
			e.printStackTrace();
		}  finally{
			try{
				if(dos != null){
					dos.close();
				}
				if(br != null){
					br.close();
				}
				if(client != null){
					client = null;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public String returnRecive(){
		return this.status;
	}

}
