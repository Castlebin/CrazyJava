package ch15.se08.replace;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;

public class ReplaceTest {
	@Test
	public void testReplace() throws FileNotFoundException, IOException, ClassNotFoundException {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
			Person p = new Person("孙悟空", 500);
			oos.writeObject(p);// 系统将使用类提供的writeReplace()方法来进行序列化
			
			// 现在反序列化读到的是我们自己在writeReplace()方法中返回的那个结果
			ArrayList<Object> list = (ArrayList<Object>) ois.readObject();
			System.out.println(list);
		}
	}
}
