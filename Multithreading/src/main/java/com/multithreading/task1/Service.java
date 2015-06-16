package com.multithreading.task1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Service extends Thread {
	
	private boolean isStopped;

	private BlockingQueue<Handler> queue = new LinkedBlockingQueue<Handler>();

	public void addHandler(Handler handler) {
		if(!isStopped){
			System.out.println("Add handler "+handler.getValue());
			queue.add(handler);
		}else {
			System.out.println("Could not add handler");
		}
	}

	public void startService() {
		System.out.println("Start Service");
		this.start();
	}

	public void stopService() {
		System.out.println("Called stop service");
		this.interrupt();
	}

	@Override
	public void run() {
		while (!this.isInterrupted()) {
			while (!queue.isEmpty()) {
				System.out.print("Handler execute: ");
				queue.poll().execute();
			}
		}
		isStopped = true;
		while (!queue.isEmpty()) {
			System.out.print("Handler execute: ");
			queue.poll().execute();
		}
		System.out.println("Stopped service");
	}
}
