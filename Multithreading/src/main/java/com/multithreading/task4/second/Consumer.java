package com.multithreading.task4.second;

import java.util.List;
import java.util.concurrent.Semaphore;

class Consumer extends Thread{

    private List<Integer> values;
    private Semaphore semaphore;

    public Consumer (List<Integer> values, Semaphore semaphore) {
    	super("Consumer");
        this.values = values;
        this.semaphore = semaphore;
    }
  
    @Override
    public void run() {
        while(true){
            try {
            	semaphore.acquire();
            	if(!values.isEmpty()){
            		System.out.println("Consumer: "+ values.get(0));
            		values.remove(0);
            	}
            	semaphore.release();
            } catch (InterruptedException ex) {
            	ex.printStackTrace();
            }
        }
    }
}