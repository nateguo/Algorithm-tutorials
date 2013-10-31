package org.test.java.api;

import java.util.Random;

public class TestRandom {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=0; i < 10; i++) {
			Random rand = new Random();
			int a = rand.nextInt();
			System.out.println("Ramdom Integer " + a);
		}

	}

}
