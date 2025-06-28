package com.example.demo.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimedLockExample {
	 private final ReentrantLock lock = new ReentrantLock();

	    public void doTimedWork() {
	        try {
	            if (lock.tryLock(10, TimeUnit.SECONDS)) {
	                try {
	                    System.out.println(Thread.currentThread().getName() + " acquired lock");
	                    Thread.sleep(10000); // Simulate longer task
	                } finally {
	                    lock.unlock();
	                    System.out.println(Thread.currentThread().getName() + " released lock");
	                }
	            } else {
	                System.out.println(Thread.currentThread().getName() + " could not get the lock in time");
	            }
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	            System.out.println(Thread.currentThread().getName() + " was interrupted");
	        }
	    }
}
