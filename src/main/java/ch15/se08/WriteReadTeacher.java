package ch15.se08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class WriteReadTeacher {
	@Test
	public void testWriteTeacher() throws FileNotFoundException, IOException {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
			Person stu = new Person("悟空", 500);
			Teacher t1 = new Teacher("菩提老祖", stu);
			Teacher t2 = new Teacher("三藏", stu);
			
			oos.writeObject(t1);
			oos.writeObject(t2);
			oos.writeObject(stu);
			
			t2.setName("三藏法师");// 改变t2后再次试图进行序列化，发现并不会序列化，只会输出一个序列化标号标示一下
			oos.writeObject(t2);// 已经序列化过，不会再次序列化输出，只会输出一个序列化标号标示一下
		}
	}
	
	@Test
	public void testReadTeacher() throws FileNotFoundException, IOException, ClassNotFoundException {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
			// 必须按照原来序列化的顺序进行反序列化，这样读取顺序才是对的
			Teacher t1 = (Teacher) ois.readObject();
			System.out.println("t1-->" + t1.getName()+" " + t1.getStudent().getName()+" " + t1.getStudent().getAge());
			Teacher t2 = (Teacher) ois.readObject();
			System.out.println("t2-->" + t2.getName()+" " + t2.getStudent().getName()+" " + t2.getStudent().getAge());
			Person stu = (Person) ois.readObject();
			System.out.println("stu-->" + stu.getName()+" " + stu.getAge());
			
			Teacher t3 = (Teacher) ois.readObject();// 会在文件中记录一个标号，所以仍然可以按顺序读到它
			
			System.out.println(t1.getStudent() == stu);	//true
			System.out.println(t2.getStudent() == stu);	//true
			
			System.out.println(t2 == t3);						//true
		}
	}
}
