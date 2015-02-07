package ch15.se04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyinTest {
	public static void main(String[] args) throws IOException {
		try(
				// 将System.in包装为InputStreamReader
				InputStreamReader reader = new InputStreamReader(System.in);
				// 将InputStreamReader进一步包装为BufferedReader
				BufferedReader br = new BufferedReader(reader)
				) {
			String line = null;
			while((line=br.readLine()) != null) {
				if(line.equals("exit")) {// 输入exit的话，退出程序
					System.out.println("输入了exit，程序退出");
					System.exit(0);
				}
				System.out.println("输入了：" + line);
			}
		}
	}
}
