package ch15.se03;

import java.io.FileInputStream;
import java.io.IOException;

// 使用FileInputStream来读取本源文件自身
public class FileInputStreamTest {
	public static void main(String[] args) throws IOException {
		// 创建字节输入流
		FileInputStream fis = new FileInputStream("src/main/java/ch15/se03/FileInputStreamTest.java");
		
		byte[] bbuf = new byte[1024];
		int hasRead = 0;
		try {
			// 通过循环来读取文件内容
			while((hasRead=fis.read(bbuf)) > 0) {
				// 将取出的字节数组转化为字符串进行输出
				String content = new String(bbuf, 0, hasRead);
				System.out.println(content);
			}
		} finally {
			// 关闭文件输入流
			fis.close();
		}
	}
}
