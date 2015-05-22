package com.gc;

public class Leak6 {
	public class FinalizeObj {
		int s = 0;
		FinalizeObj finalizeObj = new FinalizeObj();
		Object obj = new Object();

		@Override
		protected void finalize() throws Throwable {
			Object tempObj = this;
			System.out.println(tempObj);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			Leak6 main = new Leak6();
			Object obj = main.new FinalizeObj();
			System.out.println(obj);
		}
	}
}
