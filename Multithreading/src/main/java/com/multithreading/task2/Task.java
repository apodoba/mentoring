package com.multithreading.task2;

public class Task {
	public static void main(String[] args) {
		
		DataStructure<String> threadSafeArrayList = new DataStructure<String>();
		
		new WriteThread("Write 1", threadSafeArrayList);
		new ReadThread("Read 1", threadSafeArrayList);
		new WriteThread("Write 2", threadSafeArrayList);
		new WriteThread("Write 3", threadSafeArrayList);
		new ReadThread("Read 2", threadSafeArrayList);
		new ReadThread("Read 3", threadSafeArrayList);
		new WriteThread("Write 4", threadSafeArrayList);
		
		
		new ReadThread("Read 4", threadSafeArrayList);
		new WriteThread("Write 5", threadSafeArrayList);
		new ReadThread("Read 5", threadSafeArrayList);
		
		
	}

}
