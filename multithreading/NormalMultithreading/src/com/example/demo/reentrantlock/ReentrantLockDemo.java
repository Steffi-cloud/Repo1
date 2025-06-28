package com.example.demo.reentrantlock;
/**
 * 
 * The thread acquires the same lock twice (once in outerMethod, once in innerMethod)

ReentrantLock keeps an internal hold count, which it tracks per thread

The thread must release the lock the same number of times it acquired it

Difference from synchronized
synchronized blocks/methods are also reentrant, but:

ReentrantLock gives manual control (lock/unlock, timeouts, interruption)

It also allows querying lock state (like isHeldByCurrentThread())
 *
 */
public class ReentrantLockDemo {
	 public static void main(String[] args) {
	        ReentrantExample example = new ReentrantExample();

	        Thread t1 = new Thread(example::outerMethod, "Thread-1");
	        t1.start();
	    }
}
