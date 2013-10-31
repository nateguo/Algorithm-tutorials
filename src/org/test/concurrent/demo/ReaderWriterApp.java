package org.test.concurrent.demo;

import java.util.HashMap;
import java.util.Map;

public class ReaderWriterApp {

	public static void main(String[] args) {
		Semaphore mutexReader = new Semaphore(1);
		Semaphore mutexWriter = new Semaphore(1);
		Semaphore mutexResource = new Semaphore(1);
		Map<String, String> resource = new HashMap<String, String>();
		SharedResource sharedResource = 
				new SharedResource(mutexReader, mutexWriter, mutexResource, resource);
		new Reader(sharedResource, "hello");

	}

}
