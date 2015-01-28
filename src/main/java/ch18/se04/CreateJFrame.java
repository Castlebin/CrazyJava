package ch18.se04;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CreateJFrame {
	
	public static void main(String[] args) throws ClassNotFoundException, 
			NoSuchMethodException, SecurityException, InstantiationException, 
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName("javax.swing.JFrame");
		Constructor<?> ctor = clazz.getConstructor(String.class);
		
		Object obj = ctor.newInstance("测试窗口");
		System.out.println(obj);
	}

}
