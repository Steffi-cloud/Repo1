package com.example.demo;

public class PrintLetters implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		   for (char c = 'A'; c <= 'E'; c++) {
	            System.out.println("Letter: " + c);
	        }
	}

}
