package com.multithreading.task3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ResourcePool {
	private final BlockingQueue<SimpleDateFormat> pool;
    private final ReentrantLock lock = new ReentrantLock();
    private int createdObjects = 0;
    private int size;

    protected ResourcePool(int size) {
        pool = new ArrayBlockingQueue<>(size, true);
        this.size = size;
    }

    public SimpleDateFormat acquire() throws Exception {
        if (!lock.isLocked()) {
            if (lock.tryLock()) {
                try {
                    ++createdObjects;
                    return createObject();
                } finally {
                    if (createdObjects < size) lock.unlock();
                }
            }
        }
        return pool.take();
    }

    public void recycle(SimpleDateFormat resource) throws Exception {
        pool.add(resource);
    }

    public void createPool() {
        if (lock.isLocked()) {
            for (int i = 0; i < size; ++i) {
                pool.add(createObject());
                createdObjects++;
            }
        }
    }

    private SimpleDateFormat createObject() {
        return new SimpleDateFormat("yyyyMMdd");
    }

    public Date convert(String input) throws Exception {
        SimpleDateFormat format = acquire();
        try {
            return format.parse(input);
        } finally {
            recycle(format);
        }
    }
}
