package ch15.se08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;


public class WriteReadObjectTest {
	
	@Test
	public void testWriteObject() throws FileNotFoundException, IOException {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
			Person p = new Person("heller", 18);
			
			p.info = "tttttttt";
			
			// 将该Person对象写入文件（序列化）
			oos.writeObject(p);
			System.out.println("将Person对象序列化存储到文件中");
		}
	}
	
	@Test
	public void testReadObject() throws FileNotFoundException, IOException, ClassNotFoundException {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
			//  下面这行代码并没有打印（使用了Person类的有参构造器来创建了一个实例），表明通过反序列化还原出原来的对象时并不会取调用构造方法
			Person p = (Person)ois.readObject();
			System.out.println(p.getName()+" " + p.getAge());
			
			System.out.println(p.info);// 静态变量不会被序列化，所以此处输出null
		}
	}
	
}
