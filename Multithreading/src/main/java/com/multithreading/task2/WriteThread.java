package com.multithreading.task2;

public class WriteThread extends Thread{
	
	private DataStructure<String> dataStructure;
	
	public WriteThread(String name, DataStructure<String> dataStructure) {
		super(name);
		this.dataStructure = dataStructure;
		start();
	}
	
	@Override
	public void run() {
		dataStructure.add(getName());
	}

}
