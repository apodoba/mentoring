package com.gc;

import java.util.ArrayList;
import java.util.List;

class Leak4 {
	static final int MB = 1024 * 512;

	static String createLongString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append('a');
		sb.append(System.nanoTime());
		return sb.toString();
	}

	public static void main(String[] args) {
		List<String> substrings = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			String longStr = createLongString(MB);
			String subStr = longStr.substring(1, 10);
			substrings.add(subStr);
		}
	}
}