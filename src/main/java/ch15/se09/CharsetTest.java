package ch15.se09;

import java.nio.charset.Charset;
import java.util.SortedMap;

public class CharsetTest {
	public static void main(String[] args) {
		// 获取锁使用的Java版本支持的全部字符编码集
		SortedMap<String, Charset> map = Charset.availableCharsets();
		System.out.println("目前使用的Java版本支持的字符集种类有：" + map.size() + "种，如下：");
		for(String alias : map.keySet()) {
			System.out.println(alias + " --> " + map.get(alias));
		}
	}
}
