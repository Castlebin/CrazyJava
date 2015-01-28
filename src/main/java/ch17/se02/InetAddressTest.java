package ch17.se02;

import java.io.IOException;
import java.net.InetAddress;

public class InetAddressTest {
	public static void main(String[] args) throws IOException {
		String hostName = "www.crazyit.org";
		// 根据主机名来获取对应的InetAddress实例
		InetAddress ip = InetAddress.getByName(hostName);
		// 判断是否可达
		System.out.println(hostName+" isReachable: " + ip.isReachable(2000));
		
		// 获取该InetAddress实例的IP，以字符串方式返回
		System.out.println(ip.getHostAddress());
		
		// 根据原始IP地址来获取InetAddress实例
		InetAddress local = InetAddress.getByAddress(new byte[]{127,0,0,1});
		// 判断本机是否可达
		System.out.println(local.isReachable(2000));
		// 获取该InetAddress的全限定域名
		System.out.println(local.getCanonicalHostName());
		
		// 获取本机的InetAddress实例
		InetAddress localInetAddress = InetAddress.getLocalHost();
		System.out.println(localInetAddress.getCanonicalHostName());
		System.out.println(localInetAddress.getHostAddress());
	}
}
