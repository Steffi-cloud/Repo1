package com.example.demo.conchash;

import java.util.concurrent.ConcurrentHashMap;

public class WordCounter implements Runnable {

	private final ConcurrentHashMap<String, Integer> wordMap;
    private final String[] words;

    public WordCounter(ConcurrentHashMap<String, Integer> wordMap, String[] words) {
        this.wordMap = wordMap;
        this.words = words;
    }

    @Override
    public void run() {
        for (String word : words) {
            // Use merge() to safely update count
            wordMap.merge(word, 1, Integer::sum);
        }
    }
}
