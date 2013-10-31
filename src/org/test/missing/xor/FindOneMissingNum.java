package org.test.missing.xor;

public class FindOneMissingNum {
	
	public static int findNum(int[] array) {
		int xor = 0;
		/*for(int i=2; i <= array.length + 1; i ++) 
			xor ^= i;*/
		for(int i=0; i < array.length; i++) {
			xor ^= (i+1) ^ array[i];
		}
		
		return xor ^ (array.length + 1);
	}
	
	public static void swap(int a, int b) {
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {1,5,4,3};
		System.out.println("The missing number is : " + findNum(array));
		
		System.out.println("Zeor xor num :" + (0 ^ 5));
		
		int a = 5, b = 4;
		swap(a, b);
		System.out.println("a = " + a + "; b = " + b);
		
	}

}
