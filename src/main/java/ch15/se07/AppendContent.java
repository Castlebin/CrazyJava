package ch15.se07;

import java.io.IOException;
import java.io.RandomAccessFile;

// 向文件尾部追加内容
// 为了向文件尾部追加内容，需要先将文件指针移动到文件尾部，然后向里写入内容即可
public class AppendContent {
	public static void main(String[] args) throws IOException {
		try(RandomAccessFile raf = new RandomAccessFile("hehe.txt", "rw")) {
			// 将文件指针移动到文件尾部
			raf.seek(raf.length());
			
			// 追加内容
			raf.write("这行是追加的内容！hhhh\n".getBytes());
		}
	}
}
