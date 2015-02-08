package ch15.se08.resolve;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class ResolveTest {
	@Test
	public void testResolve_1() throws FileNotFoundException, IOException, ClassNotFoundException {// No Resolve
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
			oos.writeObject(Orientation_1.HORIZONTAL);
			Orientation_1 horizontal = (Orientation_1) ois.readObject();
			System.out.println(horizontal == Orientation_1.HORIZONTAL);// false
		}
	}
	
	// Orientation_2提供了readResolve()方法
	@Test
	public void testResolve_2() throws FileNotFoundException, IOException, ClassNotFoundException {// No Resolve
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
			oos.writeObject(Orientation_2.HORIZONTAL);
			Orientation_2 horizontal = (Orientation_2) ois.readObject();
			System.out.println(horizontal == Orientation_2.HORIZONTAL);// true
		}
	}
}
