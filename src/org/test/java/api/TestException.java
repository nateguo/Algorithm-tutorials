package org.test.java.api;

public class TestException {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] strs = new String[3];
		int i=0;
		for(String s : strs)
		{
			System.out.println(i++);
			s.toString();
		}

	}

}
