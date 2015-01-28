package ch17.se03.SimpleAIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SimpleAIOServer {
	private static final int PORT = 30000;
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		try(
				AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open()) {
			serverSocketChannel.bind(new InetSocketAddress(PORT));
			// 采用循环接受来自客户端的请求
			while(true) {
				Future<AsynchronousSocketChannel> future = serverSocketChannel.accept();
				AsynchronousSocketChannel socketChannel = future.get();
				// 执行输出
				socketChannel.write(ByteBuffer.wrap("欢迎来到AIO的世界！".getBytes("UTF-8"))).get();
			}
		}
	}
}
