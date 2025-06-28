package com.example.demo.reentrantlock;

public class InterruptibleLockDemo {
	public static void main(String[] args) throws InterruptedException {
        InterruptibleLockExample obj = new InterruptibleLockExample();

        Thread t1 = new Thread(obj::doWork, "Thread-1");
        Thread t2 = new Thread(obj::doWork, "Thread-2");

        t1.start();
        Thread.sleep(100); // Ensure t1 acquires the lock
        t2.start();

        Thread.sleep(1000); // Let t2 try to acquire
        t2.interrupt(); // Interrupt t2 while it’s waiting
    }

}
