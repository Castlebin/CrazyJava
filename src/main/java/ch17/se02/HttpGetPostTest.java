package ch17.se02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpGetPostTest {
	public static void main(String[] args) {
		final String BASE_URL = "http://www.zhuhaicitybike.com/zhmap/ibikeinterface.asp";
		System.out.println(HttpGetPostTest.sendGetRequest(BASE_URL, null));
		System.out.println(HttpGetPostTest.sendGetRequest(BASE_URL, "user=heller&password=heheh"));
	}
	
	public static String sendGetRequest(String url, String param) {
		try {
			String result = null;
			url = url + "?" + param;
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.connect();
			
			// 获取所有的响应头
			Map<String, List<String>> map = conn.getHeaderFields();
			for(String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while((line=in.readLine()) != null) {
				result +=  line + "\n";
			}
			
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String sendPostRequest(String url, String param) {
		try {
			String result = null;
			
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setDoInput(true);
			conn.setDoOutput(true);	// post请求必须设置这两条参数
			
			try(
					PrintWriter out = new PrintWriter(conn.getOutputStream())) {
				// 发送请求参数
				out.print(param);
				// flush输出流的缓冲区
				out.flush();
			}
			
			// 获取所有的响应头
			Map<String, List<String>> map = conn.getHeaderFields();
			for(String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while((line=in.readLine()) != null) {
				result +=  line + "\n";
			}
			
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
