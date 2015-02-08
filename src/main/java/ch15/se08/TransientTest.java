package ch15.se08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class TransientTest {

	@Test
	public void testTransient() throws FileNotFoundException, IOException, ClassNotFoundException {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
			Person2 per = new Person2("悟空", 500);
			
			oos.writeObject(per);
			
			Person2 pr = (Person2)ois.readObject();
			System.out.println(pr.getAge());// 输出为0，说明transient关键字起了作用
		}
	}
	
}
