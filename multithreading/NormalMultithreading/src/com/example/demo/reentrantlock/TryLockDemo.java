package com.example.demo.reentrantlock;

public class TryLockDemo {
	public static void main(String[] args) {
        TryLockExample obj = new TryLockExample();

        Runnable task = obj::doWork;

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
