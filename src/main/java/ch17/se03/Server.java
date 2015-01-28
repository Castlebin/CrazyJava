package ch17.se03;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(3000);
		
		try {
			while(true) {
				Socket s = ss.accept();
				PrintStream ps = new PrintStream(s.getOutputStream());
				// 进行IO操作
				ps.println("这里是来自服务器的相应，你好！现在时刻：" + new Date());
				
				// 关闭
				ps.close();
				s.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
			ss.close();
		}
	}

}
