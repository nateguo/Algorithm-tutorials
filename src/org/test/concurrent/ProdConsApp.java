package org.test.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Producer<E> implements Runnable {

	private final BlockingQueue<E> queue;

	public Producer(BlockingQueue<E> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				queue.put(produce());
			}
		} catch (InterruptedException e) {
		}
	}

	E produce() {
		return (E) new Object();
	}
}

class Consumer<E> implements Runnable {

	private final BlockingQueue<E> queue;

	public Consumer(BlockingQueue<E> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {

		while (true) {
			try {
				consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	void consume() throws InterruptedException {
		E element = queue.take();
		//
	}
}

public class ProdConsApp {
	public static void main(String[] args) {
		BlockingQueue<String> pool = new LinkedBlockingQueue<String>(10);
		Producer<String> producer = new Producer<String>(pool);
		Consumer<String> consumer = new Consumer<String>(pool);

		Thread prod = new Thread(producer);
		prod.start();
		Thread cons = new Thread(consumer);
		cons.start();

	}
}
