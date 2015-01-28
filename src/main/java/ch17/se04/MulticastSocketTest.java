package ch17.se04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class MulticastSocketTest implements Runnable {
	// 使用常量作为本程序的多点广播IP地址
	private static final String BROADCAST_IP = "230.0.0.1";
	// 端口
	private static final int BROADCAST_PORT = 30000;
	
	// 定义每个数据报文的最大大小为4KB
	private static final int DATA_LEN = 4096;
	
	private MulticastSocket socket;
	private InetAddress broadcastAddress;
	
	private byte[] inBuff = new byte[DATA_LEN];
	
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	private DatagramPacket outPacket;
	
	public void init() throws IOException {
		try(
				Scanner scan = new Scanner(System.in)) {
			// 创建用于发送、接收数据的MulticastSocket对象
			socket = new MulticastSocket(BROADCAST_PORT);
			broadcastAddress = InetAddress.getByName(BROADCAST_IP);
			socket.joinGroup(broadcastAddress);
			socket.setLoopbackMode(false);
			outPacket = new DatagramPacket(new byte[0], 0, broadcastAddress, BROADCAST_PORT);
			
			new Thread(this).start();
			
			// 不断地读取键盘输入
			while(scan.hasNextLine()) {
				byte[] buff = scan.nextLine().getBytes();
				outPacket.setData(buff);
				socket.send(outPacket);
			}
		} finally {
			socket.close();
		}
	}

	public static void main(String[] args) throws IOException {
		new MulticastSocketTest().init();
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				socket.receive(inPacket);
				System.out.println("聊天信息：" + new String(inBuff, 0, inPacket.getLength()));
			}
		} catch(IOException e) {
			e.printStackTrace();
			try {
				if(socket != null) {
					socket.leaveGroup(broadcastAddress);
					socket.close();
				}
				System.exit(1);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
