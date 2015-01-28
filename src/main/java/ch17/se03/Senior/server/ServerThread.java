package ch17.se03.Senior.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import ch17.se03.Senior.CrazyitProtocal;

public class ServerThread extends Thread {
	private Socket socket;
	private BufferedReader br;
	private PrintStream ps;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		String userName = null;
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ps = new PrintStream(socket.getOutputStream());
			String line = null;
			while((line=br.readLine()) != null) {
				System.out.println(Server.clients.getKeyByValue(ps)+": "+line);	// 打印出真实的消息内容，便于调试
				// 如果消息以CrazyitProtocal.USER_ROUND开始和结束，则确定表示的是用户登录的用户名
				if(line.startsWith(CrazyitProtocal.USER_ROUND) && line.endsWith(CrazyitProtocal.USER_ROUND)) {
					// 得到真实消息
					userName = getRealMsg(line);
					// 如果用户名重复
					if(Server.clients.map.containsKey(userName)) {
						System.out.println(userName+" 重复登录！");
						// 告知客户端重复登录(返回特定的响应码)
						ps.println(CrazyitProtocal.NAME_REP);
					} else {
						Server.clients.map.put(userName, ps);
						// 告知客户端登录成功(返回特定的响应码)
						ps.println(CrazyitProtocal.LOGIN_SUCCESS);
						String welcome = userName + "登录了！目前客户端在线用户数为："+Server.clients.map.size()+"，用户列表为："+Server.getUserList();
						System.out.println(welcome);
						for(PrintStream clientPs : Server.clients.valueSet()) {
							clientPs.println(welcome);
						}
						
					}
				}
				// 如果是以CrazyitProtocal。PRIVATE_ROUND开始和结束，可以判断其实私聊信息
				else if(line.startsWith(CrazyitProtocal.PRIVATE_ROUND) && line.endsWith(CrazyitProtocal.PRIVATE_ROUND)) {
					String userMsg = getRealMsg(line);
					// 以CrazyitProtocal.SPLIT_SIGN分割消息字符串，前面是私聊的目标对象，后面是聊天信息
					String[] userAndMsg = userMsg.split(CrazyitProtocal.SPLIT_SIGN);
					String user = userAndMsg[0];
					String msg = userAndMsg[1];
					// 发送私聊信息, 私聊的话需要确定该用户现在确实已经登陆了
					PrintStream targetPs = Server.clients.map.get(user);
					if(targetPs != null) {
						Server.clients.map.get(user).println(userName + "悄悄对你说：" + msg);
					} else {
						ps.print("对不起，请确认你所要私聊的用户"+user+"在线！");
					}
				}
				// 公聊，向每个人都发送
				else {
					String msg = getRealMsg(line);
					for(PrintStream clientPs : Server.clients.valueSet()) {
						if(clientPs != this.ps) {
							clientPs.println(userName+"说："+msg);
						}
					}
				}
			}
		} catch(IOException e) {// 捕获异常的话，说明对应的客户端出了问题，需要从列表中移除
			Server.clients.removeByValue(ps);
			String welcome = userName+"退出了，还剩"+Server.clients.map.size()+"位用户。用户列表为："+Server.getUserList();
			System.out.println(welcome);
			for(PrintStream clientPs : Server.clients.valueSet()) {
				clientPs.println(welcome);
			}
			// 关闭资源
			try
			{
				if (br != null)	{
					br.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (socket != null) {
					socket.close();
				}
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private String getRealMsg(String line) {
		return line.substring(CrazyitProtocal.PROTOCAL_LEN, line.length()-CrazyitProtocal.PROTOCAL_LEN);
	}
	
}
