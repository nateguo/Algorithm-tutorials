package org.test.concurrent.demo;

public class Semaphore {
	
	private int count;

	public Semaphore(int count) {
		this.count = count;
	}
	
	public synchronized void P() {
		count--;
		if(count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public synchronized void V() {
		count++;
		if(count <= 0) {
			notifyAll();
		}
		
	}
}
