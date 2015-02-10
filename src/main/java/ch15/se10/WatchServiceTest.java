package ch15.se10;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchServiceTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		// 获取文件系统的WathcService对象
		WatchService watchService = FileSystems.getDefault().newWatchService();
		// 为tmp目录注册监听
		Paths.get("/tmp").register(watchService, 
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);
		
		while(true) {
			// 获取下一个文件变化事件
			WatchKey key = watchService.take();
			for(WatchEvent<?> event : key.pollEvents()) {
				System.out.println(event.context()+" 文件发生了事件：" + event.kind());
			}
			
			// 重设WatchKey，如果重设失败，退出监听
			boolean valid = key.reset();
			if(!valid) {
				break;
			}
		}
	}
}
