package org.test.concurrent.demo;

import java.util.concurrent.TimeUnit;

class CustomThread extends Thread {

	public CustomThread(String name) {
		super(name);
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println(threadName + " Loop " + i + "time");
				TimeUnit.MILLISECONDS.sleep(2000L);
			}
		} catch (InterruptedException e) {
		}
		System.out.println(threadName + " end !");
	}

}

class SCustomThread extends Thread {

	private CustomThread ct;

	public SCustomThread(String name, CustomThread ct) {
		super(name);
		this.ct = ct;
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		try {
			ct.join();
		} catch (InterruptedException e) {
		}
		System.out.println(threadName + " end !");
	}

}

public class ThreadJoin {
	public static void main(String[] args) {
		CustomThread thread = new CustomThread("CustomThread 1");
		SCustomThread sthread = new SCustomThread("CustomThread 2", thread);
		thread.start();
		sthread.start();

		try {
			sthread.join();
		} catch (InterruptedException e) {
		}

		System.out.println(Thread.currentThread().getName() + " end! ");
	}
}
