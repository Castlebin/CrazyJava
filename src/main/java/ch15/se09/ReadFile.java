package ch15.se09;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ReadFile {
	public static void main(String[] args) throws IOException {
		try(FileInputStream in = new FileInputStream("src/main/java/ch15/se09/ReadFile.java");
				FileChannel inChannel = in.getChannel()) {
			// 创建好字符集对象及对应的解码器对象
			Charset charset = Charset.forName("UTF-8");
			CharsetDecoder decoder = charset.newDecoder();
			// 定义一个定长的buffer，用于分多次来读取数据，而不是一次性将所有数据映射到内存中
			ByteBuffer buff = ByteBuffer.allocate(64);
			// 使用循环重复读取文件
			while(inChannel.read(buff) != -1) {
				// 锁定Buffer的空白区
				buff.flip();
				
				// 将ByteBuffer的内容转码
				CharBuffer cbuff = decoder.decode(buff);// Exception in thread "main" java.nio.charset.MalformedInputException: Input length = 1
				System.out.print(cbuff);
				
				// 使用clear()，为下次读取数据做准备
				buff.clear();
			}
		}
	}
}
