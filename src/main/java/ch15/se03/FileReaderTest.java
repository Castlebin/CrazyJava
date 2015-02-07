package ch15.se03;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// 使用FileReader来读取文件
public class FileReaderTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try(FileReader fr = new FileReader("src/main/java/ch15/se03/FileReaderTest.java")) {
			char[] cbuf = new char[1024];
			int hasRead = 0;
			while((hasRead=fr.read(cbuf)) > 0) {
				String content = new String(cbuf, 0, hasRead);
				System.out.println(content);
			}
		}
	}
}
