package ch07.se05;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindGroup {
	public static void main(String[] args) {
		// 使用字符串模拟网络爬虫获得的一个字符串
		String str = "我想求购一本《疯狂Java讲义》，请尽快联系我，电话，15088881234。"
				+ "代购信息，详情：13425890000。"
				+ "出售二手电脑，13412341234。";
		
		String regexStr = "((13\\d)|(15\\d)|(18\\d))\\d{8}";// 用来匹配电话号码的正则表达式字符串
		
		System.out.println("目标字符串是：" + str);
		System.out.println("正则表达式为：" + regexStr);
		
		// 创建一个Pattern对象，并且用它创建一个Matcher对象
		Pattern p = Pattern.compile(regexStr);
		
		Matcher m = p.matcher(str);
		
		// 将所有匹配的字符串输出
		while(m.find()) {
			System.out.println(m.group());
		}
	}
}
