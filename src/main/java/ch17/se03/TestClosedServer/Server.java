package ch17.se03.TestClosedServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(3000);
		
		// 在收到一个来自客户端的连接之前，此行代码会一直阻塞
		Socket s = ss.accept();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String content = null;
		while((content = br.readLine()) != null) {
			System.out.println(content);
			s.close();
			while(true) {
				
			}
		}
	}
}
