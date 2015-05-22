package com.gc;

import java.util.HashMap;
import java.util.Map;

public class Leak10 {
	private Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

	public int square(int i) {
		int result = i * i;
		cache.put(i, result);
		return result;
	}

	public static void main(String[] args) throws Exception {
		Leak10 leak = new Leak10();
		int index = 0;
		while (true){
			System.out.println("Answer " + leak.square(index++));
		}		
	}
}
