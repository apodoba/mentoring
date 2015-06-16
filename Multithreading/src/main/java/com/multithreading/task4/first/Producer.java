package com.multithreading.task4.first;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {

    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
    	super("Producer");
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            try {
                System.out.println("Producer: " + i);
                queue.put(i);
            } catch (InterruptedException ex) {
            	ex.printStackTrace();
            }
        }
    }

}