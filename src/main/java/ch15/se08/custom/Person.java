package ch15.se08.custom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 自定义序列化(提供writeObject()、readObject()、readObjectNoData()这些方法即可)
public class Person implements Serializable {
	private static final long serialVersionUID = -6502256128385376440L;
	
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	// 提供writeObject()和readObject()方法
	private void writeObject(ObjectOutputStream out) throws IOException {
		// 这里是自定义的序列化方式
		// 将name字段的值反转后写入二进制流
		out.writeObject(new StringBuilder(name).reverse());
		out.writeObject(age);
	}
	// 对应的readObjectf()方法
	private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
		this.name = ((StringBuilder)in.readObject()).reverse().toString();
		this.age = in.readInt();
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
