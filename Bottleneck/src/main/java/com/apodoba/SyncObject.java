package com.apodoba;


public class SyncObject {
	
	@SuppressWarnings("static-access")
	public void doSomthing(){
		Thread thread = Thread.currentThread();    
		System.out.println("start doing " + thread.getName());
		try {
			thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("stop doing " + thread.getName());
	}
}
