package com.multithreading.task4.second;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Task {

    public static void main(String args[]){
  
     List<Integer> values = new ArrayList<Integer>();
     Semaphore semaphore = new Semaphore(1);
 
     Thread producerThread = new Producer(values, semaphore);
     Thread consumerThread = new Consumer(values, semaphore);

     producerThread.start();
     consumerThread.start();
    }
}
