package com.example.demo.conchash;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
	 public static void main(String[] args) throws InterruptedException {
	        ConcurrentHashMap<String, Integer> wordMap = new ConcurrentHashMap<>();

	        String[] text1 = {"apple", "banana", "apple", "cherry"};
	        String[] text2 = {"banana", "apple", "cherry", "cherry"};

	        Thread t1 = new Thread(new WordCounter(wordMap, text1));
	        Thread t2 = new Thread(new WordCounter(wordMap, text2));

	        t1.start();
	        t2.start();

	        t1.join();
	        t2.join();

	        System.out.println("Final Word Count:");
	        wordMap.forEach((key, value) -> System.out.println(key + " => " + value));
	    }
}
