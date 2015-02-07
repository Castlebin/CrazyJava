package ch15.se07;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		try (RandomAccessFile raf = new RandomAccessFile(
				"src/main/java/ch15/se07/RandomAccessFileTest.java", "r")) {
			// 获取RandomAccessFile对象文件指针的位置，初始位置是0
			System.out.println("RandomAccessFile的文件指针初始位置为：" + raf.getFilePointer());
			
			// 移动文件指针
			raf.seek(300);
			byte[] bbuf = new byte[64];
			int hasRead = 0;
			while((hasRead=raf.read(bbuf)) > 0) {
				String content = new String(bbuf, 0, hasRead);
				System.out.print(content);
			}
		}
	}
}
