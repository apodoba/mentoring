package com.multithreading.task4.first;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task {

    public static void main(String args[]){
  
     BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
 
     Thread producerThread = new Producer(queue);
     Thread consumerThread = new Consumer(queue);

     producerThread.start();
     consumerThread.start();
    }
}
