package ch18.se06;

import java.util.Date;

public class ObjectFactory2 {
	public static <T> T getInstance(Class<T> cls) {
		try {
			return cls.newInstance();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		Date d = getInstance(Date.class);
		System.out.println(d);
	}
}
