package ch17.se03.MultiThread.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread implements Runnable {
	private Socket s;
	private BufferedReader br;

	public ServerThread(Socket s) throws IOException {
		this.s = s;
		// 将socket输入流包装为一个BufferedReader对象
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	@Override
	public void run() {
		try {
			String content = null;
			// 不断地从socket中读取数据
			while((content=readFromClient()) != null) {
				// 广播，也就是类似于聊天室一样，向每个其他的客户端广播消息
				for(Socket s : MyServer.socketList) {
					if(s != this.s) {
						// 获取socket对应的输出流
						PrintStream ps = new PrintStream(s.getOutputStream());
						ps.println(content);
					}
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	// 读取来自客户端的数据
	private String readFromClient() {
		try {
			return br.readLine();
		// 如果在读取数据时捕获了异常，说明客户端已经断开，所以应该将服务端对应的socket移除
		} catch(IOException e) {
			// 删除该socket
			MyServer.socketList.remove(s);
		}
		
		return null;
	}

}
