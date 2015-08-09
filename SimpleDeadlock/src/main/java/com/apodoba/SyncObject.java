package com.apodoba;

public class SyncObject {
	private int value = 0;

	synchronized void setValue(int value) {
		this.value = value;
	}

	synchronized int getValue() {
		return value;
	}

	public synchronized boolean equals(Object o) {
		SyncObject syncObject = (SyncObject) o;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			System.err.println("Interrupted!");
		}
		return value == syncObject.getValue();
	}
}
