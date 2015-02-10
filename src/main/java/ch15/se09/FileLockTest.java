package ch15.se09;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

// 文件锁是由JVM持有的，所以其实它管不了其他的进程
// 不同的操作系统或者平台支持程度不一，所以还是不要使用了，多进程下访问并不能保证安全
public class FileLockTest {
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		try(FileOutputStream fileOutputStream = new FileOutputStream("out.txt");
				FileChannel channel = fileOutputStream.getChannel()) {
			// 使用阻塞方式对文件进行加锁
			FileLock lock = channel.lock();
			
			// 程序暂停30s
			Thread.sleep(30 * 1000);
			
			// 用完需要释放文件锁
			lock.release();
		}
	}
}
