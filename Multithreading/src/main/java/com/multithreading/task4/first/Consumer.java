package com.multithreading.task4.first;

import java.util.concurrent.BlockingQueue;

class Consumer extends Thread{

    private BlockingQueue<Integer> queue;

    public Consumer (BlockingQueue<Integer> queue) {
    	super("Consumer");
        this.queue = queue;
    }
  
    @Override
    public void run() {
        do{
            try {
                System.out.println("Consumer: "+ queue.take());
            } catch (InterruptedException ex) {
            	ex.printStackTrace();
            }
        }while(!queue.isEmpty());
    }
  
  
}