package com.example.demo.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
	private final ReentrantLock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " entered outerMethod");
            innerMethod(); // Reentrant call
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " exited outerMethod");
        }
    }

    private void innerMethod() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " entered innerMethod");
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " exited innerMethod");
        }
    }
}
