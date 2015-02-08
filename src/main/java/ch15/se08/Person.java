package ch15.se08;

import java.io.Serializable;

public class Person implements Serializable {
	private String name;
	private int age;
	
	public Person(String name, int age) {
		System.out.println("使用了Person类的有参构造器来创建了一个实例");
		this.name = name;
		this.age = age;
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
