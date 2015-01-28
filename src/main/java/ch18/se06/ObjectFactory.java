package ch18.se06;

public class ObjectFactory {
	
	public static Object getInstance(String className) {
		try {
			Class<?> cls = Class.forName(className);
			return cls.newInstance();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		
	}

}
