package com.multithreading.task2;

public class ReadThread extends Thread{
	
	private DataStructure<String> dataStructure;
	
	public ReadThread(String name, DataStructure<String> dataStructure) {
		super(name);
		this.dataStructure = dataStructure;
		start();
	}
	
	@Override
	public void run() {
		dataStructure.get();
	}

}
