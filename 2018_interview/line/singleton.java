package pattern;

class Singleton4 {
	private Singleton4() {}
	
	private static class Singleton {
		private static Singleton4 instance = new Singleton4();
	}
	
	public static Singleton4 getInstance() {
		return Singleton.instance;
	}
}

class Singleton3 {
	private static Singleton3 instance =  null;
	private Singleton3() {}
	
	public synchronized static Singleton3 getInstance() {
		if(instance == null) {
			instance = new Singleton3();
		}
		return instance;
	}
}

class Singleton2 {
	private static Singleton2 instance = new Singleton2();
	private Singleton2() {}
	
	public static Singleton2 getInstance() {
		return instance;
	}
}

public class Singleton {
	private static Singleton instance = null;
	private Singleton() {}
	
	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		Singleton4 s4 = Singleton4.getInstance();
		if(s4 == null) {
			System.out.println("null");
		} else {
			System.out.println("test");
		}
	}
}
