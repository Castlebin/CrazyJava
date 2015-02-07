package ch15.se04;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

// 以字符串或者字符数组（或者字节数组）作为输入输出流节点
public class StringNodeTest {
	public static void main(String[] args) throws IOException {
		String src = "从明天起，做一个幸福的人\n"
				+ "喂马、劈柴、周游世界\n"
				+ "从明天起，关心粮食和蔬菜\n"
				+ "我有一座房子，面朝大海、春暖花开\n"
				+ "从明天起，和每一个亲人通信\n"
				+ "告诉他们我的幸福\n";
		
		char[] cbuf = new char[32];
		int hasRead = 0;
		
		// 使用字符串作为输入流节点
		try(StringReader sr = new StringReader(src)) {
			while((hasRead=sr.read(cbuf)) > 0) {
				System.out.print(new String(cbuf, 0, hasRead));
			}
		}
		System.out.println("------------------------------------");
		
		// 使用字符串作为输出流节点
		// 下面创建一个StringWriter其实就是以一个StringBuffer作为输出流节点，参数20指的就是这个StringBuffer的初始长度
		try(StringWriter sw = new StringWriter(20)) {
			// 执行输出
			sw.write("我爱你-匿名用户\n");
			sw.write("我爱你\n");
			sw.write("但我不敢说\n");
			sw.write("我怕我说了\n");
			sw.write("就会死\n");
			sw.write("我不怕死\n");
			sw.write("我怕我死了\n");
			sw.write("再没有人像我这样爱你\n");
			
			// 好了，现在可以查看一下字符串输出流节点里面的数据内容了
			System.out.println(sw.toString());
		}
	}
}
