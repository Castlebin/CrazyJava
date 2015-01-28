package ch17.se03.NoBlock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NServer {
	// 用于检测所有Channel状态的Selector
	private Selector selector;
	
	private static final int PORT = 30000;
	
	// 定义实现编码、解码的字符集
	private Charset charset = Charset.forName("UTF-8");
	
	public static void main(String[] args) throws IOException {
		new NServer().init();
	}
	
	public void init() throws IOException {
		selector = Selector.open();
		// 通过open()方法打开一个未绑定的ServerSocketChannel实例
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
		ServerSocketChannel ssc = ServerSocketChannel.open();
		// 将该ServerSocketChannel绑定到指定的IP地址
		ssc.bind(isa);
		// 设置ServerSocketChannel以非阻塞的方式工作（一个ServerSocketChannel代表传统Socket方式下的一个ServerSocket）
		ssc.configureBlocking(false);
		// 将ServerSocketChannel注册到指定的Selector上
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		
		while(selector.select() > 0) {
			// 依此处理selector上每个已选择的SelectionKey，每一个SelectionKey代表的是一个注册在它上面的ServerSocketChannel
			for(SelectionKey sk : selector.selectedKeys()) {
				// 从selector上的已选择的Key集合中删除正在处理的这个SelectionKey
				selector.selectedKeys().remove(sk);
				// 如果sk对应的Channel包含客户端的连接请求
				if(sk.isAcceptable()) {
					// 调用accept方法，产生服务端的SocketChannel
					SocketChannel sc = ssc.accept();
					// 同样的需要设置为非阻塞模式
					sc.configureBlocking(false);
					// 也需要注册到selector上
					sc.register(selector, SelectionKey.OP_READ);
					
					// 将sk对应的ServerSocketChannel设置为准备接受其他请求
					sk.interestOps(SelectionKey.OP_ACCEPT);
				}
				// 如果sk对应的Channel有数据需要读取（这是SocketChannel，代表原来服务端的通信Socket）
				if(sk.isReadable()) {
					// 获取该SelectionKey对应的SocketChannel，读取其中的数据
					SocketChannel sc = (SocketChannel)sk.channel();
					// 使用ByteBuffer来读取数据
					ByteBuffer buff = ByteBuffer.allocate(1024);
					String content = "";
					try {
						while(sc.read(buff) > 0) {
							buff.flip();
							content += charset.decode(buff);
						}
						System.out.println("读取到来自客户端Socket的数据：" + content);
						// 将该SocketChannel设置为准备下一次读取
						sk.interestOps(SelectionKey.OP_READ);
					} catch(IOException e) {
						// 发生了异常，代表与客户端的连接出现了问题，所以需要remove掉对应的SocketChannel
						// 从selector中删除对应的SelectionKey，然后关闭服务端通讯的SocketChannel
						sk.cancel();
						if(sk.channel() != null) {
							sk.channel().close();
						}
					}
					// 如果content长度不为0，即聊天信息不为空时
					if(content.length() > 0) {
						// 群发消息
						// 遍历该selector里所有的SelectionKey
						for(SelectionKey key : selector.keys()) {
							Channel targetChannel = key.channel();
							if(targetChannel instanceof SocketChannel) {
								// 发送消息
								SocketChannel dest = (SocketChannel)targetChannel;
								dest.write(charset.encode(content));
							}
						}
					}
				}
			}
		}
	}
}
