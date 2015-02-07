package ch15.se06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 读取其他进程的输入流
public class ReadFromProcess {
	public static void main(String[] args) throws IOException {
		// 使用exec来运行javac命令，即 新建了一个javac子进程
		Process p = Runtime.getRuntime().exec("javac");
		
		// 以p进程的错误流来创建BufferedReader对象
		try(BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()))) {
			String line = null;
			while((line=br.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
}
