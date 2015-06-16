package com.multithreading.task3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task {
	public static void main(String args[]) {
        final ResourcePool pool = new ResourcePool(2);

        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return pool.convert("20150706");
            }
        };

        ExecutorService exec = Executors.newFixedThreadPool(5);
        List<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            results.add(exec.submit(task));
        }
        exec.shutdown();
        try {
            for (Future<Date> result : results) {
                System.out.println(result.get());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
