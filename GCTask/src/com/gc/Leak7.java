package com.gc;

import java.util.Map;
import java.util.WeakHashMap;

public class Leak7 {
	public static void main(String[] args) {
		Map<Object, Object> objectMap = new WeakHashMap<Object, Object>();
		for (int i = 0; i < 1000; i++) {
		    objectMap.put(i, new Object());
		    System.out.println("Map size :" + objectMap.size());
		}
	}
}
