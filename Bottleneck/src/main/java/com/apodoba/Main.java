package com.apodoba;

public class Main {

	public static void main(String [] args){
		SyncObject obj = new SyncObject();
		
		Thread deadlock1 = new Thread(new Bottleneck(obj, "thead 1"));
		Thread deadlock2 = new Thread(new Bottleneck(obj, "thead 2"));
		Thread deadlock3 = new Thread(new Bottleneck(obj, "thead 3"));
		Thread deadlock4 = new Thread(new Bottleneck(obj, "thead 4"));
		Thread deadlock5 = new Thread(new Bottleneck(obj, "thead 5"));
		
		deadlock1.start();
		deadlock2.start();
		deadlock3.start();
		deadlock4.start();
		deadlock5.start();
	}
}
