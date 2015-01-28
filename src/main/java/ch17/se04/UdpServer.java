package ch17.se04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
	
	public static final int PORT = 30000;
	
	// 定义每个数据报文的大小最大为4KB
	private static final int DATA_LEN = 4096;
	// 接收数据的字节数组
	private byte[] inBuff = new byte[DATA_LEN];
	// 以指定字节数组创建准备接收数据的DatagramPacket对象
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	
	// 定义一个用于发送的DatagramPacket对象
	private DatagramPacket outPacket;
	
	// 定义一个字符数组，服务器端发送该字符数组中的元素
	String[] books = new String[] {
			"疯狂Java讲义",
			"轻量级JavaEE企业应用实战",
			"疯狂Android讲义",
			"疯狂Ajax讲义"
	};
	
	public static void main(String[] args) throws IOException {
		new UdpServer().init();
	}
	
	public void init() throws IOException {
		try(
				DatagramSocket socket = new DatagramSocket(PORT)) {
			// 采用循环接受数据
			for(int i=0; i<1000; i++) {
				// 读取Socket中的数据，读到的数据放入inPacket封装的数组中
				socket.receive(inPacket);
				// 判断inPacket.getData()和inBuff是否是同一个数组
				System.out.println(inBuff == inPacket.getData());
				
				// 将接收到的内容转换为字符创后输出
				System.out.println(new String(inBuff, 0, inPacket.getLength()));
				// 从字符串数组中取出一个座位发送数据
				byte[] sendData = books[i%4].getBytes();
				// 以指定的字节数组作为发送数据
				outPacket = new DatagramPacket(sendData, sendData.length, inPacket.getSocketAddress());
				// 发送数据
				socket.send(outPacket);
			}
		}
	}

}
