package com.apodoba;

public class Bottleneck implements Runnable {

	private String threadName;
	private SyncObject syncObject;

	public Bottleneck(SyncObject syncObject, String threadName) {
		this.syncObject = syncObject;
		this.threadName = threadName;
	}

	public void run() {
		System.out.println("syncObject:: Thread started");
		Thread thread = Thread.currentThread();
		thread.setName(threadName);
		synchronized (syncObject) {
			System.out.println("syncObject:: Waiting");
			syncObject.doSomthing(); 
			System.out.println("syncObject:: Running again");
		}
	}
}
