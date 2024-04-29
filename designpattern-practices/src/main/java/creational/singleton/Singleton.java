package creational.singleton;

public class Singleton {
	
	private static Singleton instance = null;
	
	private Singleton() { // 외부에서 new  객체생성 불가	(내부에서만 가능)	
	}
	
	public static Singleton getInstance() {
		if(instance == null ) {
			instance = new Singleton();
		}
		return instance;
	}

}
