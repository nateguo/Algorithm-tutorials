package org.test.concurrent.demo;

public class Reader implements Runnable{

	private SharedResource rs;
	private String readKey;
	
	public Reader(SharedResource rs, String readKey) {
		this.rs = rs;
		this.readKey = readKey;
	}

	@Override
	public void run() {
		String value = read();
		System.out.println(Thread.currentThread().getName() + ": " + value);
	}
	
	private String read() {
		String value = null;
		rs.getMutexs().P();
		value = rs.getRs().get(readKey);
		rs.getMutexs().V();
		return value;
	}
}
