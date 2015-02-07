package ch15.se05;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

// 重定向标准输出流
public class RedirectOut {
	public static void main(String[] args) throws FileNotFoundException {
		try (PrintStream ps = new PrintStream(new FileOutputStream("hehe.txt"))) {
			// 使用System类的静态方法来重定向标准输入输出流
			System.setOut(ps);
			
			// 依然使用标准的方式来包装来自键盘的输入流
			Scanner sc = new Scanner(System.in);
			// 定义使用回车符（\n）作为分隔符(表示读到回车的话sc.hasNext()就可以返回true了)
			sc.useDelimiter("\n");
			
			while(sc.hasNext()) {
				String content = sc.next();
				System.out.println("从键盘输入了：" + content);
				if(content.equals("exit")) {
					System.out.println("输入了exit，程序退出");
					System.exit(0);
				}
			}
		}
	}
}
