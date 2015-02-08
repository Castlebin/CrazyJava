package ch15.se08;

import java.io.Serializable;

// 使用transient关键字标明不想序列化类的某个字段
public class Person2 implements Serializable {
	private static final long serialVersionUID = -4984094005171959288L;
	
	private String name;
	private transient int age;
	
	public Person2(String name, int age) {
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
