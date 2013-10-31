package org.test.designpatterns;

class Singleton1 {

	private static Singleton1 instance;
	
	private Singleton1() {
		
	}
	
	public static final Singleton1 getInstance() {
		if(instance == null) {
			synchronized(Singleton1.class) {
				if(instance == null)
					instance = new Singleton1();
			}
		}
		
		return instance;
	}
}

class Singleton2 {
	private Singleton2() {
		
	}
	
	private static class SingletonHolder{
		private static final Singleton2 instance = new Singleton2();
	}
	
	public static final Singleton2 getInstance() {
		return SingletonHolder.instance;
	}
}

public class SingletonApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
