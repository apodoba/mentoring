package com.apodoba;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(200000);

		String folderPath = "/home/arina/projects/Reader";
		Path path = Paths.get(folderPath, "Task #5 - Data.txt");
		Charset charset = Charset.forName("UTF-8");
		try {
			BufferedReader reader = Files.newBufferedReader(path, charset);

			Thread thread1 = new Thread(new Reader(reader, queue), "1");
			Thread thread2 = new Thread(new Reader(reader, queue), "2");
			Thread thread3 = new Thread(new Reader(reader, queue), "3");
			Thread thread4 = new Thread(new Reader(reader, queue), "4");
			Thread thread5 = new Thread(new Reader(reader, queue), "5");
			Thread thread6 = new Thread(new Reader(reader, queue), "6");

			thread1.start();
			thread2.start();
			thread3.start();
			thread4.start();
			thread5.start();
			thread6.start();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
