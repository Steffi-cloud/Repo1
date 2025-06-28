package com.example.demo.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class SharedCounter {
	   private int count = 0;
	    private final ReentrantLock lock = new ReentrantLock();

	    public void increment() {
	        lock.lock(); // Acquire the lock
	        try {
	            count++;
	            System.out.println(Thread.currentThread().getName() + " incremented count to: " + count);
	        } finally {
	            lock.unlock(); // Always release the lock
	        }
	    }

	    public int getCount() {
	        return count;
	    }
}
