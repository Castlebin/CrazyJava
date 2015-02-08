package ch15.se08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class SerializeMutable {
	
	@Test
	public void testSerializeMutable() throws FileNotFoundException, IOException, ClassNotFoundException {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
			Person p = new Person("孙悟空", 500);
			oos.writeObject(p);
			Person pr1 = (Person) ois.readObject();
			System.out.println(p == pr1);	// 这边输出的是false，反序列化出来的对象必然是和原来的对象不是同一个对象了
			
			p.setName("齐天大圣");
			p.setAge(1000);
			oos.writeObject(p);// 再次对p进行序列话
			
			Person pr2 = (Person) ois.readObject();
			System.out.println(pr1 == pr2); // true, 说明并没有再次序列化
			System.out.println(pr2.getName());// 仍旧为"孙悟空"，再次说明第二次其实没有进行实质的序列化
		}
	}

}
