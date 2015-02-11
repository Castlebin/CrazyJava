package ch07.se05;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartEnd {
	public static void main(String[] args) {
		String str = "Java is very easy!";
		
		String regexStr = "\\w+";// 用来匹配单词的正则表达式字符串
		
		System.out.println("目标字符串是：" + str);
		System.out.println("正则表达式为：" + regexStr);
		
		// 创建一个Pattern对象，并且用它创建一个Matcher对象
		Pattern p = Pattern.compile(regexStr);
		
		Matcher m = p.matcher(str);
		
		// 将所有匹配的字符串输出
		while(m.find()) {
			System.out.println(m.group() + "子串的起始位置：" + m.start() + ", 结束位置：" + m.end());
		}
	}
}
