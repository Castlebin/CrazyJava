package ch17.se03.Senior.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import ch17.se03.Senior.CrazyitProtocal;

public class Client {
	private static final int SERVER_PORT = 3000;
	
	private Socket socket;
	private PrintStream ps;
	private BufferedReader brServer;
	private BufferedReader keyIn;
	
	public static void main(String[] args) {
		Client client = new Client();
		client.init();
		client.readAndSend();
	}
	
	public void init() {
		try {
			keyIn = new BufferedReader(new InputStreamReader(System.in));
			// 连接到服务端
			socket = new Socket("127.0.0.1", SERVER_PORT);
			ps = new PrintStream(socket.getOutputStream());
			brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String tip = "";
			String userName = "";
			while(true) {// 采用循环要求用户先输入用户名（相当于让用户先登陆）
				userName = JOptionPane.showInputDialog(tip+"输入用户名！");
				// 加入协议字符串
				ps.println(CrazyitProtocal.USER_ROUND + userName + CrazyitProtocal.USER_ROUND);
				// 读取服务器的响应
				String result = brServer.readLine();
				// 如果用户名重复
				if(result.equals(CrazyitProtocal.NAME_REP)) {
					tip = "用户名重复！请重新";
					continue;// 用户名重新，要求换一个用户名再重新登陆
				} else if(result.equals(CrazyitProtocal.LOGIN_SUCCESS)) {// 登录成功
					System.out.println("我是"+userName+"，现在已经成功登陆聊天室！");
					break;
				}
			}
		} catch(UnknownHostException e) {
			System.out.println("找不到远程服务器，请确定服务器已经启动！");
			closeRs();
			System.exit(1);
		} catch (IOException e) {
			System.out.println("网络异常！请重新登录！");
			closeRs();
			System.exit(1);
		}
		
		new ClientThread(brServer).start();
	}
	
	// 不断读取键盘输入，向服务器端发送数据
	private void readAndSend() {
		try {
			String line = null;
			while((line=keyIn.readLine()) != null) {
				// 如果发送的消息中有冒号，且以//开头，则认为是想发送私信
				if(line.indexOf(":")>0 && line.startsWith("//")) {
					line = line.substring(2);
					ps.println(CrazyitProtocal.PRIVATE_ROUND + line.split(":")[0]+
							CrazyitProtocal.SPLIT_SIGN+line.split(":")[1]+CrazyitProtocal.PRIVATE_ROUND);
				} else {
					ps.println(CrazyitProtocal.MSG_ROUND + line +CrazyitProtocal.MSG_ROUND);
				}
			}
		} catch(IOException e) {
			System.out.println("通讯异常，请重新登录！");
			closeRs();
			System.exit(1);
		}
	}

	private void closeRs() {
		try {
			if(keyIn != null) {
				keyIn.close();
			}
			if(brServer != null) {
				brServer.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(socket != null) {
				socket.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
