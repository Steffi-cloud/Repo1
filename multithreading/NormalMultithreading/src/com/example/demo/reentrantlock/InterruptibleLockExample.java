package com.example.demo.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class InterruptibleLockExample {
	private final ReentrantLock lock = new ReentrantLock();

    public void doWork() {
        try {
            System.out.println(Thread.currentThread().getName() + " trying to acquire lock");
            lock.lockInterruptibly(); // Can be interrupted while waiting
            try {
                System.out.println(Thread.currentThread().getName() + " acquired lock");
                Thread.sleep(5000);
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " released lock");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted while waiting");
        }
    }
}
