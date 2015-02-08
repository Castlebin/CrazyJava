package ch15.se08.replace;

import java.io.Serializable;
import java.util.ArrayList;

// 实现更加变态的序列化机制，在序列化时完全替换掉被序列化的对象（提供writeReplace()方法即可）
public class Person implements Serializable {
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	// writeReplace()方法，序列化时完全替换掉原来的对象
	private Object writeReplace() {
		ArrayList<Object> list = new ArrayList<>();
		list.add(name);
		list.add(age);
		
		return list;
 	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
