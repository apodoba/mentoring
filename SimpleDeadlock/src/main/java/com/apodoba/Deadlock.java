package com.apodoba;

public class Deadlock implements Runnable {

	static int nextId = 1;

	private SyncObject obj1;
	private SyncObject obj2;
	private int id = 0;

	public Deadlock(SyncObject obj1, SyncObject obj2) {
		this.obj1 = obj1;
		this.obj2 = obj2;
		id = nextId++;
	}

	public void run() {
		print("Setting value to obj1... ");
		obj1.setValue(id);
		print("done.");
		print("Comparing objects... ");
		print("Done. Result: " + ((obj1.equals(obj2)) ? "equal" : "not equal"));
	}

	private void print(String msg) {
		System.out.println("Thread #" + id + ": " + msg);
	}
}
