package com.multithreading.task1;

public class Task {

	public static void main(String[] args) throws InterruptedException {
		Service service = new Service();
		service.startService();
		
		new HandlerThread(service, "One");
		new HandlerThread(service, "Two");
		new HandlerThread(service, "Three");
		new HandlerThread(service, "Four");
		new HandlerThread(service, "Five");
		new HandlerThread(service, "Six");
		new HandlerThread(service, "Seven");
		new HandlerThread(service, "Eight");
		new HandlerThread(service, "Nine");
		new HandlerThread(service, "Ten");
		new HandlerThread(service, "Eleven");
		

		Thread.sleep(1000);
		
		System.out.println("Call stop");
		service.stopService();
		
		new HandlerThread(service, "Twelve");
		new HandlerThread(service, "Thirteen");
		
	}
}
