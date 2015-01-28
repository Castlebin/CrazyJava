package ch17.se02;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLDecoderTest {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String keyword = "我爱你, I love you.";
		// 1. url编码，使用utf-8
		String enStr = URLEncoder.encode(keyword, "UTF-8");
		System.out.println(enStr);
		
		// 2. url解码
		String deStr = URLDecoder.decode(enStr, "UTF-8");
		System.out.println(deStr);
	}

}
