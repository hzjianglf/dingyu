package com.lmd.sso.ticket;

public class Runtest {
	public static void main(String[] args) {
		Test1 t = new Test1();
		t.start();
		while (true) {
			try {
				t.sleep(3000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
//			t.interrupt();
			System.out.println(t.isAlive());
		}
	}
}


class Test1 extends Thread{
	
//	Test1(){
//		this.run();
//	}
	
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
		System.out.println("正在启动");
		try {
			this.sleep(5000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		}
	}
}
