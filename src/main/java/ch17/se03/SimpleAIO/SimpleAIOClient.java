package ch17.se03.SimpleAIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

public class SimpleAIOClient {
	private static final int PORT = 30000;
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		ByteBuffer buff = ByteBuffer.allocate(1024);
		Charset charset = Charset.forName("UTF-8");
		try(
				AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open()) {
			socketChannel.connect(new InetSocketAddress("127.0.0.1", PORT)).get();
			
			socketChannel.read(buff).get();
			buff.flip();
			System.out.println("来自服务器端的消息：" + charset.decode(buff));
		}
	}
}
