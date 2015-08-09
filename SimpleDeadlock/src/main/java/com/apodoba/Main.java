package com.apodoba;

public class Main {

	public static void main(String[] args) {
		SyncObject a1 = new SyncObject();
		SyncObject a2 = new SyncObject();
		Thread t1 = new Thread(new Deadlock(a1, a2));
		Thread t2 = new Thread(new Deadlock(a2, a1));
		t1.start();
		t2.start();

	}

}
