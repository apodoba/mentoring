package com.apodoba;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Reader implements Runnable {

	private BlockingQueue<String> queue;
	private BufferedReader reader;

	public Reader(BufferedReader reader, BlockingQueue<String> queue) {
		this.queue = queue;
		this.reader = reader;
	}

	@Override
	public void run() {
		while (true) {
			String line = "";
			try {
				synchronized (reader) {
					line = reader.readLine();
				}
				if (line != null && !line.isEmpty()) {
					String toSave = line.substring(0, 3);
					System.out.println("Read thread "
							+ Thread.currentThread().getName() + " line "
							+ toSave);
					queue.add(toSave);
					//wait(20000);
				} else {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
