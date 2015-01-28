package ch17.se03.Senior.client;

import java.io.BufferedReader;
import java.io.IOException;

// 负责读取来自服务器端的消息
public class ClientThread extends Thread {
	BufferedReader br;
	
	public ClientThread(BufferedReader brServer) {
		this.br = brServer;
	}

	@Override
	public void run() {
		try {
			String line = null;
			while((line=br.readLine()) != null) {
				System.out.println(line);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
