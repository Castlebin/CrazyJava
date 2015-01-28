package ch17.se03.Senior.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final int SERVER_PORT = 3000;
	
	// 创建一个CrazyitMap实例，用来保存每个客户端名字和对应的输出流之间的对应关系
	public static CrazyitMap<String, PrintStream> clients = new CrazyitMap<>();
	
	public void init() {
		try(
				ServerSocket ss = new ServerSocket(SERVER_PORT)) {
			while(true) {// 不断地接受来自客户端的请求
				Socket socket = ss.accept();
				new ServerThread(socket).start();
			}
		} catch(IOException e) {
			System.out.println("服务器启动失败！是否端口："+SERVER_PORT+"已被占用？");
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.init();
	}
	
	public static String getUserList() {
		return clients.map.keySet().toString();
	}
}
