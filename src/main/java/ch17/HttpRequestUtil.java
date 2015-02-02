package ch17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class HttpRequestUtil {
	private static final int CONNECT_TIMEOUT = 30 * 1000;
	private static final int READ_TIMEOUT = 60 * 1000;
	
	public static Logger logger = Logger.getLogger(HttpRequestUtil.class);

	public static String getHttpGetResponse(String requestUrl) {
		return getHttpGetResponse(requestUrl, null);
	}
	
	public static String getHttpGetResponse(String requestUrl, Map<String,String> paramMap) {
		return getHttpGetResponse(requestUrl, paramMap, "UTF-8");
	}
	
	public static String getHttpGetResponse(String requestUrl, Map<String,String> paramMap, String responseCharsetName) {
		return getHttpGetResponse(requestUrl, paramMap, responseCharsetName, CONNECT_TIMEOUT, READ_TIMEOUT);
	}
	
	public static String getHttpGetResponse(String requestUrl, Map<String,String> paramMap, String responseCharsetName, int connectTimeout, int readTimeout) {
		StringBuilder sb = new StringBuilder();
		
		try {
			HttpURLConnection conn = null;
			String paramString = paramMapToString(paramMap);
			if(paramString != null) {
				requestUrl = requestUrl + "?" + paramString;
			}
			URL url = new URL(requestUrl);
			conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			conn.connect();
			
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				String line = null;
				try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), responseCharsetName))) {
					while ((line = br.readLine()) != null) {
						sb.append(line+"\n");
					}
				}

				return sb.toString();
			}
		} catch (Exception e) {
			logger.error("暂时无法访问 "+requestUrl+" , 网络错误！Exception: " + e);
		}
		
		return null;
	}
	
	public static String getHttpPostResponse(String requestUrl, Map<String,String> paramMap) {
		return getHttpPostResponse(requestUrl, paramMap, "UTF-8");
	}
	
	public static String getHttpPostResponse(String requestUrl, Map<String,String> paramMap, String responseCharsetName) {
		return getHttpPostResponse(requestUrl, paramMap, responseCharsetName, CONNECT_TIMEOUT, READ_TIMEOUT);
	}
	
	public static String getHttpPostResponse(String requestUrl, Map<String,String> paramMap, String responseCharsetName, int connectTimeout, int readTimeout) {
		StringBuilder sb = new StringBuilder();
		
		try {
			HttpURLConnection conn = null;
			URL url = new URL(requestUrl);
			conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			try(
					PrintWriter out = new PrintWriter(conn.getOutputStream())) {
				out.print(paramMapToString(paramMap));
				out.flush();
			}
			
				String line = null;
				try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), responseCharsetName))) {
					while ((line = br.readLine()) != null) {
						sb.append(line+"\n");
					}
				}

				return sb.toString();
		} catch (Exception e) {
			logger.error("暂时无法访问 "+requestUrl+" , 网络错误！Exception: " + e);
		}
		
		return null;
	}

	public static String paramMapToString(Map<String, String> paramMap) {
		if(paramMap==null || paramMap.size()==0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(Entry<String, String> entry : paramMap.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if(key!=null || value!=null) {
				sb.append(key+"="+value+"&");
			}
		}
		
		return sb.substring(0, sb.length()-1);
	}
	
}