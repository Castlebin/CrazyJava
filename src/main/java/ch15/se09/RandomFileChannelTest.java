package ch15.se09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class RandomFileChannelTest {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		File f = new File("out.txt");
		try(RandomAccessFile raf = new RandomAccessFile(f, "rw");
				FileChannel randomChannel = raf.getChannel()) {
			// 将Channel中的所有数据映射到内存中
			MappedByteBuffer buffer = randomChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());
			
			// 把Channel的记录指针position值移到末尾，再把buffer中的内容输出到Channel（即完成将原文件复制一份，然后添加到它自己末尾）
			randomChannel.position(f.length());
			randomChannel.write(buffer);
		}
	}
}
