package com.example.demo;

public class PrintNumbers implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 for (int i = 0; i < 5; i++) {
	            System.out.println("Number: " + i);
	        }
	}

}
