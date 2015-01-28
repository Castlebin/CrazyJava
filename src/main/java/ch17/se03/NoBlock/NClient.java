package ch17.se03.NoBlock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NClient {
	// 定义检测SocketChannel的Selector对象
	private Selector selector;
	
	private static final int PORT = 30000;
	
	// 处理编码的字符集
	private Charset charset = Charset.forName("UTF-8");
	
	// 客户端的SocketChannel
	private SocketChannel sc;
	
	public static void main(String[] args) throws IOException {
		new NClient().init();
	}
	
	public void init() throws IOException {
		selector = Selector.open();
		// 远程服务器地址
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
		// 调用open方法连接到指定的Socket服务器
		sc = SocketChannel.open(isa);
		// 设置该SocketChannel以非阻塞方式工作
		sc.configureBlocking(false);
		// 注册SocketChannel到指定的Selector
		sc.register(selector, SelectionKey.OP_READ);
		
		// 启动读取来自服务器端的数据的线程
		new ClientThread().start();
		
		// 读取来自键盘的输入消息，发送至服务器端
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			// 发送消息
			sc.write(charset.encode(line));
		}
	}
	
	private class ClientThread extends Thread {
		@Override
		public void run() {
			try {
				while(selector.select() > 0) {
					for(SelectionKey sk : selector.selectedKeys()) {
						// 从selector中删除正在处理的SelectionKey
						selector.selectedKeys().remove(sk);
						// 如果对应的SocketChannel中有可读取的数据
						if(sk.isReadable()) {
							// 使用NIO读取Channel中的数据
							SocketChannel sc = (SocketChannel)sk.channel();
							ByteBuffer buff = ByteBuffer.allocate(1024);
							String content = "";
							while(sc.read(buff) > 0) {
								sc.read(buff);
								buff.flip();
								content += charset.decode(buff);
							}
							// 答应读取到的内容
							System.out.println("来自服务器端的聊天信息：" + content);
							// 为下一次读取做准备
							sk.interestOps(SelectionKey.OP_READ);
						}
					}
				}
			} catch(IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
