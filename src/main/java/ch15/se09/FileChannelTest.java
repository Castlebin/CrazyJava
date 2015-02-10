package ch15.se09;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		File f = new File("src/main/java/ch15/se09/FileChannelTest.java");
		
		// 使用标准的节点流的getChannel()方法返回其对应的Channel实例
		
		try(FileInputStream fileInputStream = new FileInputStream(f);
				FileOutputStream fileOutputStream = new FileOutputStream("out.txt");
				FileChannel inChannel = fileInputStream.getChannel();
				FileChannel outChannel = fileOutputStream.getChannel()) {
			
			// ** 使用map方法将FileChannel中的全部数据映射为ByteBuffer **/
			MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
			
			// 直接将buffer里面的数据全部输出
			outChannel.write(buffer);
			
			// 现在调用clear()方法，复原limit、position的位置
			buffer.clear();
			
			// 创建字符集编码器
			Charset charset = Charset.forName("UTF-8");
			// 创建解码器对象
			CharsetDecoder decoder = charset.newDecoder();
			// 使用解码器将ByteBuffer转换为CharBuffer
			CharBuffer charBuffer = decoder.decode(buffer);
			// 打印（CharBuffer对象的toString()方法可以获得对应的字符串）
			System.out.println(charBuffer);
		}
	}
}
