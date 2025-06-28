package com.example.demo.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {
	private final ReentrantLock lock = new ReentrantLock();

    public void doWork() {
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + " acquired the lock");
                Thread.sleep(100); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " released the lock");
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " could not acquire the lock");
        }
    }
}
