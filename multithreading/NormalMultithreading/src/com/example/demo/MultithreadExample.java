package com.example.demo;

public class MultithreadExample {
	public static void main(String[] args) {
        // Create thread objects
        Thread t1 = new Thread(new PrintNumbers());
        Thread t2 = new Thread(new PrintLetters());

        // Start the threads
        t1.start();
        t2.start();

        // Wait for both threads to finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }

        System.out.println("Main thread finished.");
    }
}
