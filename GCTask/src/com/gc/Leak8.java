package com.gc;

public class Leak8 {
	public static void main(String args[]) {
		Leak8 main = new Leak8();
		main.new NewThread("One");
		main.new NewThread("Two");
		main.new NewThread("Three");
		main.new NewThread("Four");
		main.new NewThread("Five");
		main.new NewThread("Six");
		main.new NewThread("Seven");
		main.new NewThread("Eight");
		main.new NewThread("Nine");
		main.new NewThread("Ten");
		main.new NewThread("Eleven");

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("Главный поток прерван");
		}
		System.out.println("Главный поток завершён");
	}

	class NewThread implements Runnable {
		Thread t;
		String name;

		NewThread(String Threadname) {
			name = Threadname;
			t = new Thread(this, name);
			System.out.println("Новый поток создан " + name);
			t.start();
		}

		public void run() {
			try {
				for (int i = 10000; i > 0; --i) {
					System.out.println(name + i);
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				System.out.println("Поток " + name + " прерван");
			}
			System.out.println(name + "поток завершён");
		}
	}

}
