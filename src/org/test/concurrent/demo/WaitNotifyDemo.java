package org.test.concurrent.demo;

class PrintA implements Runnable {
	private final String str;
	public PrintA(String str) {
		this.str = str;
	}
	
	@Override
	public void run() {
		System.out.println(str);
	}
	
}

public class WaitNotifyDemo {
	public static void main(String[] args) {
		String strA = "A";
		String strB = "B";
		String strC = "C";
		Thread a = new Thread(new PrintA(strA), "Print a");
		Thread b = new Thread(new PrintA(strB), "Print b");
		Thread c = new Thread(new PrintA(strC), "Print c");
	}
}
