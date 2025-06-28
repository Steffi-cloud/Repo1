package com.example.demo.reentrantlock;

public class TimedLockDemo {
	 public static void main(String[] args) throws InterruptedException {
	        TimedLockExample obj = new TimedLockExample();

	        Thread t1 = new Thread(obj::doTimedWork, "Thread-1");
	        Thread t2 = new Thread(obj::doTimedWork, "Thread-2");

	        t1.start();
	        Thread.sleep(1000); // Delay to cause contention
	        t2.start();
	    }
}
