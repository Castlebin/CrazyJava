package ch07.se05;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchesTest {
	public static void main(String[] args) {
		String[] mails = { 
				"kongyeeku@163.com", 
				"kongyeeku@gmail.com",
				"ligang@crazyit.org", 
				"wawa@abc.xx" };
		String mailRegEx = "\\w{3,20}@\\w+\\.(com|org|cn|net|gov)";	// 用来匹配邮箱地址的正则表达式
		
		Pattern mailPattern = Pattern.compile(mailRegEx);
		Matcher matcher = null;
		for(String mail : mails) {
			if(matcher==null) {
				matcher = mailPattern.matcher(mail);
			} else {
				matcher.reset(mail);
			}
			
			String result = mail + "\t是一个合法的邮件地址？\t" + matcher.matches();
			System.out.println(result);
		}
	}
}
