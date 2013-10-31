package org.test.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Resource <E>{
	public ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	private List<E> buffer;
	
	public Resource(List<E> buffer) {
		this.buffer = buffer;
	}

	public E read(int index) {
		lock.readLock().lock();
		try{
			TimeUnit.SECONDS.sleep(60L);
			if(index>(buffer.size()-1) || index < 0)
				throw new IllegalArgumentException();
			return buffer.get(index);
		} catch (InterruptedException e) {
			return null;
		}finally{
			lock.readLock().unlock();
		}
	}
	
	public void write(int index, E element) {
		lock.writeLock().lock();
		try{
			TimeUnit.SECONDS.sleep(30L);
			buffer.set(index, element);
		} catch (InterruptedException e) {
			
		}finally{
			lock.writeLock().unlock();
		}
	}
	
}

class ReaderRunnable<E> implements Runnable {
	final Resource<E> resource;
	final CountDownLatch latch;
	private int index;
	public ReaderRunnable(Resource<E> resource, CountDownLatch latch,int index) {
		this.resource = resource;
		this.latch = latch;
		this.index = index;
	}

	public void run() {
		E element = resource.read(index);
		System.out.println(Thread.currentThread().getName() + "Read: " + index + "Value: " + element);
		latch.countDown();
	}
}

class WriterRunnable<E> implements Runnable {
	final Resource<E> resource;
	final CountDownLatch latch;
	int index;
	E element;
	public WriterRunnable(Resource<E> resource, CountDownLatch latch, int index, E element) {
		this.resource = resource;
		this.latch = latch;
		this.index = index;
		this.element = element;
	}

	public void run() {
		resource.write(index, element);
		System.out.println(Thread.currentThread().getName() + "Write: " + index + "Value: " + element);
		latch.countDown();
		
	}
}

public class ReaderWriteApp2 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		List<String> buffer = new ArrayList<String>();
		buffer.add("java");
		buffer.add("python");
		buffer.add("c++");
		Resource<String> sharedResource = new Resource<String>(buffer);
		
		int[] indexes = new int[] {1,0,2};
		String[] elements = new String[] {"cassandra", "hbase", "mongdb"};
		
//		testWrite(sharedResource, elements);
//		testRead(sharedResource, indexes);
		
		testReadWrite(sharedResource, indexes, elements);
	}
	
	private static <Type> void testRead(Resource<Type> resource, int[] indexes) throws Exception {
		CountDownLatch threadSignal = new CountDownLatch(indexes.length);
		for(int i=0; i<indexes.length; i++) {
			ReaderRunnable<Type> r = new ReaderRunnable<Type>(resource, threadSignal, indexes[i]);
			new Thread(r, "r" + i).start();
		} 
		threadSignal.await();
		
	}

	private static <Type> void testWrite(Resource<Type> resource, Type[] elements) throws Exception {
		CountDownLatch threadSignal = new CountDownLatch(elements.length);
		for(int i=0; i<elements.length; i++) {
			WriterRunnable<Type> w = new WriterRunnable<Type>(resource, threadSignal, i, elements[i]);
			new Thread(w, "w" + i).start();
		}
		threadSignal.await();
		
	}
	
	private static <Type> void testReadWrite(Resource<Type> resource, int[] indexes, Type[] elements) throws Exception {
		CountDownLatch threadSignal = new CountDownLatch(elements.length + indexes.length);
		for(int i=0; i < (indexes.length + elements.length); i++) {
			if(i%2 == 0){
				WriterRunnable<Type> w = new WriterRunnable<Type>(resource, threadSignal, i%elements.length, elements[i%elements.length]);
				new Thread(w, "w" + i).start();
			}else {
				ReaderRunnable<Type> r = new ReaderRunnable<Type>(resource, threadSignal, indexes[i%indexes.length]);
				new Thread(r, "r" + i).start();
			}
		}
		
		threadSignal.await();
		
	}
}
