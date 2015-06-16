package com.multithreading.task4.second;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Producer extends Thread {

	 private List<Integer> values;
	 private Semaphore semaphore;

    public Producer(List<Integer> values, Semaphore semaphore) {
    	super("Producer");
        this.values = values;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            try {
            	semaphore.acquire();
                System.out.println("Produced: " + i);
                values.add(i);
                semaphore.release();
            } catch (InterruptedException ex) {
            	ex.printStackTrace();
            }
        }
    }

}