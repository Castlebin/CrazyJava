package ch17.se03.MultiThread.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

// 这个版本的Client是有点问题的，因为在服务端结束后，客户端还会继续运行，而服务端对应的socket则已经被删除了
// 可以在服务器停止后，让客户端程序也结束？no，不行，加入重连倒是可以?，作为思考题
// OK,已经加好了
public class MyClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("127.0.0.1", 3000);
		// 启用一个线程用于读取来自服务端广播的消息
		new Thread(new ClientThread(s)).start();
		
		// 获取socket对应的输出流，用于向服务端发送数据
		PrintStream ps = new PrintStream(s.getOutputStream());
		
		// 获取来自键盘输入的数据流，用于向程序输入
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		// 不断的读取键盘输入
		while((line=br.readLine()) != null) {
			// 想好了，在这里加上判断的服务器是否已经断开，还可以继续加入重连
			if(s.isClosed()) {
				System.out.println("服务端已断开，结束客户端程序！");
				System.exit(0);
			}
			
			// 向服务端发送键盘输入的消息
			ps.println(line);
		}
	}
}
