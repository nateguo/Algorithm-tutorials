package org.test.concurrent.demo;

public class Writer implements Runnable {

	private SharedResource rs;
	private String writeKey;
	private String writeValue;
	
	public Writer(SharedResource rs, String writeKey, String writeValue) {
		this.rs = rs;
		this.writeKey = writeKey;
		this.writeValue = writeValue;
	}

	@Override
	public void run() {
		write();
		System.out.println(Thread.currentThread().getName() 
				+ "write :" + writeKey + ":" + writeValue);
	}
	
	private void write() {
		rs.getMutexw().V();
		rs.getMutexs().P();
		rs.getRs().put(writeKey, writeValue);
		rs.getMutexs().V();
		
	}
}
