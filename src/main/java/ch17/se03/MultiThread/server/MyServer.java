package ch17.se03.MultiThread.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyServer {
	// 创建一个list，用来保存服务端和客户端通讯的Socket列表。同时将这个list包装为线程安全的集合类型，因为涉及到多线程.
	public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());
	
	public static void main(String[] args) throws IOException {
		try(
				ServerSocket ss = new ServerSocket(3000)) {
			while(true) {
				Socket s = ss.accept();
				// 将与客户端通讯的socket加入socket列表中
				socketList.add(s);
				// 新建一个线程来处理和此客户端的通讯
				new Thread(new ServerThread(s)).start();
			}
		}
	}

}
