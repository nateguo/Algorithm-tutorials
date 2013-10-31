package org.test.concurrent.demo;

import java.util.Map;

public class SharedResource {
	
	private Semaphore mutexr;
	private Semaphore mutexw;
	private Semaphore mutexs;
	
	public SharedResource(Semaphore mutexr, Semaphore mutexw, Semaphore mutexs,
			Map<String, String> rs) {
		this.mutexr = mutexr;
		this.mutexw = mutexw;
		this.mutexs = mutexs;
		this.rs = rs;
	}

	private Map<String, String> rs;

	public Semaphore getMutexr() {
		return mutexr;
	}

	public Semaphore getMutexw() {
		return mutexw;
	}

	public Semaphore getMutexs() {
		return mutexs;
	}

	public Map<String, String> getRs() {
		return rs;
	}

}
