package ch17.se03.MultiThread.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// 使用一个线程不断的读取来自服务端的消息
public class ClientThread implements Runnable {
	private Socket s;
	private BufferedReader br;

	public ClientThread(Socket s) throws IOException {
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		try {
			String content = null;
			// 不断读取来自服务端的消息
			while((content=br.readLine()) != null) {
				// 客户端显示收到的消息
				System.out.println(content);
			}
		} catch(Exception e) {
			try {
				s.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
