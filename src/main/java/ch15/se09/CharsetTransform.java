package ch15.se09;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharsetTransform {
	public static void main(String[] args) throws CharacterCodingException {
		Charset cn = Charset.forName("GBK");
		// 获取对应的编码器和解码器
		CharsetEncoder cnEncoder = cn.newEncoder();
		CharsetDecoder cnDecoder = cn.newDecoder();
		
		// 创建一个CharBuffer
		CharBuffer cbuff = CharBuffer.allocate(16);
		cbuff.put('孙');
		cbuff.put('悟');
		cbuff.put('空');
		
		// 调用flip()方法，为读取数据做好准备
		cbuff.flip();
		
		// 将cbuff中内容转换为ByteBuffer
		ByteBuffer bbuff = cnEncoder.encode(cbuff);
		// 循环访问ByteBuffer中每个字节
		for(int i=0; i<bbuff.capacity(); i++) {
			System.out.print(bbuff.get(i));
		}
		
		// 解码
		System.out.println("\n" + cnDecoder.decode(bbuff));
	}
}
