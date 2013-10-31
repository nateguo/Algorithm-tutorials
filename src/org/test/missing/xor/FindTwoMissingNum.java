package org.test.missing.xor;

import java.util.Arrays;

public class FindTwoMissingNum {

	public static int[] findMissingNum (int[] array) {
		int[] missingNum = new int[2];
		int xor = 0;
		for(int i = 0; i < array.length ; i++) 
			xor ^= (i+1) ^ array[i];
		xor ^= (array.length + 1);
		xor ^= (array.length + 2);
		// xor = missing1 ^ missing2
		int k = xor - (xor & (xor - 1));
		
		int xora = 0;
		for(int i = 0; i < array.length; i++) {
			if(((i + 1) & k) == 0)
				xora ^= (i+1);
			if((array[i] & k) == 0)
				xora ^= array[i];
		}
		
		if(((array.length + 1) & k) == 0)
			xora ^= (array.length + 1);
		if(((array.length + 2) & k) == 0)
			xora ^= (array.length + 2);
		
		missingNum[0] = xora;
		missingNum[1] = xora ^ xor;
		
		return missingNum;
		
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = {1,6,5,4,3};
		System.out.println(Arrays.toString(findMissingNum(array)));

	}

}
