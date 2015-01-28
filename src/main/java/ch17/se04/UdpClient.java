package ch17.se04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
	// 定义发送数据报的目的地
	public static final int DEST_PORT = 30000;
	public static final String DEST_IP = "127.0.0.1";
	
	private static final int DATA_LEN = 4096;
	// 定义接收网络数据的字节数组
	byte[] inBuff = new byte[DATA_LEN];
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	private DatagramPacket outPacket;
	
	public static void main(String[] args) throws IOException {
		new UdpClient().init();
	}
	
	public void init() throws IOException {
		try(
				DatagramSocket socket = new DatagramSocket()) {
			// 初始化发送用的DatagramSocket
			outPacket = new DatagramPacket(new byte[0], 0, InetAddress.getByName(DEST_IP), DEST_PORT);
			
			// 创建键盘输入流
			try(Scanner scan = new Scanner(System.in)) {
				while(scan.hasNextLine()) {
					byte[] buff = scan.nextLine().getBytes();
					outPacket.setData(buff);
					socket.send(outPacket);
					socket.receive(inPacket);
					System.out.println(new String(inBuff, 0, inPacket.getLength()));
				}
			}
		}
	}
}
