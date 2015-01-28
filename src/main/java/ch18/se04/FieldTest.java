package ch18.se04;

import java.lang.reflect.Field;

class Person {
	private String name;
	private int age;
	
	public String toString() {
		return "Person[name: "+name+", age: "+age + "]";
	}
}

public class FieldTest {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, 
			IllegalArgumentException, IllegalAccessException {
		// 创建一个Perosn对象
		Person p = new Person();
		
		Class<?> clazz = Person.class;
		
		// 获取name成员变量
		Field nameField = clazz.getDeclaredField("name");
		nameField.setAccessible(true);
		// 赋值
		nameField.set(p, "张无忌");
		
		// 获取age成员变量
		Field ageField = clazz.getDeclaredField("age");
		ageField.setAccessible(true);
		ageField.setInt(p, 20);
		
		System.out.println(p);
	}
}
