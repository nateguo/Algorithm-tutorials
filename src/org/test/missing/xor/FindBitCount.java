package org.test.missing.xor;

public class FindBitCount {

	public static void main(String[] args) {
		int a = 100;
		System.out.println("The bit count is : " + bitCount(a));

	}
	
	public static int bitCount(int integer) {
		int i = 0;
		while( integer > 0) {
			integer = integer & (integer - 1);
			i++;
		}
		return i;
	}
}
