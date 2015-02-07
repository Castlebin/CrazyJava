package ch15.se04;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try(FileOutputStream fos = new FileOutputStream("hehe.txt");
				PrintStream ps = new PrintStream(fos)) {
			// 使用处理流PrintStream进行操作
			ps.println("这是一个普通的字符串");
			// 输出一个对象，哈哈哈(输出的是该对象toString()之后的字符串)
			ps.println(new PrintStreamTest());
		}
	}
}
