package ch15.se08.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// 实现Externalizable接口来实现序列化
public class Person implements Externalizable {
	private String name;
	private int age;
		
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(new StringBuilder(name).reverse());
		out.writeObject(age);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		this.name = ((StringBuilder)in.readObject()).reverse().toString();
		this.age = in.readInt();
	}
	
}
