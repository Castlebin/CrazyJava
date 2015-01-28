package ch17.se03.TestClosedServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

// 建立连接以后，Socket服务端意外停止，客户端仍然可以不断的向socket输出流中发送数据，同理服务端！！所以必须要检查服务端是否正常
// 这也是为什么Socket编程中通常需要“心跳包”的原因之一
public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("127.0.0.1", 3000);
		PrintStream ps = new PrintStream(s.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String content = null;
		while((content = br.readLine()) != null) {
			ps.println(content);
		}
	}
}
