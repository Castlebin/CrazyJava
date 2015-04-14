package ch07.se05;

public class MatchModeTest {
	public static void main(String[] args) {
		String str = "hello, java!";
		
		// 默认为贪婪模式
		System.out.println(str.replaceFirst("\\w*", "★"));//输出★, java!
		
		// 勉强模式（在用来做匹配的模式字符串后面加上问号（?））
		System.out.println(str.replaceFirst("\\w*?", "★"));//输出★hello, java!
		
		// 占有模式（加上+号，目前只有Java支持这种模式）
		System.out.println(str.replaceFirst("\\w*+", "★"));//输出★, java!
		
	}
}
