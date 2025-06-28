package com.example.demo.reentrantlock;

public class ReentrantLockExample {
	 public static void main(String[] args) throws InterruptedException {
	        SharedCounter counter = new SharedCounter();

	        Runnable task = () -> {
	            for (int i = 0; i < 10; i++) {
	                counter.increment();
	            }
	        };

	        Thread t1 = new Thread(task, "Thread-1");
	        Thread t2 = new Thread(task, "Thread-2");

	        t1.start();
	        t2.start();

	        t1.join();
	        t2.join();

	        System.out.println("Final Count: " + counter.getCount());
	    }
}
