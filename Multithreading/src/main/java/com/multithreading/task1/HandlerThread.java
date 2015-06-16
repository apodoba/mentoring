package com.multithreading.task1;

public class HandlerThread implements Runnable{
	
	private final Service service;
	private final String value;
	
	public HandlerThread(Service service, String value) {
		this.service = service;
		this.value = value;
		Thread thread = new Thread(this, value);
		thread.start();
	}

	@Override
	public void run() {
		service.addHandler(new HandlerImpl(value));
	}

}
