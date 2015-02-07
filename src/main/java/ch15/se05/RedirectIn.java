package ch15.se05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// 重定向标准输入
public class RedirectIn {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		try(FileInputStream fis = new FileInputStream("src/main/java/ch15/se05/RedirectIn.java")) {
			// 将标准输入重定向到fis
			System.setIn(fis);
			
			// 包装标准输入流
			Scanner sc = new Scanner(System.in);
			// 使用回车符（\n）作为分隔符
			sc.useDelimiter("\n");
			
			while(sc.hasNext()) {
				System.out.println("读取到的输入内容：" + sc.next());
			}
			
			sc.close();
			System.out.println("-----------结束---------");
		}
	}
}
