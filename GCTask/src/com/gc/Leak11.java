package com.gc;

import java.util.Map;

public class Leak11 {
	public final String key;

	public Leak11(String key) {
		this.key = key;
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10000; i++) {
			Map<Object, Object> map = System.getProperties();
			map.put(new Leak11("key"+i), "value");
		}		
	}
}