package com.multithreading.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataStructure<T> {
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock lock = readWriteLock.writeLock();
	private final List<T> list = new ArrayList<T>();

	public void add(T value) {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " set lock");
		try {
			if(list.size()<10){
				System.out.println("Add value " + value + " by "+Thread.currentThread().getName());
				list.add(value);
			}else {
				int randomIndex = new Random().nextInt(list.size()-1 + 1);
				System.out.println("Change value " + list.get(randomIndex) + " to " + value + " by "+Thread.currentThread().getName());
				list.remove(randomIndex);
				list.add(value);
			}
		} finally {
			System.out.println(Thread.currentThread().getName() + " delete lock");
			lock.unlock();
		}
	}

	public void get() {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " set lock");
		try {
			if(!list.isEmpty()){
				int randomIndex = new Random().nextInt(list.size()-1 + 1);
				System.out.println("Value " + list.get(randomIndex) + " read by "+Thread.currentThread().getName()); 
			}
		} finally {
			System.out.println(Thread.currentThread().getName() + " delete lock");
			lock.unlock();
		}
	}
}
