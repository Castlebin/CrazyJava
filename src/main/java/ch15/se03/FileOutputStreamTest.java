package ch15.se03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try(FileInputStream fis = new FileInputStream("src/main/java/ch15/se03/FileOutputStreamTest.java");
				// 创建字符输出流
				FileOutputStream fos = new FileOutputStream("hehe.txt")) {
			byte[] bbuf = new byte[64];
			int hasRead = 0;
			while((hasRead=fis.read(bbuf)) > 0) {
				// 读多少，往输出流中写多少
				fos.write(bbuf, 0, hasRead);
			}
		}
	}
}
